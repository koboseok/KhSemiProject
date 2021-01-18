package com.kh.semi.subscribe.model.DAO;
import static com.kh.semi.common.JDBCTemplate.*;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.semi.member.model.dao.MemberDAO;


public class SubscribeDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	private Properties prop;

	public SubscribeDAO(){
		String fileName = SubscribeDAO.class.getResource("/com/kh/semi/sql/subscribe/subscribe-query.xml").getPath();
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(fileName)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/** 구독 서비스를 가져오는 DAO
	 * @param conn
	 * @param category 
	 * @return service
	 * @throws Exception
	 */
	public List<String> getService(Connection conn, String category) throws Exception {
		List<String> service = null;
		String query = prop.getProperty("getService");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, category);
			
			service = new ArrayList<String>();
			while(rset.next()) {
				service.add(rset.getString(1));
			}
			
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return service;
	}

}
