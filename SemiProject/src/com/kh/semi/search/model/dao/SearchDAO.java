package com.kh.semi.search.model.dao;

import static com.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kh.semi.freeBoard.model.vo.FreeBoard;
import com.kh.semi.freeBoard.model.vo.FreePageInfo;

public class SearchDAO {
	
	

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	
	
	/** 조건 만족하는 게시글 수
	 * @param conn
	 * @param condition
	 * @return fListCount
	 * @throws Exception
	 */
	public int getfListCount(Connection conn, String condition)throws Exception {
		
		int fListCount = 0;
		
		
		// 넘어온 condition으로 조합
		String query = "SELECT COUNT(*) FROM FREE_BOARD WHERE FR_FL ='N' AND" + condition ;
		
		
		try {
			stmt =conn.createStatement();
			
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				fListCount = rset.getInt(1);
				
			}
			
		}finally {
			close(rset);
			close(stmt);
		}
		return fListCount;
	}



	/** 검색 게시글 목록 조회
	 * @param conn
	 * @param fPInfo
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<FreeBoard> searchFBoardList(Connection conn, FreePageInfo fPInfo, String condition)throws Exception {
		
		
		List<FreeBoard> fList = null;
		
		String query 
				= "SELECT * FROM" + 
						"    (SELECT ROWNUM RNUM , V.*" + 
						"    FROM" + 
						"        (SELECT * FROM FREE_BOARD " + 
						"        WHERE " + condition +
						"        AND FR_FL = 'N' ORDER BY FR_NO DESC) V )" + 
						"WHERE RNUM BETWEEN ? AND ?";
		
		
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
