package com.kh.semi.member.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.member.model.service.MemberService;
import com.kh.semi.member.model.vo.Member;

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
			//회원가입 Controller **************************************************
			else if(command.contentEquals("/signUp.do")) {
				errorMsg = "회원가입 과정에서 오류 발생";
				
				String memEmail = request.getParameter("email");
				String memPwd = request.getParameter("pwd1");
				String memName = request.getParameter("name");
				
				String phone1 = request.getParameter("phone1");
				String phone2 = request.getParameter("phone2");
				String phone3 = request.getParameter("phone3");
				String memPhone = phone1 + "-" + phone2 + "-" + phone3;

				Member member = new Member(memEmail, memPwd, memName, memPhone);	
				
				
				String serviceName = request.getParameter("serviceName");
				int serviceCharge = Integer.parseInt(request.getParameter("serviceCharge"));
				Date paymentDate = Date.valueOf(request.getParameter("paymentDate"));
				
				Map<String, Object> service = new HashMap<String, Object>();
				service.put("serviceName", serviceName);
				service.put("serviceCharge", serviceCharge);
				service.put("paymentDate", paymentDate);
				
				
				
					int result = new MemberService().signUp(member, service);
					
					if(result>0) {
						swalIcon = "success";
						swalTitle = "회원가입 성공!";
						swalText = memName + "님의 가입을 환영합니다!";
					} else {
						swalIcon = "error";
						swalTitle = "회원가입 실패...";
						swalText = "문제가 지속될 경우 고객센터로 문의 바랍니다.";
					}
					
					HttpSession session = request.getSession();
					session.setAttribute("swalIcon", swalIcon);
					session.setAttribute("swalTitle", swalTitle);
					session.setAttribute("swalText", swalText);
					
					response.sendRedirect(request.getContextPath());
					
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
