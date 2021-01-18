package com.kh.semi.admin.model.dao;
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

import com.kh.semi.admin.model.vo.PageInfo;
import com.kh.semi.member.model.vo.Member;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class AdminDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	private Properties prop = null;
	
	public AdminDAO() {
		String fileName = AdminDAO.class.getResource("/com/kh/semi/sql/admin/admin-query.xml").getPath();
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(fileName));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	
	}
	
	/**전체 회원 수 반환 DAO
	 * @param conn
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Connection conn) throws Exception {
		int listCount = 0;
		String query = prop.getProperty("getListCount");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} finally {
			close(rset);
			close(stmt);
		}
	
		return listCount;
	}

	/**회원 목록 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @return mList
	 * @throws Exception
	 */
	public List<Member> selectMemberList(Connection conn, PageInfo pInfo) throws Exception {
		List<Member> mList = null;
		String query = prop.getProperty("selectMemberList");
		
		try {
			int startRow = (pInfo.getCurrentPage() -1) * pInfo.getLimit()+1;
			int endRow = startRow + pInfo.getLimit()-1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			mList = new ArrayList<Member>();
			
			while(rset.next()) {
				Member member = new Member(rset.getInt("MEM_NO"),
											rset.getString("MEM_EMAIL"),
											rset.getString("MEM_NM"),
											rset.getString("MEM_PHONE"));
				mList.add(member);
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return mList;
	}
}
