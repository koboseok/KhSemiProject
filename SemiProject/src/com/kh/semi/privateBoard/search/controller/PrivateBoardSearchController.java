package com.kh.semi.privateBoard.search.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.privateBoard.model.vo.Attachment;
import com.kh.semi.privateBoard.model.vo.Board;
import com.kh.semi.privateBoard.model.vo.PageInfo;
import com.kh.semi.privateBoard.search.model.service.PrivateBoardSearchService;

@WebServlet("/privateBoard/privateBoardSearch.do")
public class PrivateBoardSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchKey = request.getParameter("sk");
		String searchValue = request.getParameter("sv");
		String cp = request.getParameter("cp");
		
		try {
			PrivateBoardSearchService service = new PrivateBoardSearchService();
			
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("searchKey", searchKey);
			map.put("searchValue", searchValue);
			map.put("currentPage", cp);
			
			//검색된 내용에 대한 페이징 처리를 위해 데이터를 계산하고 저장하는 객체 PageInfo 얻어오기
			PageInfo pInfo = service.getPageInfo(map);
			
			//검색 게시글 목록 조회
			List<Board> bList = service.searchBoardList(map, pInfo);
			
			//결과 확인
			/*
			System.out.println(pInfo);
			for(Board b : bList) {
				System.out.println(b);
			}
			*/
			
			//검색 게시글 목록 조회 성공시 썸네일 목록 조회 수행
			if(bList!=null) {
				List<Attachment> fList = service.searchThumbnailList(map, pInfo);
				
				if(!fList.isEmpty()) { //조회된 썸네일 목록이 있다면
					request.setAttribute("fList", fList);
				}
			}
			
			
			//조회된 내용과 pageInfo객체를 request 객체에 담아서 요청 위임
			String path = "/WEB-INF/views/privateBoard/PrivateMain.jsp";
			request.setAttribute("bList", bList);
			request.setAttribute("pInfo", pInfo);
			
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
			
			
		} catch(Exception e) {
			e.printStackTrace();
			String path = "/WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("errMsg", "검색 과정에서 오류 발생");
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
