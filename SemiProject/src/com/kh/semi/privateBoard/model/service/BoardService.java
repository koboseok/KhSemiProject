package com.kh.semi.privateBoard.model.service;
import static com.kh.semi.common.JDBCTemplate.*;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kh.semi.privateBoard.model.dao.BoardDAO;
import com.kh.semi.privateBoard.model.exception.FileInsertFailedException;
import com.kh.semi.privateBoard.model.vo.Attachment;
import com.kh.semi.privateBoard.model.vo.Board;
import com.kh.semi.privateBoard.model.vo.PageInfo;


public class BoardService {

	private BoardDAO dao = new BoardDAO();

	/**페이징 처리를 위한 값 계산 Service
	 * @param cp
	 * @return pInfo
	 * @throws Exception
	 */
	public PageInfo getPageInfo(String cp) throws Exception {
		Connection conn = getConnection();

		//cp가 null인 경우
		int currentPage = cp == null ? 1: Integer.parseInt(cp);

		//DB에서 전체 게시글 수를 조회하여 반환받기
		int listCount = dao.getListCount(conn);

		close(conn);

		//얻어온 현재 페이지와, DB에서 조회한 전체 게시글 수를 이용하여
		//PageInfo객체를 생성함
		return new PageInfo(currentPage, listCount);
	}

	/** 게시글 목록 조회
	 * @param pInfo
	 * @return bList
	 * @throws Exception
	 */
	public List<Board> selectBoardList(PageInfo pInfo) throws Exception {
		Connection conn = getConnection();

		List<Board> bList = dao.selectBoardList(conn, pInfo);

		close(conn);

		return bList;
	}

	public Board selectBoard(int boardNo) throws Exception {
		Connection conn = getConnection();
		Board board = dao.selectBoard(conn, boardNo);

		if(board != null) { //db에서 조회 성공 시
			//조회 수 증가
			int result = dao.increaseReadCount(conn, boardNo);
			if(result>0) { commit(conn);

			//반환되는 board데이터에는 조회수가 증가되어 있지 않기 때문에
			//조회수를 1 증가시켜 줌
			board.setReadCount(board.getReadCount()+1);
			}

			else		rollback(conn);
		}
		close(conn);

		return board;
	}

