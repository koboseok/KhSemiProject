package com.kh.semi.myList.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.member.model.vo.Member;
import com.kh.semi.myList.model.service.MyListService;
import com.kh.semi.myList.model.vo.MyList;

@WebServlet("/myList/*")
public class MyListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * String path = "/WEB-INF/views/myList/myList.jsp"; RequestDispatcher view =
		 * request.getRequestDispatcher(path); view.forward(request, response);
		 */
		
		String uri = request.getRequestURI();         
		String contextPath = request.getContextPath();   
		String command = uri.substring((contextPath + "/myList").length() );

		String path = null;
		RequestDispatcher view = null;

		String swalIcon = null;
		String swalTitle = null;
		String swalText = null;

		String errorMsg = null;

		HttpSession session = request.getSession();
		try {
			
			MyListService service = new MyListService();

			
//			마이리스트 Controller ******************************************
			if(command.equals("/myList.do")) {
				errorMsg = "마이리스트 조회 과정에서 오류 발생";
				
				
				Member loginMember = (Member)session.getAttribute("loginMember");
				
				
//				로그인이 되어있지 않을때
				if(loginMember == null) {
					
					swalIcon = "error";
					swalTitle = "마이리스트 조회 실패..";
					swalText = "로그인 후 이용해 주세요.";
					
					
//				로그인이 되어있을 때
				}else {
					
					int memNo = loginMember.getMemNo();
					
					List<MyList> list = service.selectList(memNo);
					System.out.println(list);
					
					request.setAttribute("list", list);
				}
					
					session.setAttribute("swalIcon", swalIcon);
					session.setAttribute("swalTitle", swalTitle);
					session.setAttribute("swalText", swalText);
					
					path = "/WEB-INF/views/myList/myList.jsp";
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				
			} 
			
			
			
			
			

		} catch(Exception e) {
			e.printStackTrace();
			path = "/WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("errorMsg", errorMsg);
			view = request.getRequestDispatcher(path);
			view.forward(request,response);
		}
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
