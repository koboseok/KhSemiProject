package com.kh.semi.subscribe.model.DAO;
import static com.kh.semi.common.JDBCTemplate.*;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

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


	/** 구독 카테고리를 가져오는 DAO
	 * @param conn
	 * @return 
	 * @throws Exception
	 */
	public List<String> getCategory(Connection conn) throws Exception {
		List<String> category = null;
		String query = prop.getProperty("getCategory");

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while(rset.next()) {
				category.add(rset.getString("SERV_CATEGORY"));
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return category;
	}

}