	/**게시글 등록 Service(게시글+파일)
	 * @param map
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(Map<String, Object> map) throws Exception {
		Connection conn = getConnection();

		int result = 0;

		//1. 게시글 번호 얻어오기
		int boardNo = dao.selectNextNo(conn);

		if(boardNo>0) {
			//얻어온 게시글 번호를 map에 추가(게시글, 파일정보 삽입 DAO에 사용하기 위해)
			map.put("boardNo", boardNo);

			//2. 글 제목/내용에 크로스 사이트 스크립팅 방지 --스크립트를 이용한 악의적 공격
			String boardTitle = (String)map.get("boardTitle");
			String boardContent = (String)map.get("boardContent");

			boardTitle = replaceParameter(boardTitle);
			boardContent = replaceParameter(boardContent);

			//3. 글 내용 개행문자 \r\n -> <br> 변경 처리
			boardContent = boardContent.replaceAll("\r\n", "<br>");

			//처리된 내용을 다시 map에 추가
			map.put("boardTitle", boardTitle);
			map.put("boardContent", boardContent);


			try {

				//4. 게시글 부분(제목, 내용, 카테고리)만 BOARD테이블에 삽입하는 DAO 호출
				result = dao.insertBoard(conn, map);

				//autoCommit을 꺼놔도 error가 안 나면 commit진행
				//autoCommit은 'executeUpdate 되자마자' commit을 하는 것
				//connection을 반환하는 등의 동작 뒤 commit은 AutoCommit이 꺼져있어도 발생함

				if(result>0) {
					commit(conn);
				} else {
					rollback(conn);
				}


				//5. 파일 정보 부분만 Attachment테이블에 삽입하는 DAO 호출
				List<Attachment> fList = (List<Attachment>)map.get("fList");

				//게시글 부분 삽입 성공 & 파일 정보가 있을 경우
				if(result>0 && !fList.isEmpty()) {
					result = 0; //result재활용을 위해 0으로 초기화

					//fList의 요소를 하나씩 반복 접근하여
					//DAO메소드를 반복 호출해 정보를 삽입함
					for(Attachment at : fList) {
						//파일 정보가 저장된 Attachment객체에
						//해당 파일이 작성된 게시글 번호를 추가 세팅
						at.setParentBoardNo(boardNo);

						//에러가 나면 트랜잭션 처리 결과 삽입 x
						result = dao.insertAttachment(conn, at);

						if(result == 0) { //파일 정보 삽입 실패
							//강제로 예외 발생
							throw new FileInsertFailedException("파일 정보 삽입 실패");
						}
					}
				}
			} catch(Exception e) {
				//4,5번에 대한 추가 작업
				//게시글 또는 파일 정보 삽입 중 에러 발생 시
				//서버에 저장된 파일을 삭제하는 작업이 필요

				List<Attachment> fList = (List<Attachment>)map.get("fList");

				if(!fList.isEmpty()) {

					for(Attachment at : fList) {
						String filePath = at.getFilePath();
						String fileName = at.getFileName();

						File deleteFile = new File(filePath+fileName);
						if(deleteFile.exists()) {
							//해당 경로에 파일이 존재하면
							deleteFile.delete(); //해당 파일 삭제

						}
					}
				}

				//에러페이지 보여질 수 있도록 catch한 exception을 controller로 보내준다
				throw e;

			}//end catch

			//6. 트랜잭션 처리
			if(result>0) {
				commit(conn);

				//삽입 성공시 상세조회 화면으로 이동해야하기 때문에
				//글 번호를 반환할 수 있도록 result에 boardNo를 대입
				result = boardNo;
			} else {
				rollback(conn);
			}

			//7. 커넥션 반환

			close(conn);
		}


		//8. 결과 반환

		return result;
	}

	//크로스 사이트 스크립팅 방지 메소드
	private String replaceParameter(String param) {
		String result = param;
		if(param != null) {
			result = result.replaceAll("&", "&amp;");
			result = result.replaceAll("<", "&lt;");
			result = result.replaceAll(">", "&gt;");
			result = result.replaceAll("\"", "&quot;");
		}
		return result;
	}

	/** 게시글에 포함된 이미지 목록 조회 service
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public List<Attachment> selectBoardFiles(int boardNo) throws Exception{
		Connection conn = getConnection();

		List<Attachment> fList = dao.selectBoardFiles(conn, boardNo);

		close(conn);

		return fList;
	}

	/** 썸네일 목록 조회 Service
	 * @param pInfo
	 * @return fList
	 * @throws Exception
	 */
	public List<Attachment> selectThumbnailList(PageInfo pInfo) throws Exception {
		Connection conn = getConnection();
		List<Attachment> fList = dao.selectThumbnailList(conn, pInfo);
		close(conn);
		return fList;
	}

	/**게시글 수정 화면 출력용 Service
	 * @param boardNo
	 * @return board
	 * @throws Exception
	 */
	public Board updateView(int boardNo) throws Exception {
		Connection conn = getConnection();

		//이전에 만들어둔 상세조회 DAO 호출
		Board board = dao.selectBoard(conn, boardNo);

		//textarea출력을 위한 개행문자 변경
		board.setBoardContent(board.getBoardContent().replaceAll("<br>", "\r\n"));

		close(conn);

		return board;
	}

