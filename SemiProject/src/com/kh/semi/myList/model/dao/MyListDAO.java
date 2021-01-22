package com.kh.semi.myList.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.kh.semi.myList.model.vo.MyList;

import static com.kh.semi.common.JDBCTemplate.*;

public class MyListDAO {
	
//	자주사용하는 JDBC 참조 변수 미리 선언 
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
//	외부 XML 파일에 작성된 SQL을 읽어올 변수를 선언
	Properties prop = null;
	
//	기본생성자로 NoticeDAO 객체 생성 시 SQL이 작성된 xml파일 얻어오기
	public MyListDAO() {
		String fileName = MyListDAO.class.getResource("/com/kh/semi/sql/myList/myList-query.xml").getPath();
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(fileName)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 마이리스트 조회
	 * @param conn
	 * @param memNo
	 * @return list
	 * @throws Exception
	 */
	public List<MyList> selectList(Connection conn, int memNo) throws Exception{
		
		List<MyList> list = null;
		
		String query = prop.getProperty("selectList");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,memNo);
			
			rset = pstmt.executeQuery();
			
			
			list = new ArrayList<MyList>();
			while(rset.next()) {
				list.add(new MyList(rset.getInt("MEM_NO"), rset.getString("SERV_CODE"), 
									rset.getString("SERV_NM"), rset.getDate("SUB_ST_DT"), 
									rset.getDate("SUB_END_DT"), rset.getInt("PRICE"), rset.getString("SERV_IMG")));
			}
			
			
		}finally {
			close(rset);
			close(pstmt);
			
		}
		
		return list;
	}

	/** 마이 리스트 추가
	 * @param conn
	 * @param addList
	 * @return result
	 * @throws Exception
	 */
	public int insertMyList(Connection conn, MyList addList) throws Exception{
		
		int result = 0;

		String query = prop.getProperty("insertMyList");
		try {
			pstmt =conn.prepareStatement(query);
			
			pstmt.setInt(1, addList.getMemNo());
			pstmt.setString(2, addList.getServCode());
			pstmt.setDate(3, addList.getStartDt());
			pstmt.setInt(4, addList.getPrice());
			pstmt.setDate(5,addList.getStartDt());
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			close(pstmt);
		}
		return result;
	}

	/** 마이 리스트 삭제 
	 * @param conn
	 * @param delMyList
	 * @return result
	 * @throws Exception
	 */
	public int deleteMyList(Connection conn, int memNo , String ServCode) throws Exception {
		int result = 0;
		
		String query = prop.getProperty("deleteMyList");
		
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, memNo);
			pstmt.setString(2, ServCode);
			
			result = pstmt.executeUpdate();
			
			
			
			
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int updateMyList(Connection conn, MyList updateList , String servCode) throws Exception{
		int result = 0;

		String query = prop.getProperty("updateMyList");
		try {
			pstmt =conn.prepareStatement(query);
			
			pstmt.setString(1, updateList.getServCode());
			pstmt.setDate(2, updateList.getStartDt());
			pstmt.setInt(3, updateList.getPrice());
			pstmt.setDate(4,updateList.getStartDt());
			pstmt.setInt(5, updateList.getMemNo());
			pstmt.setString(6, servCode);
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

