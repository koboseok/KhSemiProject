package com.kh.semi.search.model.service;

import static com.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kh.semi.freeBoard.model.vo.FRAttachment;
import com.kh.semi.freeBoard.model.vo.FreeBoard;
import com.kh.semi.freeBoard.model.vo.FreePageInfo;
import com.kh.semi.search.model.dao.SearchDAO;


import sun.font.CreatedFontTracker;

public class SearchService {

	private SearchDAO dao = new SearchDAO();

	/** 검색 내용 포함 페이징 처리 정보 생성
	 * @param map
	 * @return fPInfo
	 * @throws Exception
	 */
	public FreePageInfo getPageInfo(HashMap<String, Object> map)throws Exception {

		Connection conn =getConnection();

		map.put("currentPage",(map.get("currentPate") == null) 
				? 1 : Integer.parseInt((String)map.get("currentPate")));



		//검색 조건에 따라 조건문 조합 메소드
		String condition = createCondition(map);




		// 조건을 만족하는 게시글 수 조회
		int fListCount = dao.getfListCount(conn, condition);

		close(conn);


		return new FreePageInfo((int)map.get("currentPage"), fListCount);
	}
	/** 검색 게시물 목록 조회
	 * @param map
	 * @param fPInfo 
	 * @return fList
	 * @throws Exception
	 */
	public List<FreeBoard> searchFBoardList(HashMap<String, Object> map, FreePageInfo fPInfo)throws Exception {
		
		Connection conn = getConnection();
		
		String condition = createCondition(map);
		
		List<FreeBoard> fList = dao.searchFBoardList(conn, fPInfo, condition);	
		
		close(conn);
		return fList;
	}

	/** 검색 조건에 따라 조건문을 조합  
	 * @param map
	 * @return
	 */
	private String createCondition(Map<String, Object> map) {


		String condition = null;

		String searchKey = (String)map.get("searchKey");
		String searchValue = (String)map.get("searchValue");


		//option에 있는 value값 사용
		switch(searchKey) {
		case "title" :
			condition = " FR_TITLE LIKE '%' || '" + searchValue + "' || '%' ";

			break;

		case "content" :
			condition = " FR_CONTENT LIKE '%' || '" + searchValue + "' || '%' ";

			break;	

		case "titcont" :
			condition = " (FR_TITLE LIKE '%' || '" + searchValue + "' || '%' "
					+ " OR FR_CONTENT LIKE '%' || '" + searchValue +  "' || '%') "  ;

			break;	

		case "writer" :
			condition = " MEM_NM LIKE '%' || '" + searchValue + "' || '%' ";

			break;	

		} 


		return condition;
	}

	
	
	/** 검색 게시글 목록 리스트 조회 Service
	 * @param map
	 * @param pInfo 
	 * @return bList
	 * @throws Exception
	 */
	public List<FreeBoard> searchBoardList(Map<String, Object> map, FreePageInfo fPInfo) throws Exception {
		
		Connection conn = getConnection();
		
		String condition = createCondition(map);
		
		List<FreeBoard> fList = dao.searchFBoardList(conn, fPInfo, condition);
		
		close(conn);
		
		return fList;
	}
	
	
	/** 검색이 적용된 썸네일 목록 조회 Service
	 * @param map
	 * @param fPInfo
	 * @return fPList
	 * @throws Exception
	 */
	public List<FRAttachment> searchThumbnailList(Map<String, Object> map, FreePageInfo fPInfo) throws Exception{
		
		
		Connection conn = getConnection();
		
		// 검색에 사용됳 SQL 조건문 생성
		String condition = createCondition(map);
		
		List<FRAttachment> fileList = dao.searchThumbnailList(conn, fPInfo, condition);
		
		close(conn);
		
		return fileList;
	}
	
	





}
