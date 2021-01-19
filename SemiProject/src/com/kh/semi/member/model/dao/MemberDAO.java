package com.kh.semi.member.model.dao;
import static com.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

import com.kh.semi.member.model.vo.Member;

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

	/**구독서비스 목록 입력 DAO
	 * @param conn
	 * @param service
	 * @param memNo 
	 * @return result
	 * @throws Exception
	 */
	public int insertMemSub(Connection conn, Map<String, Object> service, int memNo) throws Exception {
		int result = 0;
		String query = prop.getProperty("insertMemSub");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memNo);
			
		} finally {

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
		System.out.println("DAO" + loginMember);
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
}
