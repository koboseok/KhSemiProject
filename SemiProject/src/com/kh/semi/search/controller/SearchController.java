package com.kh.semi.search.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.freeBoard.model.vo.FreePageInfo;
import com.kh.semi.search.model.service.SearchService;
import com.sun.javafx.collections.MappingChange.Map;

@WebServlet("/search.do")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();  // 요청 들어 오는 주소
		String contextPath = request.getContextPath(); // 
		String command = uri.substring((contextPath + "/freeBoard").length());  // freeBoard 뒤만 잘라내기

		String path = null;
		RequestDispatcher view = null;
		
		String swalIcon = null;
		String swalTitle = null;
		String swalText = null;

		String errorMsg = null;
		
	
	try {
		
		if(command.equals("freeBoard/search.do")) {
			
			String searchKey = request.getParameter("fsk");
			String searchValue = request.getParameter("fsv");
			String currentpage = request.getParameter("cp");

			// 세개를 전달하기 위한 Map
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			SearchService service = new SearchService();
			
			// fPInfo 얻어오기
			 FreePageInfo fPInfo = service.getPageInfo();
			 
			
		}

	
	}catch(Exception e) {
		e.printStackTrace();
		path = "/WEB-INF/views/common/errorPage.jsp";
		request.setAttribute("errorMsg", errorMsg);
		view = request.getRequestDispatcher(path);
		view.forward(request, response);
	}
	
	
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
