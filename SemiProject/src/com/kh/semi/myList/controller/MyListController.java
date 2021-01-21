package com.kh.semi.myList.controller;

import java.io.IOException;
import java.sql.Date;
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

			Member loginMember = (Member)session.getAttribute("loginMember");
			
//			마이리스트 Controller ******************************************
			if(command.equals("/myList.do")) {
				errorMsg = "마이리스트 조회 과정에서 오류 발생";
				
				
				
				
//				로그인이 되어있지 않을때
				if(loginMember == null) {
					
					swalIcon = "error";
					swalTitle = "마이리스트 조회 실패..";
					swalText = "로그인 후 이용해 주세요.";
					
					
//				로그인이 되어있을 때
				}else {
					
					int memNo = loginMember.getMemNo();
					
					List<MyList> list = service.selectList(memNo);
//					System.out.println(list);
					
					int sum = 0;
					int temp = 0;
					
					for (MyList myList : list ) {
						
						 sum += myList.getPrice();
					}
					request.setAttribute("list", list);
					request.setAttribute("sum", sum);
					
					temp = sum;
					sum = 0;
					request.setAttribute("temp", temp);
					
				}
					
					session.setAttribute("swalIcon", swalIcon);
					session.setAttribute("swalTitle", swalTitle);
					session.setAttribute("swalText", swalText);
					
					path = "/WEB-INF/views/myList/myList.jsp";
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				
			} 
			
//			구독 리스트 추가 ***************************
			else if (command.equals("/addList.do")) {
				errorMsg = "마이리스트 추가 과정에서 오류 발생";
				String servCode = request.getParameter("servCode");
				int servPrice = Integer.parseInt(request.getParameter("servPrice"));
				String startDt = request.getParameter("startDt");
				
				Date serviceSt = Date.valueOf(request.getParameter("startDt")); 
				int memNo = loginMember.getMemNo();
				
				MyList addList = new MyList();
				
				addList.setServCode(servCode);
				addList.setPrice(servPrice);
				addList.setStartDt(serviceSt);
				addList.setMemNo(memNo);
				
				System.out.println(addList);
				
				int result = service.insertMyList(addList);
				
//				추가 성공 시
				if (result > 0) {
					swalIcon =  "success";
					swalTitle = "구독 리스트 추가 성공 !!";
					swalText = "마이 리스트가 추가 되었습니다 .";
					
				}else {
					swalIcon = "error";
					swalTitle = "마이 리스트 추가 실패 ..";
					swalText = "건의 게시판에 문의 바랍니다.";
				}
				session.setAttribute("swalIcon", swalIcon);
				session.setAttribute("swalTitle", swalTitle);
				session.setAttribute("swalText", swalText);
				
				response.sendRedirect("myList.do");
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
