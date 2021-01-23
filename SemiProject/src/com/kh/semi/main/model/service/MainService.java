package com.kh.semi.main.model.service;
import static com.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.semi.main.model.dao.MainDAO;
import com.kh.semi.navsubscribe.model.vo.Subscribe;


public class MainService {

	private MainDAO dao = new MainDAO();
	
	public List<Subscribe> selectPopTop(String popName) throws Exception {
		Connection conn = getConnection();

		List<Subscribe> popTopList = dao.selectPopTop(conn, popName);

		close(conn);

		return popTopList;

	}
}
