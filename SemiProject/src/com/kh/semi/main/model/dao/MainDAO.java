package com.kh.semi.main.model.dao;

import static com.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.semi.main.model.dao.MainDAO;
import com.kh.semi.subscribe.model.vo.Subscribe;
public class MainDAO {
	
//	자주사용하는 JDBC 참조 변수 미리 선언 
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	Properties prop = null;
	
	public MainDAO() {
		String fileName = MainDAO.class.getResource("/com/kh/semi/sql/main/main.xml").getPath();
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
public List<Subscribe> selectPopTop(Connection conn, String popName) throws Exception {
		
		List<Subscribe> popTopList = null;

		String query = prop.getProperty("selectPopTop");

		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, popName);
			rset = pstmt.executeQuery();

			popTopList = new ArrayList<Subscribe>();

			while (rset.next()) {
				Subscribe subscribe = new Subscribe();

				subscribe.setSubName(rset.getString("SERV_NM"));
				subscribe.setSubImage(rset.getString("SERV_IMG"));

				popTopList.add(subscribe);
			}

		} finally {
			close(rset);
			close(stmt);
		}

		return popTopList;
		
		
	}


}
