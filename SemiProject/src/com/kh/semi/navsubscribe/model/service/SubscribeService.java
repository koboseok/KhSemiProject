package com.kh.semi.navsubscribe.model.service;

import static com.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.semi.navsubscribe.model.dao.SubscribeDAO;
import com.kh.semi.navsubscribe.model.vo.Subscribe;
import com.kh.semi.subscribereply.model.vo.SubscribeReply;

public class SubscribeService {

	private SubscribeDAO dao = new SubscribeDAO();

	public List<Subscribe> selectConList() throws Exception {
		Connection conn = getConnection();

		List<Subscribe> conList = dao.selectConList(conn);
		close(conn);
		return conList;
	}

	public List<Subscribe> selectLifeList() throws Exception {
		Connection conn = getConnection();

		List<Subscribe> lifeList = dao.selectLifeList(conn);
		close(conn);
		return lifeList;
	}

	public List<Subscribe> selectNewsList() throws Exception {
		Connection conn = getConnection();

		List<Subscribe> newsList = dao.selectNewsList(conn);
		close(conn);
		return newsList;
	}

	public List<Subscribe> selectFoodList() throws Exception {
		Connection conn = getConnection();

		List<Subscribe> foodList = dao.selectFoodList(conn);
		close(conn);
		return foodList;
	}

	public Subscribe selectInfo(String subName) throws Exception {
		Connection conn = getConnection();

		Subscribe subscribe = dao.selectInfo(conn, subName);

		close(conn);

		return subscribe;
	}
	
	
	public List<Subscribe> extendSubscribe(String conName) throws Exception {
		Connection conn = getConnection();

		List<Subscribe> exList = dao.extendSubscribe(conn, conName);
		close(conn);
		return exList;
	}


	public SubscribeReply selectReplyInfo(String reply) throws Exception {
		Connection conn = getConnection();

		SubscribeReply replyInfo = dao.selectReplyInfo(conn, reply);

		close(conn);

		return replyInfo;
	}


	


}
