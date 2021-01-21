package com.kh.semi.search.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.freeBoard.model.vo.FRAttachment;
import com.kh.semi.freeBoard.model.vo.FreeBoard;
import com.kh.semi.freeBoard.model.vo.FreePageInfo;
import com.kh.semi.search.model.service.SearchService;
import com.sun.javafx.collections.MappingChange.Map;

@WebServlet("/search.do")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response, Object cp) throws ServletException, IOException {
	
		String searchKey = request.getParameter("sk");
		String searchValue = request.getParameter("sv");
		String currentPage = request.getParameter("cp");
		
	
	try {
		
		SearchService service = new SearchService();
			// 세개를 전달하기 위한 Map
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("searchKey", searchKey); 
			map.put("searchValue", searchValue); 
			map.put("currentPage", cp); 
			
			
			// fPInfo 얻어오기
			 FreePageInfo fPInfo = service.getPageInfo(map);
			 
			// 검색 게시글 목록 조회 
			 
			 List<FreeBoard> fList = service.searchFBoardList(map, fPInfo);
			 	
			 
			 
			 if(fList != null) {
			
				 List<FRAttachment> fileList = service.searchThumbnailList(map, fPInfo);
					
					
				 if(!fList.isEmpty()) { // 조회된 썸네일 목록이 있다면
						request.setAttribute("fList", fList);
					}
					
				}

				// 조회된 내용과 PageInfo 객체를 request 객체에 담아서 요청 위임
				
				String path = "/WEB-INF/views/freeBoard/freeMain.jsp";
				
				request.setAttribute("fList", fList);
				request.setAttribute("fPInfo", fPInfo);

				RequestDispatcher view = request.getRequestDispatcher(path);
				view.forward(request, response);
			
		

	
	}catch(Exception e) {
		
		e.printStackTrace();
		String path = "/WEB-INF/views/common/errorPage.jsp";
		
		request.setAttribute("errorMsg", "검색 과정에서 오류 발생");
		
		RequestDispatcher view = request.getRequestDispatcher(path);
		view.forward(request, response);
	}
	
	
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
