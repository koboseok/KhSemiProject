package com.kh.semi.admin.model.service;
import static com.kh.semi.common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.List;

import com.kh.semi.admin.model.dao.AdminDAO;
import com.kh.semi.admin.model.vo.PageInfo;
import com.kh.semi.member.model.vo.Member;

public class AdminService {

	private AdminDAO dao = new AdminDAO();

	/**페이징 처리를 위한 계산 Service
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

	/**게시글 목록 조회
	 * @param pInfo
	 * @return mList
	 * @throws Exception
	 */
	public List<Member> selectMemberList(PageInfo pInfo) throws Exception {
		Connection conn = getConnection();
		
		List<Member> mList = dao.selectMemberList(conn, pInfo);
		
		close(conn);
		
		return mList;
	}
}
