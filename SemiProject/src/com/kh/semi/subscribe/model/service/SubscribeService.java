package com.kh.semi.subscribe.model.service;
import static com.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.semi.subscribe.model.dao.SubscribeDAO;

public class SubscribeService {

	
	private SubscribeDAO dao = new SubscribeDAO();
	
	/** 카테고리별 구독 서비스를 가져오는 service
	 * @param category 
	 * @return category
	 * @throws Exception
	 */
	public List<String> getService(String category) throws Exception {
		Connection conn = getConnection();
		List<String> service = dao.getService(conn, category);
		close(conn);
		return service;
	}

}
