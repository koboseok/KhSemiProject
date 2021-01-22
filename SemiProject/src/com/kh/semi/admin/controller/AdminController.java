package com.kh.semi.admin.controller;

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

import com.kh.semi.admin.model.service.AdminService;
import com.kh.semi.admin.model.vo.PageInfo;
import com.kh.semi.admin.model.vo.Report;
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
			//정지 회원으로 전환 + 신고 내용 입력 Controller ******************************************
			if(command.equals("/reportMember.do")) {
				errorMsg = "회원 등급 변경 과정에서 오류 발생";
				String reportReason = request.getParameter("reportReason");
				Date reportDate = Date.valueOf(request.getParameter("reportDate"));
				int boardNo = Integer.parseInt(request.getParameter("boardNo"));
				String boardCm = request.getParameter("boardCm");
				int selectMemNo = Integer.parseInt(request.getParameter("selectMemNo"));
				String boardCode = request.getParameter("boardCode");
				
				
				Report report = new Report(reportReason, reportDate, boardNo, boardCm,
						selectMemNo, boardCode);
				
				int result = service.reportMember(report);
			
				if(result>0) {
					swalIcon = "success";
					swalText = "MemberNo." + boardNo + " 회원의 계정이 정지되었습니다.";
				} else {
					swalIcon = "error";
					swalTitle = "에러 발생! 다시 시도해 주세요.";
				}
				
				HttpSession session = request.getSession();
				session.setAttribute("swalIcon", swalIcon);
				session.setAttribute("swalTitle", swalTitle);
				
				response.sendRedirect(request.getHeader("referer"));
			}
			
			
			//정지 회원 조회 Controller ******************************************
			else if(command.equals("/blockList.do")) {
				errorMsg = "불량 회원 조회 과정에서 오류 발생";
				
				//페이징 처리를 위한 값 계산
				PageInfo pInfo = service.getBpageInfo(cp);
				
				//게시글 목록 조회 비즈니스 로직
				List<Report> bmList = service.selectBmemberList(pInfo);
				
				path = "/WEB-INF/views/admin/blockList.jsp";
				request.setAttribute("bmList", bmList);
				request.setAttribute("pInfo", pInfo);
				
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
			//일반 회원으로 전환 Controller ******************************************
			else if(command.equals("/convertMember.do")) {
				errorMsg = "일반 회원으로 전환하는 과정에서 오류 발생";
				
				int memNo = Integer.parseInt(request.getParameter("selectMemNo"));
				int result = service.convertMember(memNo);
				
				if(result>0) {
					swalIcon = "success";
					swalTitle = "일반 회원으로 전환 완료";
				} else {
					swalIcon = "error";
					swalTitle = "일반 회원으로 전환 실패";
				}
				
				HttpSession session = request.getSession();
				session.setAttribute("swalIcon", swalIcon);
				session.setAttribute("swalTitle", swalTitle);
				
				response.sendRedirect(request.getHeader("referer"));

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
