package com.kh.semi.subscribe.model.service;
import static com.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.semi.subscribe.model.DAO.SubscribeDAO;

public class SubscribeService {

	
	private SubscribeDAO dao = new SubscribeDAO();
	
	/** 구독 서비스 카테고리를 가져오는 service
	 * @return category
	 * @throws Exception
	 */
	public List<String> getCategory() throws Exception {
		Connection conn = getConnection();
		List<String> category = dao.getCategory(conn);
		close(conn);
		return category;
	}

}
