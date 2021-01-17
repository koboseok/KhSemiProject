package com.kh.semi.member.model.dao;
import static com.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
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
			//임시 중단
		} finally {
			
		}
		
		
		
		return 0;
	}

}
