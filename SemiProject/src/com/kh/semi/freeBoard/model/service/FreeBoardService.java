
package com.kh.semi.freeBoard.model.service;

import static com.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.semi.freeBoard.model.dao.FreeBoardDAO;
import com.kh.semi.freeBoard.model.vo.FreeBoard;
import com.kh.semi.freeBoard.model.vo.FreePageInfo;

public class FreeBoardService {

	private FreeBoardDAO dao = new FreeBoardDAO();

	/**
	 * 페이징 처리 Service (값 계산하기)
	 * 
	 * @param cp
	 * @return fPInfo
	 * @throws Exception
	 */

	public FreePageInfo getFreePageInfo(String cp) throws Exception {

		Connection conn = getConnection();

		int currentPage = cp == null ? 1 : Integer.parseInt(cp);

		// db에서 전체 게시글 수를 조회하여 반환 받기
		int fListCount = dao.getfListCount(conn);

		close(conn);

		// 현재 페이지, 전체 게시글 수 이용해여 FreePageInfo 객체 생성 // FreePageInfo에 두개의 값 전달
		return new FreePageInfo(currentPage, fListCount);
	}

	/**
	 * 게시물 목록 조회 Service
	 * 
	 * @param fPInfo
	 * @return NList
	 * @throws Exception
	 */

	public List<FreeBoard> selectFBoardList(FreePageInfo fPInfo) throws Exception{

		Connection conn = getConnection();

		// 1. 최근 공지사항을 최대 5개까지 조회하여 List에 저장
		List<FreeBoard> fList = dao.selectFBoardNoticeList(conn);
		
		if(fList != null) {
			
			// 2. PageInfo의 limit 값을 (10 - 조회된 공지글 수) 변환
			fPInfo.setLimit(fPInfo.getLimit() - fList.size());
			
			// 3. 일반 게시글 목록을 조회하여 List에 저장
			// 이 때 변경된 PageInfo를 이용해 조회하기 때문에
			// 일반 게시글이 현재 페이지에 맞는 7개가 조회됨.
			List<FreeBoard> fList2 = dao.selectFBoardList(conn, fPInfo);
			
			//4. 공지사항을 저장해둔 List와  일반 게시글을 저장한 List를  하나의 List로 합쳐서 반환
			if(fList2 != null && !fList2.isEmpty()) {
				fList.addAll(fList2);
			}
			
		}

		close(conn);

		return fList;

	}

}





