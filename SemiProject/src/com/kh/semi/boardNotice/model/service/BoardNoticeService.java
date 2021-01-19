package com.kh.semi.boardNotice.model.service;
import static com.kh.semi.common.JDBCTemplate.*;


import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.semi.boardNotice.model.dao.BoardNoticeDAO;
import com.kh.semi.boardNotice.model.vo.BoardNotice;

public class BoardNoticeService {

	
	private BoardNoticeDAO dao = new BoardNoticeDAO();
	
		
	/** 공지사항 목록 조회 Service
	 * @return list
	 * @throws Exception
	 */
	public List<BoardNotice> selectList() throws Exception{
		Connection conn = getConnection();

		List<BoardNotice> list = dao.selectList(conn);

		close(conn);

		return list;
	}

	/** 공지사항 상세조회 Service
	 * @param noticeNo
	 * @return notice
	 * @throws Exception
	 */
	public BoardNotice selectNotice(int noticeNo) throws Exception {
//		1) 커넥션 얻어오기
		Connection conn = getConnection();

//		2) DAO 수행 후 결과 반환
		BoardNotice notice = dao.selectNotice(conn, noticeNo);

//		READ_COUNT 증가 
//		프로시저를 이용해 DB에서 한번에 처리한다 ( 실무 )
//		3) 공지사항 조회 성공 시 조회 수 증가
		if (notice != null) {
			int result = dao.increaseReadCount(conn, noticeNo);

//			트랜잭션 처리
			if (result > 0) {
				commit(conn);

//				이미 조회된 notice에서 readCount 값을 1 증가
				notice.setReadCount(notice.getReadCount() + 1);

			} else {
				rollback(conn);
			}

		}

//		4) 커넥션 반환
		close(conn);

		return notice;
	}

	/** 공지사항 등록
	 * @param map
	 * @return result
	 * @throws Exception
	 */
	public int insertNotice(Map<String, Object> map) throws Exception {
		Connection conn = getConnection();

//		1. 작성될 글번호를 먼제 얻어오는 DAO 메소드 수행
//		이유 1) 글 작성 성공 후 해당 글 상세 조회 페이지로 redirect 하기 위해
//		이유 2) 글 작성 + 파일 첨부 시 발생하는 시퀀스 번호 밀림 현상을 제거하기 위해
		int noticeNo = dao.selectNextNo(conn);

		int result = 0;

		if (noticeNo > 0) { // 다음 글번호를 얻어오는데 성공한 경우

//			2. 실제 공지글 등록 DAO 메소드 수행

//			map에 얻어온 다음 글번호를 세팅
			map.put("noticeNo", noticeNo);

//			크로스 사이트 스크립팅 방지 처리
//												<script> -> &lt;script&gt;
			map.put("noticeTitle", replaceParameter((String) map.get("noticeTitle")));
			map.put("noticeContent", replaceParameter((String) map.get("noticeContent")));

//			개행문자 변경 처리
//			textarea의 개행문자는 \r\n 이지만 
//			상세 조회에서 사용되는 div 태그의 개행문자는 <br>이기 때문에 변경이 필요하다. (화면단에서도 처리할 수 있지만 백단에서 처리하는게 효율이 높다)
			map.put("noticeContent", ((String) map.get("noticeContent")).replaceAll("\r\n", "<br>"));

			result = dao.insertNotice(conn, map);

//			트랜잭션 처리
			if (result > 0) {
				commit(conn);
//				삽입 성공 시 result에 작성한 글 번호를 대입
				result = noticeNo;
//				 글 번호가 최종적으로 반환된다.

			} else
				rollback(conn);
		}

		close(conn);

		return result;

	}

	/** 공지사항 수정 화면
	 * @param noticeNo
	 * @return notice
	 * @throws Exception
	 */
	public BoardNotice upadateView(int noticeNo) throws Exception {
		Connection conn = getConnection();

//		공지사항 상세조회 DAO 메소드 호출
		BoardNotice notice = dao.selectNotice(conn, noticeNo);

//		textarea에 출력하기 위해 개행문자 변경
		if (notice != null) {
			notice.setNoticeContent(notice.getNoticeContent().replaceAll("<br>", "\r\n"));

		}

		close(conn);

		return notice;
	}

	/** 공지사항 수정용
	 * @param map
	 * @return result
	 * @throws Exception
	 */
	public int updateNotice(Map<String, Object> map) throws Exception {
		
		Connection conn = getConnection();

//		크로스사이트스크립팅 방지
		map.put("noticeTitle", replaceParameter((String) map.get("noticeTitle")));
		map.put("noticeContent", replaceParameter((String) map.get("noticeContent")));

//		개행문자 변경 처리
		map.put("noticeContent", ((String) map.get("noticeContent")).replaceAll("\r\n", "<br>"));

		int reuslt = dao.updateNotice(conn, map);

		if (reuslt > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return reuslt;
	}

	/** 공지사항 삭제
	 * @param noticeNo
	 * @return result
	 * @throws Exception
	 */
	public int updateNoticeFl(int noticeNo) throws Exception {
		Connection conn = getConnection();

		int result = dao.updateNoticeFl(conn, noticeNo);

		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		
		return result;
	}
	
	
//	크로스 사이트 스크립팅 
//	웹 애플리케이션에서 많이 나타나는 보안 취약점 중 하나로
//	웹 사이트 관리자가 아닌 사용자가 웹페이지에 악성 스크립트를 삽입할 수 있는 취약점

//	크로스 사이트 스크립팅 방지 메소드
	private String replaceParameter(String param) {

		String result = param;

		if (param != null) {
			result = result.replaceAll("&", "&amp;");
			result = result.replaceAll("<", "&lt;");
			result = result.replaceAll(">", "&gt;");
			result = result.replaceAll("\"", "&quot;");

		}

		return result;

	}

}
