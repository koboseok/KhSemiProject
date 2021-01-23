package com.kh.semi.jointBoard.search.model.service;
import static com.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.semi.jointBoard.model.vo.Attachment;
import com.kh.semi.jointBoard.model.vo.Board;
import com.kh.semi.jointBoard.model.vo.PageInfo;
import com.kh.semi.jointBoard.search.model.dao.JointBoardSearchDAO;

public class JointBoardSearchService {
	private JointBoardSearchDAO dao = new JointBoardSearchDAO();

	/**검색 내용이 포함된 페이징 처리 정보 생성 Service
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PageInfo getPageInfo(Map<String, Object> map) throws Exception {
		Connection conn = getConnection();
		
		map.put("currentPage", 
				(map.get("currentPage") == null) ? 1
						: Integer.parseInt((String)map.get("currentPage")));
		
		String condition = createCondition(map);
		
		
		int listCount = dao.getListCount(conn, condition);
		
		close(conn);
		
		return new PageInfo((int)map.get("currentPage"), listCount);
	}

	/**검색 조건에 따라 SQL에 사용될 조건문을 조합하는 메소드
	 * @param map
	 * @return condition
	 */
	private String createCondition(Map<String, Object> map) {
		
		String condition = null;
		
		String searchKey = (String)map.get("searchKey");
		String searchValue = (String)map.get("searchValue");
		

		switch(searchKey) {
		case "title" : 
			condition = " BOARD_TITLE LIKE '%' || '" + searchValue + "' || '%' ";
			break;

		case "content" : 
			condition = " BOARD_CONTENT LIKE '%' || '" + searchValue + "' || '%' ";
			break;

		case "titcont" : 
			condition = " (BOARD_TITLE LIKE '%' || '" + searchValue + "' || '%' "
						+ "OR BOARD_CONTENT LIKE '%' || '" + searchValue + "' || '%') ";
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
	   public List<Board> searchBoardList(Map<String, Object> map, PageInfo pInfo) throws Exception {
	      
	      Connection conn = getConnection();
	      
	      String condition = createCondition(map);
	      
	      List<Board> bList = dao.searchBoardList(conn, pInfo, condition);
	      
	      close(conn);
	      
	      
	      return bList;
	   }

	/**검색이 적용될 썸네일 목록 조회 service
	 * @param map
	 * @param pInfo
	 * @return
	 * @throws Exception
	 */
	public List<Attachment> searchThumbnailList(Map<String, Object> map, PageInfo pInfo) throws Exception {
		Connection conn = getConnection();
		

		String condition = createCondition(map);
		
		List<Attachment> fList = dao.searchThumbnailList(conn, pInfo, condition);
		
		close(conn);
		
		return fList;
	}
	
	
	
}


