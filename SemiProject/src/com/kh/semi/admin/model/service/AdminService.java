package com.kh.semi.admin.model.service;
import static com.kh.semi.common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.semi.admin.model.dao.AdminDAO;
import com.kh.semi.admin.model.vo.PageInfo;
import com.kh.semi.admin.model.vo.Report;
import com.kh.semi.member.model.vo.Member;

public class AdminService {

	private AdminDAO dao = new AdminDAO();

	/**회원 페이징 처리를 위한 계산 Service
	 * @param cp
	 * @return pInfo
	 * @throws Exception
	 */
	public PageInfo getPageInfo(String cp) throws Exception {
		Connection conn = getConnection();
		
		int currentPage = cp == null? 1: Integer.parseInt(cp);
		
		//전체 회원 수 가져오기
		int listCount = dao.getListCount(conn);
		
		close(conn);
		
		return new PageInfo(currentPage, listCount);
	}

	/**회원 목록 조회 service
	 * @param pInfo
	 * @return mList
	 * @throws Exception
	 */
	public List<Member> selectMemberList(PageInfo pInfo) throws Exception {
		Connection conn = getConnection();
		
		List<Member> mList = dao.selectMemberList(conn, pInfo);
		mList = dao.addSubList(conn, mList);
		
		close(conn);
		
		return mList;
	}

	/** 정지회원으로 전환 + 신고 내용 입력 Service
	 * @param report
	 * @return result
	 * @throws Exception
	 */
	public int reportMember(Report report) throws Exception {
		Connection conn = getConnection();
		
		int memNo = report.getReportMemNo();
		
		//회원등급을 G에서 B로 전환
		int result = dao.updateGradeB(conn, memNo);
		
		if(result>0) {
			//report테이블에 신고 내역 입력
			result = dao.reportMember(conn, report);
		}
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	/** 불량회원 페이지 처리를 위한 계산 service
	 * @param cp
	 * @return pInfo
	 * @throws Exception
	 */
	public PageInfo getBpageInfo(String cp) throws Exception {
		Connection conn = getConnection();
		
		int currentPage = cp == null? 1: Integer.parseInt(cp);
		
		//전체 회원 수 가져오기
		int bListCount = dao.getbPageInfo(conn);
		
		close(conn);
		return new PageInfo(currentPage, bListCount);
	}
	

	/** 불량회원 목록 조회 service
	 * @param pInfo
	 * @return
	 * @throws Exception
	 */
	public List<Report> selectBmemberList(PageInfo pInfo) throws Exception {
		Connection conn = getConnection();
		
		List<Report> bmList = dao.selectBmemberList(conn, pInfo);
		
		close(conn);
		
		return bmList;
	}

	/**일반 회원으로 전환 service
	 * @param memNo
	 * @return result
	 * @throws Exception
	 */
	public int convertMember(int memNo) throws Exception {
		int result = 0;
		Connection conn = getConnection();
		
		result = dao.convertMember(conn, memNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}

	/**검색 결과 페이징 처리 Service
	 * @param map
	 * @return PInfo
	 * @throws Exception
	 */
	public PageInfo getSearchPageInfo(Map<String, Object> map) throws Exception {
		Connection conn = getConnection();
		
		map.put("currentPage", (map.get("currentPage")==null) ? 1 
				: Integer.parseInt((String)map.get("currentPage")));
		String condition = createCondition(map);
		
		int listCount = dao.getSearchListCount(conn, condition);
		
		close(conn);
		
		return new PageInfo((int)map.get("currentPage"), listCount);
	}

	/**검색 조건에 따라 SQL문에 사용될 조건문을 조합하는 메소드
	 * @param map
	 * @return
	 * @throws Exception
	 */
	private String createCondition(Map<String, Object> map) throws Exception {
		String condition = null;
		
		String searchKey = (String)map.get("searchKey");
		String searchValue = (String)map.get("searchValue");
		
		
		switch(searchKey) {
		case "memNo" : 
			condition = " MEM_NO LIKE '%' || '" + searchValue + "' || '%' ";
			break;
		case "memEmail" : 
			condition = " MEM_EMAIL LIKE '%' || '" + searchValue + "' || '%' ";
			break;
		case "memName" : 
			condition = " MEM_NM LIKE '%' || '" + searchValue + "' || '%' ";
		}
		return condition;

	}

	/**검색 회원 목록 조회 service
	 * @param map
	 * @param pInfo
	 * @return 
	 * @throws Exception
	 */
	public List<Member> searchMemberList(Map<String, Object> map, PageInfo pInfo) throws Exception {
		Connection conn = getConnection();
		
		String condition = createCondition(map);
		
		List<Member> mList = dao.searchMemberList(conn, pInfo, condition);
		mList = dao.addSubList(conn, mList);
		
		return mList;
	}




}
