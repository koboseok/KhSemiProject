package com.kh.semi.subscribereply.model.dao;

import static com.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.semi.subscribereply.model.vo.SubscribeReply;
import com.kh.semi.subscribe.model.dao.SubscribeDAO;

public class SubscribeReplyDAO {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	private Properties prop;

	public SubscribeReplyDAO() {

		String fileName = SubscribeDAO.class.getResource("/com/kh/semi/sql/subscribereply/subscribereply.xml")
				.getPath();
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<SubscribeReply> selectList(Connection conn, String parentSubscribe) throws Exception {

		List<SubscribeReply> srList = null;

		String query = prop.getProperty("selectList");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, parentSubscribe);

			rset = pstmt.executeQuery();

			srList = new ArrayList<SubscribeReply>();

			while (rset.next()) {
				SubscribeReply sReply = new SubscribeReply();
				sReply.setReplyNo(rset.getInt("SERV_NO"));
				sReply.setReplyContent(rset.getString("SERV_RE_CONTENT"));
				sReply.setPoint(rset.getInt("SERV_POINT"));
				sReply.setMemberNo(rset.getInt("MEM_NO"));
				sReply.setMemberName(rset.getString("MEM_NM"));

				srList.add(sReply);
			}

		} finally {
			close(rset);
			close(pstmt);
		}

		return srList;
	}

	public int insertReply(Connection conn, SubscribeReply inReply) throws Exception {
		int result = 0;

		String query = prop.getProperty("insertReply");

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, inReply.getReplyContent());
			pstmt.setFloat(2, inReply.getPoint());
			pstmt.setString(3, inReply.getServiceName());
			pstmt.setInt(4, inReply.getMemberNo());

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);

		}
		return result;
	}

	public int updateReply(Connection conn, SubscribeReply upReply) throws Exception {
		int result = 0;

		String query = prop.getProperty("updateReply");

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, upReply.getReplyContent());
			pstmt.setInt(2, upReply.getReplyNo());

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}

	public int updateReplyStatus(Connection conn, int ReplyNo) throws Exception {
		int result = 0;

		String query = prop.getProperty("updateReplyStatus");

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, ReplyNo);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;

	}

}
