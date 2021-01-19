
package com.kh.semi.freeBoard.model.dao;

import static com.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kh.semi.freeBoard.model.dao.FreeBoardDAO;
import com.kh.semi.freeBoard.model.vo.Attachment;
import com.kh.semi.freeBoard.model.vo.FreeBoard;
import com.kh.semi.freeBoard.model.vo.FreePageInfo;


public class FreeBoardDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;

	private Properties prop = null;

	// 기본 생성자
	public FreeBoardDAO() {
		String fileName = FreeBoardDAO.class.getResource(

				"/com/kh/semi/sql/board/freeBoard-query.xml").getPath();
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 자유 게시판 글 수 반환DAO
	 * 
	 * @param conn
	 * @return fListCount
	 * @throws Exception
	 */

	public int getfListCount(Connection conn) throws Exception {

		int fListCount = 0;

		String query = prop.getProperty("getfListCount");

		try {

			stmt = conn.createStatement();

			rset = stmt.executeQuery(query);

			if (rset.next()) {
				fListCount = rset.getInt(1);
			}

		} finally {

			close(stmt);

		}

		return fListCount;
	}

	/** 목록  조회 DAO
	 * @param conn
	 * @param fPInfo
	 * @return
	 */
	public List<FreeBoard> selectFBoardList(Connection conn, FreePageInfo fPInfo) throws Exception {

		List<FreeBoard> fList = null;

		String query = prop.getProperty("selectFBoardList");





		try {
			// SQL 구문 조건절에 대입할 변수 생성
			int startRow = (fPInfo.getCurrentPage() - 1) * fPInfo.getLimit() + 1; 
			int endRow = startRow + fPInfo.getLimit() - 1; 

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			fList = new ArrayList<FreeBoard>();


			while (rset.next()) {
				FreeBoard fBoard = new FreeBoard();

				fBoard.setfBoardNo(rset.getInt("FR_NO"));
				fBoard.setfBoardTitle(rset.getString("FR_TITLE"));
				fBoard.setMemName(rset.getString("MEM_NM"));
				fBoard.setfCreateDate(rset.getTimestamp("FR_C_DT"));
				fBoard.setfReadCount(rset.getInt("FR_READ_COUNT"));
				fBoard.setMemGrade(rset.getString("MEM_GRADE"));
				fList.add(fBoard);
			
			
			}
			
			
		}finally {

			close(rset);
			close(pstmt);


		}

		return fList;
	}

	/** 게시글 상세 조회
	 * @param conn
	 * @param fboardNo
	 * @return fBoard
	 * @throws Exception
	 */
	public FreeBoard selectFBoard(Connection conn, int fBoardNo)throws Exception {
	
		FreeBoard fBoard = null;
		
		String query = prop.getProperty("selectFBoard");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, fBoardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				fBoard = new FreeBoard();
				
				fBoard.setfBoardNo(rset.getInt("FR_NO"));
				fBoard.setfBoardTitle(rset.getString("FR_TITLE"));
				fBoard.setfBoardContent(rset.getString("FR_CONTENT"));
				fBoard.setMemName(rset.getString("MEM_NM"));
				fBoard.setfCreateDate(rset.getTimestamp("FR_C_DT"));
				fBoard.setfReadCount(rset.getInt("FR_READ_COUNT"));
				fBoard.setMemGrade(rset.getString("MEM_GRADE"));	
				
			}
	
		}finally {
			close(rset);
			close(pstmt);
			
			
		}
		
		
		
		return fBoard;
	}

	/** 게시물 조회수 증가
	 * @param conn
	 * @param fBoardNo
	 * @return
	 * @throws Exception
	 */
	public int increaseReadCount(Connection conn, int fBoardNo) throws Exception {
		
		int result = 0;
		
		String query = prop.getProperty("increaseReadCount");
		
				try {
	         
	         pstmt = conn.prepareStatement(query);
	         pstmt.setInt(1,fBoardNo);
	         
	         result = pstmt.executeUpdate();
	     
				}finally {
	       
	    	  close(pstmt);
	      }
	      
	      return result;
	   }

	/** 다음 게시글 번호 조회 DAO
	 * @param conn
	 * @return boardNo
	 * @throws Exception
	 */
	public int selectNextNo(Connection conn) throws Exception {
		int boardNo = 0;

		String query = prop.getProperty("selectNextNo");

		try {

			stmt = conn.createStatement();

			rset = stmt.executeQuery(query);

			if (rset.next()) {
				boardNo = rset.getInt(1);
			}

		} finally {
			close(rset);
			close(stmt);
		}

		return boardNo;
	}

	/** 게시글 삽입 DAO
	 * @param conn
	 * @param map
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(Connection conn, Map<String, Object> map) throws Exception {
		int result = 0;

		String query = prop.getProperty("insertBoard");

		try {

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, (int) map.get("boardNo"));
			pstmt.setString(2, (String) map.get("boardTitle"));
			pstmt.setString(3, (String) map.get("boardContent"));
			pstmt.setInt(4, (int) map.get("boardWriter"));
			pstmt.setInt(5, (int) map.get("categoryCode"));

			result = pstmt.executeUpdate();

		} finally {
			close(rset);
			close(pstmt);

		}
		return result;
	}

	/** 파일 정보 삽입 DAO
	 * @param conn
	 * @param at
	 * @return result 
	 * @throws Exception
	 */
	public int insertAttachment(Connection conn, Attachment at) throws Exception {
		int result = 0;

		String query = prop.getProperty("insertAttachment");

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, at.getImgPath());
			pstmt.setString(2, at.getImgName());
			pstmt.setInt(3, at.getImgLevel());
			pstmt.setInt(4, at.getBoardNo());

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 게시글에 포함된 이미지 목록 조회 DAO
	 * @param conn
	 * @param boardNo
	 * @return fList
	 * @throws Exception
	 */
	public List<Attachment> selectBoardFiles(Connection conn, int boardNo) throws Exception {
		List<Attachment> fList = null;

		String query = prop.getProperty("selectBoardFiles");

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, boardNo);

			rset = pstmt.executeQuery();

			fList = new ArrayList<Attachment>();

			while (rset.next()) {

				Attachment at = new Attachment(rset.getInt("FILE_NO"), rset.getString("FILE_NAME"),
						rset.getInt("FILE_LEVEL"));

				at.setImgPath(rset.getString("FILE_PATH"));
				fList.add(at);
			}

		} finally {
			close(rset);
			close(pstmt);

		}

		return fList;
	}

	/** 썸네일 목록 조회 DAO
	 * @param conn
	 * @param fPInfo
	 * @return fList
	 * @throws Exception
	 */
	public List<Attachment> selectThumbnailList(Connection conn, FreePageInfo fPInfo) throws Exception {
		List<Attachment> fList = null;

		String query = prop.getProperty("selectThumbnailList");

		try {
//			위치 홀더에 들어갈 시작행 , 끝 행번호 계산

			int startRow = (fPInfo.getCurrentPage() - 1) * fPInfo.getLimit() + 1;
			int endRow = startRow + fPInfo.getLimit() - 1;

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

//			조회 결과를 저장할 List 생성
			fList = new ArrayList<Attachment>();
			while (rset.next()) {

				Attachment at = new Attachment();
				at.setImgName(rset.getString("FILE_NAME"));
				at.setBoardNo(rset.getInt("PARENT_BOARD_NO"));

				fList.add(at);
			}

		} finally {
			close(rset);
			close(pstmt);

		}

		return fList;
	}
	
	/** 게시글 수정 DAO
	 * @param conn
	 * @param map
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(Connection conn, Map<String, Object> map) throws Exception {
		int result = 0;

		String query = prop.getProperty("updateBoard");

		try {

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, (String) map.get("boardTitle"));
			pstmt.setString(2, (String) map.get("boardContent"));
			pstmt.setInt(3, (int) map.get("categoryCode"));
			pstmt.setInt(4, (int) map.get("boardNo"));

			result = pstmt.executeUpdate();

		} finally {

			close(pstmt);

		}

		return result;
	}

	/** 파일 정보 수정 DAO
	 * @param conn
	 * @param newFile
	 * @return result 
	 * @throws Exception
	 */
	public int updateAttachment(Connection conn, Attachment newFile) throws Exception {
		int result = 0;

		String query = prop.getProperty("updateAttachment");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newFile.getImgPath());
			pstmt.setString(2, newFile.getImgName());
			pstmt.setInt(3, newFile.getImgNo());

			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

	

}



