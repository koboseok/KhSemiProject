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

	/** 마이 리스트 추가
	 * @param addList
	 * @return result
	 * @throws Exception
	 */
	public int insertMyList(MyList addList) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.insertMyList(conn, addList);
		
		if(result > 0) 	commit(conn);
		else			rollback(conn);
		
		return result;
	}

	/** 마이 리스트 삭제
	 * @param delMyList
	 * @return
	 */
	public int deleteMyList(int memNo , String servCode) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.deleteMyList(conn, memNo, servCode);
		
		if(result > 0) 	commit(conn);
		else			rollback(conn);
		
		return result;
	}

	public int updateMyList(MyList updateList , String servCode) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.updateMyList(conn, updateList , servCode);
		
		if(result > 0) 	commit(conn);
		else			rollback(conn);
		
		return result;
	}

	

}
