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

		String fileName = SubscribeDAO.class.getResource("/com/kh/semi/sql/subscribereply/subscribereply.xml").getPath();
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
