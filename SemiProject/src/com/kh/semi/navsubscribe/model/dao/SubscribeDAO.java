package com.kh.semi.navsubscribe.model.dao;

import java.io.FileInputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.semi.navsubscribe.model.dao.SubscribeDAO;
import com.kh.semi.navsubscribe.model.vo.Subscribe;
import com.kh.semi.subscribereply.model.vo.SubscribeReply;

import static com.kh.semi.common.JDBCTemplate.*;

public class SubscribeDAO {

//	자주사용하는 JDBC 참조 변수 미리 선언 
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;

//	외부 XML 파일에 작성된 SQL을 읽어올 변수를 선언
	Properties prop = null;

//	기본생성자로 NoticeDAO 객체 생성 시 SQL이 작성된 xml파일 얻어오기
	public SubscribeDAO() {
		String fileName = SubscribeDAO.class.getResource("/com/kh/semi/sql/subscribe/subscribe.xml").getPath();
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Subscribe> selectConList(Connection conn) throws Exception {
		List<Subscribe> conList = null;

		String query = prop.getProperty("selectConList");

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			conList = new ArrayList<Subscribe>();

			while (rset.next()) {
				Subscribe subscribe = new Subscribe();

				subscribe.setSubName(rset.getString("SERV_NM"));
				subscribe.setSubImage(rset.getString("SERV_IMG"));

				conList.add(subscribe);
			}

		} finally {
			close(rset);
			close(stmt);
		}

		return conList;
	}

	public List<Subscribe> selectLifeList(Connection conn) throws Exception {
		List<Subscribe> lifeList = null;

		String query = prop.getProperty("selectLifeList");

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			lifeList = new ArrayList<Subscribe>();

			while (rset.next()) {
				Subscribe subscribe = new Subscribe();

				subscribe.setSubName(rset.getString("SERV_NM"));
				subscribe.setSubImage(rset.getString("SERV_IMG"));

				lifeList.add(subscribe);
			}

		} finally {
			close(rset);
			close(stmt);
		}

		return lifeList;
	}

	public List<Subscribe> selectNewsList(Connection conn) throws Exception {
		List<Subscribe> newsList = null;

		String query = prop.getProperty("selectNewsList");

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			newsList = new ArrayList<Subscribe>();

			while (rset.next()) {
				Subscribe subscribe = new Subscribe();

				subscribe.setSubName(rset.getString("SERV_NM"));
				subscribe.setSubImage(rset.getString("SERV_IMG"));

				newsList.add(subscribe);
			}

		} finally {
			close(rset);
			close(stmt);
		}

		return newsList;
	}

	public List<Subscribe> selectFoodList(Connection conn) throws Exception {
		List<Subscribe> foodList = null;

		String query = prop.getProperty("selectFoodList");

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			foodList = new ArrayList<Subscribe>();

			while (rset.next()) {
				Subscribe subscribe = new Subscribe();

				subscribe.setSubName(rset.getString("SERV_NM"));
				subscribe.setSubImage(rset.getString("SERV_IMG"));

				foodList.add(subscribe);
			}

		} finally {
			close(rset);
			close(stmt);
		}

		return foodList;
	}

	public Subscribe selectInfo(Connection conn, String subName) throws Exception {
		Subscribe subscribe = null;

		String query = prop.getProperty("selectInfo");

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, subName);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				subscribe = new Subscribe();
				subscribe.setSubName(rset.getString("SERV_NM"));
				subscribe.setSubImage(rset.getString("SERV_IMG"));
				subscribe.setCategoryName(rset.getString("SERV_CATEGORY"));
				subscribe.setSubContent(rset.getString("SERV_CONTENT"));
			}

		} finally {
			close(rset);
			close(pstmt);

		}

		return subscribe;

	}	
	
	
	public List<Subscribe> extendSubscribe(Connection conn, String conName) throws Exception {
		List<Subscribe> exList = null;

		String query = prop.getProperty("extendSubscribe");

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, conName);
			rset = pstmt.executeQuery();
			
			exList = new ArrayList<Subscribe>();

			while (rset.next()) {
				Subscribe conSubscribe = new Subscribe();

				conSubscribe.setSubNo(rset.getInt("SERV_CODE"));
				conSubscribe.setSubName(rset.getString("SERV_NM"));
				conSubscribe.setSubImage(rset.getString("SERV_IMG"));

				exList.add(conSubscribe);
			}

		} finally {
			close(rset);
			close(pstmt);
		}

		return exList;
	}

	public SubscribeReply selectReplyInfo(Connection conn, String reply) throws Exception {
		SubscribeReply replyInfo = null;

		String query = prop.getProperty("selectReplyInfo");

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, reply);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				replyInfo = new SubscribeReply();
				replyInfo.setPoint(rset.getFloat("ROUND(AVG(SERV_POINT),2)"));
				replyInfo.setMemberNo(rset.getInt("MEM_NO"));
			}

		} finally {
			close(rset);
			close(pstmt);

		}

		return replyInfo;
	}


	



}
