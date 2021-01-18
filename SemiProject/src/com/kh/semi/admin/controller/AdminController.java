package com.kh.semi.admin.controller;

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

import com.kh.semi.admin.model.service.AdminService;
import com.kh.semi.admin.model.vo.PageInfo;
import com.kh.semi.member.model.vo.Member;


@WebServlet("/admin/*")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI();         
		String contextPath = request.getContextPath();   
		String command = uri.substring((contextPath + "/admin").length() );

		String path = null;
		RequestDispatcher view = null;

		String swalIcon = null;
		String swalTitle = null;
		String swalText = null;

		String errorMsg = null;

		try {

			AdminService service = new AdminService();
			String cp = request.getParameter("cp");
			
			//회원 조회 Controller ******************************************
			if(command.equals("/memberList.do")) {
				errorMsg = "회원 조회 과정에서 오류 발생";
				
				//페이징 처리를 위한 값 계산
				PageInfo pInfo = service.getPageInfo(cp);
				
				//게시글 목록 조회 비즈니스 로직
				List<Member> mList = service.selectMemberList(pInfo);
				
				path = "/WEB-INF/views/admin/memberList.jsp";
				request.setAttribute("mList", mList);
				request.setAttribute("pInfo", pInfo);
				
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			} 
			//불량 회원 조회 Controller ******************************************
			else if(command.equals("/blockList.do")) {
				errorMsg = "불량 회원 조회 과정에서 오류 발생";
				
				path = "/WEB-INF/views/admin/blockList.jsp";
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
