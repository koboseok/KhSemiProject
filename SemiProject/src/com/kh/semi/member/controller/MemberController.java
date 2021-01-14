package com.kh.semi.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI();         
		String contextPath = request.getContextPath();   
		String command = uri.substring((contextPath + "/member").length() );

		String path = null;
		RequestDispatcher view = null;

		String swalIcon = null;
		String swalTitle = null;
		String swalText = null;

		String errorMsg = null;

		try {

			//MemberService service = new MemberService();
			//String cp = request.getParameter("cp");
			
			//회원가입 페이지 이동 Controller ******************************************
			if(command.equals("/signUpForm.do")) {
				errorMsg = "회원가입 페이지로 이동하는 과정에서 오류 발생";
				
				path="/WEB-INF/views/member/signUpForm.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			}
			//소개 페이지로 이동 Controller ******************************************
			else if(command.equals("/aboutSims.do")) {
				errorMsg = "소개 페이지로 이동하는 과정에서 오류 발생";
				
				path="/WEB-INF/views/member/aboutSims.jsp";
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
