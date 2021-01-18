
package com.kh.semi.freeBoard.model.dao;

import static com.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.semi.freeBoard.model.dao.FreeBoardDAO;
import com.kh.semi.freeBoard.model.vo.FreeBoard;
import com.kh.semi.freeBoard.model.vo.FreePageInfo;


public class FreeBoardDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;

	private Properties prop = null;

	// 기본 생성자
	public FreeBoardDAO() {
		String fileName = FreeBoardDAO.class.getResource(

				"/com/kh/semi/sql/board/freeBoard-query.xml").getPath();
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 자유 게시판 글 수 반환DAO
	 * 
	 * @param conn
	 * @return fListCount
	 * @throws Exception
	 */

	public int getfListCount(Connection conn) throws Exception {

		int fListCount = 0;

		String query = prop.getProperty("fListCount");

		try {

			stmt = conn.createStatement();

			rset = stmt.executeQuery(query);

			if (rset.next()) {
				fListCount = rset.getInt(1);
			}

		} finally {

			close(stmt);

		}

		return fListCount;
	}

	/** 목록  조회 DAO
	 * @param conn
	 * @param fPInfo
	 * @return
	 */
	public List<FreeBoard> selectFBoardList(Connection conn, FreePageInfo fPInfo) throws Exception {

		List<FreeBoard> fList = null;

		String query = prop.getProperty("selectFBoardList");





		try {
			// SQL 구문 조건절에 대입할 변수 생성
			int startRow = (fPInfo.getCurrentPage() - 1) * fPInfo.getLimit() + 1; 
			int endRow = startRow + fPInfo.getLimit() - 1; 

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			fList = new ArrayList<FreeBoard>();


			while (rset.next()) {
				FreeBoard fBoard = new FreeBoard();

				fBoard.setfBoardNo(rset.getInt("FR_NO"));
				fBoard.setfBoardTitle(rset.getString("FR_TITLE"));
				fBoard.setMemName(rset.getString("MEM_NM"));
				fBoard.setfCreateDate(rset.getTimestamp("FR_C_DT"));
				fBoard.setfReadCount(rset.getInt("FR_READ_COUNT"));
				fBoard.setMemGrade(rset.getString("MEM_GRADE"));
				fList.add(fBoard);
			
			
			}
			
			
		}finally {

			close(rset);
			close(pstmt);


		}

		return fList;
	}
}



