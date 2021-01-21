package com.kh.semi.search.model.dao;

import static com.kh.semi.common.JDBCTemplate.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kh.semi.freeBoard.model.vo.FRAttachment;
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
	/** 검색이 적용된 썸네일 목록 조회DAO
	 * @param conn
	 * @param pInfo
	 * @param condition
	 * @return fList
	 * @throws Exception
	 */
	public List<FRAttachment> searchThumbnailList(Connection conn, FreePageInfo fPInfo, String condition)throws Exception {
		
		List<FRAttachment> fileList = null;
		
		
		String query = 
				"SELECT FILE_NAME, PARENT_BOARD_NO FROM ATTACHMENT " + 
				"WHERE PARENT_BOARD_NO IN (" + 
				"    SELECT BOARD_NO FROM " + 
				"    (SELECT ROWNUM RNUM, V.* FROM " + 
				"            (SELECT BOARD_NO  FROM V_BOARD " + 
				"            WHERE BOARD_STATUS='Y' " + 
				"            AND " + condition + 
				"            ORDER BY BOARD_NO DESC ) V) " + 
				"    WHERE RNUM BETWEEN ? AND ?" + 
				") " + 
				"AND FILE_LEVEL = 0";
		
		try {

			// 위치홀더에 들어갈 시작행, 끝행 번호 계산
			
			int startRow = (fPInfo.getCurrentPage() -1) * fPInfo.getLimit() + 1;
			int endRow = startRow + fPInfo.getLimit() -1;
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			
			// 조회 결과를 저장할 List 생성
			fileList = new ArrayList<FRAttachment>();
			
			
			while(rset.next()) {
				FRAttachment at = new FRAttachment();
				at.setImgName(rset.getString("FILE_NAME"));
				at.setparentBoardNo(rset.getInt("PARENT_BOARD_NO"));
				
				fileList.add(at);
			}
			
		}finally{
			
			close(rset);
			close(pstmt);
		}
			
		
		return fileList;
	}
	
}
