package com.kh.semi.member.model.dao;
import static com.kh.semi.common.JDBCTemplate.*;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kh.semi.admin.model.vo.Report;
import com.kh.semi.freeBoard.model.vo.Board;
import com.kh.semi.freeReply.model.vo.FreeReply;
import com.kh.semi.jointReply.model.vo.JointReply;
import com.kh.semi.member.model.vo.MemSubscribe;
import com.kh.semi.member.model.vo.Member;
import com.kh.semi.privateReply.model.vo.PrivateReply;

public class MemberDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;

	private Properties prop = null;

	public MemberDAO() {
		try {
			String filePath = MemberDAO.class.getResource("/com/kh/semi/sql/member/member-query.xml").getPath();
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));
		} catch(Exception e) {
			e.printStackTrace();
		}

	}


	/**회원 가입 DAO
	 * @param conn
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Connection conn, Member member) throws Exception {
		int result = 0;

		String query = prop.getProperty("signUp");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemEmail());
			pstmt.setString(2, member.getMemPwd());
			pstmt.setString(3, member.getMemName());
			pstmt.setString(4, member.getMemPhone());

			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);

		}
		return result;
	}

	/** 비밀번호 검사 DAO
	 * @param conn
	 * @param loginMember
	 * @return result
	 * @throws Exception
	 */
	public int checkCurrentPwd(Connection conn, Member loginMember) throws Exception {
		int result = 0;

		String query = prop.getProperty("checkCurrentPwd");

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, loginMember.getMemNo());
			pstmt.setString(2, loginMember.getMemPwd());

			rset = pstmt.executeQuery();

			if(rset.next()) {
				result = rset.getInt(1);
			}

		} finally {
			close(rset);
			close(pstmt);

		}

		return result;
	}


	/** 비밀번호 변경 DAO
	 * @param conn
	 * @param loginMember
	 * @return
	 * @throws Exception
	 */
	public int updatePwd(Connection conn, Member loginMember) throws Exception {
		int result = 0;

		String query = prop.getProperty("updatePwd");

		try {

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, loginMember.getMemPwd());
			pstmt.setInt(2, loginMember.getMemNo());

			result = pstmt.executeUpdate();



		}finally {

			close(pstmt);

		}
		return result;
	}


	/** 회원탈퇴 DAO
	 * @param conn
	 * @param loginMember
	 * @return	result
	 * @throws Exception
	 */
	public int updateStatus(Connection conn, Member loginMember) throws Exception {

		int result = 0;

		String query = prop.getProperty("updateStatus");

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, loginMember.getMemPwd());
			pstmt.setInt(2, loginMember.getMemNo());

			result = pstmt.executeUpdate();

		}finally {
			close(pstmt);

		}

		return result;
	}


	/** 로그인 DAO
	 * @param conn
	 * @param member
	 * @return loginMember
	 * @throws Exception
	 */
	public Member loginMember(Connection conn, Member member) throws Exception {
		//		결과 저장용 변수 선언
		Member loginMember = null;

		String query = prop.getProperty("loginMember");

		try {
			//			1) PreparedStatement 객체를 얻어와 query 세팅

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemEmail());
			pstmt.setString(2, member.getMemPwd());

			//			3) SQL 수행 후 결과를 반환받아 저장
			rset = pstmt.executeQuery();


			if (rset.next()) {
				loginMember = new Member(rset.getInt("MEM_NO"),rset.getString("MEM_EMAIL"),
						rset.getString("MEM_NM"),
						rset.getString("MEM_PHONE"),rset.getString("MEM_GRADE"),
						rset.getString("MEM_STATUS"),rset.getString("MEM_CHECK"),
						rset.getDate("MEM_ENROLL_DATE"));
			}

		} finally {
			//			사용한 DB 자원 반환
			close(rset);
			close(pstmt);

		}
		//System.out.println("DAO" + loginMember);
		return loginMember;
	}


	/** 이메일 중복검사 DAO
	 * @param conn
	 * @param email
	 * @return result
	 * @throws Exception
	 */
	public int emailDupCheck(Connection conn, String email) throws Exception {
		int result = 0;
		String query = prop.getProperty("emailDupCheck");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);
			rset = pstmt.executeQuery();

			if(rset.next()) result = rset.getInt(1);

		} finally {
			close(rset);
			close(pstmt);
		}

		return result;
	}


	/** 별명 중복 검사 DAO
	 * @param conn
	 * @param name
	 * @return result
	 * @throws Exception
	 */
	public int nameDupCheck(Connection conn, String name) throws Exception {
		int result = 0;
		String query = prop.getProperty("nameDupCheck");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			rset = pstmt.executeQuery();
			if(rset.next()) result = rset.getInt(1);
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;

	}

	/** 서비스명으로 서비스 코드를 찾아오는 DAO
	 * @param conn
	 * @param serviceName
	 * @return servCode
	 * @throws Exception
	 */
	public int getServCode(Connection conn, String serviceName) throws Exception {
		int servCode = 0;
		String query = prop.getProperty("getServCode");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, serviceName);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				servCode = rset.getInt("SERV_CODE");
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return servCode;
	}


	/**가입 성공시 이메일로 회원 번호를 가져오는 DAO
	 * @param conn
	 * @param memEmail
	 * @return memNo
	 * @throws Exception
	 */
	public int getMemNo(Connection conn, String memEmail) throws Exception {
		int memNo = 0;
		String query = prop.getProperty("getMemNo");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memEmail);
			rset = pstmt.executeQuery();
			
			if(rset.next()) memNo = rset.getInt(1);
		} finally {
			close(rset);
			close(pstmt);
		}
		return memNo;
	}



	/** 가입 시 구독목록 등록 DAO
	 * @param conn
	 * @param list
	 * @return result
	 * @throws Exception
	 */
	public int insertMemSub(Connection conn, List<MemSubscribe> list) throws Exception {
		int result = 0;
		String query = prop.getProperty("insertMemSub");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			for (MemSubscribe MemSub : list) {
		        pstmt.setInt(1, MemSub.getMemNo());
		        pstmt.setInt(2, MemSub.getServCode());
		        pstmt.setDate(3, MemSub.getServStDate());
		        pstmt.setInt(4, MemSub.getPrice());
		        pstmt.setDate(5, MemSub.getServStDate());
		        pstmt.addBatch();
		        pstmt.clearParameters();
		      }

			int[] resultGroup = pstmt.executeBatch(); // 성공 행 개수 , 성공은 했는데 몇개인지 모름 (-2), 실패 (-3)
		    pstmt.clearParameters(); 

			for(int i : resultGroup) {
				result += i;
			}
			
			if(result / (-2) == list.size()) {
				result = 1;
			}
			
			System.out.println(result);
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	/** 정지회원 사유 출력 DAO
	 * @param conn
	 * @param memNo
	 * @return report
	 * @throws Exception
	 */
	public Report getReportReason(Connection conn, int memNo) throws Exception {
		Report report = new Report();
		String query = prop.getProperty("getReportReason");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				report.setReportDt(rset.getDate(1));
				report.setReportReason(rset.getString(2));
			}
			
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return report;
	}


	/** 회원정보 수정 DAO
	 * @param conn
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Connection conn, Member member) throws Exception {
		int result = 0;
		
		String query = prop.getProperty("updateMember");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, member.getMemName());
			pstmt.setString(2, member.getMemPhone());
			pstmt.setInt(3, member.getMemNo());
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}


	public List<Board> selectfBoardList(Connection conn, int memNo) throws Exception{
		
		List<Board> fList = null;
		
		
		String query = prop.getProperty("selectfBoardList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memNo);
			
			rset = pstmt.executeQuery();
			
			fList = new ArrayList<Board>() ;
			
			while(rset.next()) {
				fList.add(new Board(rset.getInt("BOARD_NO"), rset.getString("BOARD_TITLE"),
						rset.getString("MEM_NM"), rset.getInt("READ_COUNT"), rset.getTimestamp("BOARD_CREATE_DT")));
			}
			
		}finally {
			close(rset);
			close(pstmt);
			
			
		}
		return fList;
	}


	public List<com.kh.semi.jointBoard.model.vo.Board> selectjBoardList(Connection conn, int memNo) throws Exception{
		
		List<com.kh.semi.jointBoard.model.vo.Board> jList = null;
		
		
		String query = prop.getProperty("selectjBoardList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memNo);
			
			rset = pstmt.executeQuery();
			
			jList = new ArrayList<com.kh.semi.jointBoard.model.vo.Board>() ;
			
			while(rset.next()) {
				jList.add(new com.kh.semi.jointBoard.model.vo.Board(rset.getInt("BOARD_NO"), 
						rset.getString("BOARD_TITLE"), rset.getString("MEM_NM"),
						rset.getInt("READ_COUNT"), rset.getString("JT_CT_NM"), rset.getTimestamp("BOARD_CREATE_DT")));
			}
			
		}finally {
			close(rset);
			close(pstmt);
			
			
		}
		
		
		return jList;
	}


	/** 비공개 게시글 조회 
	 * @param conn
	 * @param memNo
	 * @return
	 * @throws Exception
	 */
	public List<com.kh.semi.privateBoard.model.vo.Board> selectpBoardList(Connection conn, int memNo) throws Exception {
		
		List<com.kh.semi.privateBoard.model.vo.Board> pList = null;
		
		String query = prop.getProperty("selectpBoardList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memNo);
			
			rset = pstmt.executeQuery();
			
			pList = new ArrayList<com.kh.semi.privateBoard.model.vo.Board>() ;
			
			while(rset.next()) {
				pList.add(new com.kh.semi.privateBoard.model.vo.Board(rset.getInt("BOARD_NO"), 
						rset.getString("BOARD_TITLE"), rset.getString("MEM_NM"), rset.getInt("READ_COUNT"),
						rset.getString("PRIV_CT_NM"), rset.getTimestamp("BOARD_CREATE_DT")));
			}
			
		}finally {
			close(rset);
			close(pstmt);
			
			
		}
		
		
		return pList;
	}


	/** 자유게시판 댓글 조회
	 * @param conn
	 * @param memNo
	 * @return
	 * @throws Exception
	 */
	public List<FreeReply> selectfReplyList(Connection conn, int memNo) throws Exception {
		
		List<FreeReply> fReply = null;
		

		String query = prop.getProperty("selectfReplyList");
		
		
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, memNo);
			
			rset = pstmt.executeQuery();
			
			fReply = new ArrayList<FreeReply>();
			
			while(rset.next()) {
				fReply.add(new FreeReply(rset.getInt("REPLY_NO"), rset.getString("REPLY_CONTENT"),
									rset.getTimestamp("REPLY_CREATE_DT")));
			}
			
			
			
		}finally {
			close(rset);
			close(pstmt);
			
		}
		
		
		
		return fReply;
	}


	/** 공동구매 게시판 댓글 조회
	 * @param conn
	 * @param memNo
	 * @return
	 * @throws Exception
	 */
	public List<JointReply> selectjReplyList(Connection conn, int memNo) throws Exception {
		List<JointReply> jReply = null;
		
		String query = prop.getProperty("selectjReplyList");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, memNo);
			
			rset = pstmt.executeQuery();
			
			jReply = new ArrayList<JointReply>();
			
			while(rset.next()) {
				jReply.add(new JointReply(rset.getInt("REPLY_NO"), rset.getString("REPLY_CONTENT"),
									rset.getTimestamp("REPLY_CREATE_DT")));
			}
			
		}finally {
			close(rset);
			close(pstmt);
			
		}
		
		
		return jReply;
	}


	/** 비공개 건의 게시판 댓글 조회
	 * @param conn
	 * @param memNo
	 * @return
	 * @throws Exception
	 */
	public List<PrivateReply> selectpReplyList(Connection conn, int memNo) throws Exception {
	List<PrivateReply> pReply = null;
		
		String query = prop.getProperty("selectpReplyList");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, memNo);
			
			rset = pstmt.executeQuery();
			
			pReply = new ArrayList<PrivateReply>();
			
			while(rset.next()) {
				pReply.add(new PrivateReply(rset.getInt("REPLY_NO"), rset.getString("REPLY_CONTENT"),
									rset.getTimestamp("REPLY_CREATE_DT")));
			}
			
		}finally {
			close(rset);
			close(pstmt);
			
		}
		
		
		return pReply;
	}


	public int selectfBoardNo(Connection conn, String boardName ,int replyNo) throws Exception{
		
		int boardNo = 0;
		
		String query = null;
		
		switch (boardName) {
			case "freeBoard" :  query = prop.getProperty("selectfBoardNo"); break;
			case "jointBoard" :  query = prop.getProperty("selectjBoardNo"); break;
			case "privateBoard" :  query = prop.getProperty("selectpBoardNo"); break;
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, replyNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				boardNo = rset.getInt(1);
				
			}
			
		}finally {
			
			close(rset);
			close(pstmt);
			
		}
		
		return boardNo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