	/**게시글 수정 Service
	 * @param map
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(Map<String, Object> map) throws Exception {
		Connection conn = getConnection();

		int result = 0; //service수행 결과 저장 변수
		List<Attachment> deleteFiles = null; //썸네일 등 바꿨을 때 삭제할 파일 정보 저장(서버에서 불필요한 파일)

		//1. 글 제목/내용에 크로스 사이트 스크립팅 방지 --스크립트를 이용한 악의적 공격
		String boardTitle = (String)map.get("boardTitle");
		String boardContent = (String)map.get("boardContent");

		boardTitle = replaceParameter(boardTitle);
		boardContent = replaceParameter(boardContent);

		//2. 글 내용 개행 문자 \r\n → <br> 변경 처리
	    boardContent = boardContent.replaceAll("\r\n", "<br>");


		try {
			//3. 게시글 부분 수정 DAO 호출
			result = dao.updateBoard(conn, map);

			//4. 게시글 수정이 성공하고 fList가 비어있지 않으면 
			//	파일 정보 수정 DAO 호출

			//수정 화면에서 새로운 이미지가 업로드 된 파일 정보만을 담고 있는 List
			List<Attachment> newFileList = (List<Attachment>)map.get("fList");

			if(result>0 && !newFileList.isEmpty() ) {
				//DB에서 해당 게시글의 수정 전 파일 목록을 조회함
				List<Attachment> oldFileList = dao.selectBoardFiles(conn, (int)map.get("boardNo"));

				//newFileList -> 수정된 썸네일(lv.0)
				//oldFileList -> 썸네일 (lv.0) 이미지1(lv.1) 이미지2(lv.2)
				//기존 썸네일(lv.0) -> 수정된 썸네일(lv.0)으로 변경됨
				//--> DB에서 기존 썸네일의 데이터를 수정된 썸네일로 변경
				// ---> db에서 기존 썸네일 정보가 사라짐

				result = 0; //result 재활용
				deleteFiles = new ArrayList<Attachment>(); //삭제될 정보 저장할 변수
				for(Attachment newFile : newFileList) {
					//flag = false인 경우는 새 이미지와 기존 이미지의 파일 레벨이 중복되는 경우 -> update
					//flag = true인 경우는 새 이미지와 기존 이미지의 파일 레벨이 중복되지 않는 경우 -> insert
					boolean flag = true;

					for(Attachment oldFile : oldFileList) {
						if(newFile.getFileLevel() == oldFile.getFileLevel()) {

							//기존 파일을 삭제 List에 추가
							deleteFiles.add(oldFile);

							//새 이미지 정보에 이전 파일 번호를 추가 -> 파일 번호를 이용한 수정 진행
							newFile.setFileNo(oldFile.getFileNo());
							flag = false;
							break;
						}
					}

					//flag값에 따라 파일 정보 insert 또는 update 진행
					if(flag) {
						result = dao.insertAttachment(conn, newFile);
					} else {
						result = dao.updateAttachment(conn, newFile);
					}

					//파일 정보 삽입 또는 수정 중 실패 시
					if(result == 0) {
						//강제로 사용자 정의 예외 발생
						throw new FileInsertFailedException();
					}
				}
			}

		}catch(Exception e) {
			//게시글 수정 중 실패 또는 오류 발생 시
			//서버에 미리 저장되어 있던 이미지 파일 삭제

			List<Attachment> fList = (List<Attachment>)map.get("fList");

			if(!fList.isEmpty()) {

				for(Attachment at : fList) {

					String filePath = at.getFilePath();
					String fileName = at.getFileName();

					File deleteFile = new File(filePath+fileName);
					if(deleteFile.exists()) {
						//해당 경로에 파일이 존재하면
						deleteFile.delete(); //해당 파일 삭제

					}
				}
			}

			//에러페이지 보여질 수 있도록 catch한 exception을 controller로 보내준다
			throw e;
		}

		//5. 트랜잭션 처리 및 삭제 목록에 있는 파일 삭제
		if(result>0) {
			commit(conn);
			//DB정보와 맞지 않는 파일(deleteFiles) 삭제 진행
			if(deleteFiles != null) {

				for(Attachment at : deleteFiles) {
					System.out.println(at);
					String filePath = at.getFilePath();
					String fileName = at.getFileName();
					File deleteFile = new File(filePath+fileName);
					if(deleteFile.exists()) {
						//해당 경로에 파일이 존재하면
						deleteFile.delete(); //해당 파일 삭제
					}
				}
			}
		} else {
			rollback(conn);
		}

		close(conn);

		return result;
	}

	/** 게시글 삭제 Service
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int updateBoardStatus(int boardNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.updateBoardStatus(conn, boardNo);
		
		if(result>0) {
			
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
}
