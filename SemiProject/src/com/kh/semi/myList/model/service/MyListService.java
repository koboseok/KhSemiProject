package com.kh.semi.myList.model.service;
import static com.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.semi.myList.model.dao.MyListDAO;
import com.kh.semi.myList.model.vo.MyList;

public class MyListService {

	private MyListDAO dao = new MyListDAO();
	
	/** 마이 리스트 조회
	 * @param memNo
	 * @return list
	 * @throws Exception
	 */
	public List<MyList> selectList(int memNo) throws Exception{
		
		Connection conn = getConnection();

		List<MyList> list = dao.selectList(conn , memNo);

		close(conn);

		return list;
	}

}
