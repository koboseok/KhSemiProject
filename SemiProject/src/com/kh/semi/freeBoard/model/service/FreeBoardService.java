
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

	/** 목록 조회 Service
	 * @param fPInfo
	 * @return fLsit
	 */
	public List<FreeBoard> selectFBoardList(FreePageInfo fPInfo) throws Exception {

		Connection conn = getConnection();
		List<FreeBoard> fList = dao.selectFBoardList(conn, fPInfo);

		close(conn);
		return fList;
	}

	/** 게시물 상세 조회
	 * @param fBoard
	 * @return
	 */
	public FreeBoard selectFBoard(int fBoardNo)throws Exception {

		Connection conn =getConnection();

		FreeBoard fBoard = dao.selectFBoard(conn, fBoardNo);

		if(fBoard != null) {
			int result = dao.increaseReadCount(conn,fBoardNo); 
			
			if(result > 0) {
				commit(conn);
				
				fBoard.setfReadCount(fBoard.getfReadCount() + 1);
			}else			
			rollback(conn);	
		}
		
		
		
		close(conn);



		return fBoard;
	}



}





