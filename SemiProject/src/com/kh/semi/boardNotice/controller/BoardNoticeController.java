package com.kh.semi.boardNotice.controller;

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
import javax.servlet.http.HttpSession;

import com.kh.semi.boardNotice.model.service.BoardNoticeService;
import com.kh.semi.boardNotice.model.vo.BoardNotice;
import com.kh.semi.member.model.vo.Member;



@WebServlet("/notice/*")
public class BoardNoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String uri = request.getRequestURI(); // 전체 요청 주소''
//		ex) /wsp/notice/list.do

		String contextPath = request.getContextPath();
//		ex) /wsp

		String command = uri.substring((contextPath + "/notice").length());

//		System.out.println(uri);
//		System.out.println(contextPath);
//		System.out.println(command);

//		컨트롤러 내에서 공용으로 사용할 변수 미리 선언
		String path = null; // forward 또는 redirect 경로를 저장할 변수
		RequestDispatcher view = null; // 요청 위임 객체

//		sweet alert로 메세지 전달하는 용도
		String swalIcon = null;
		String swalTitle = null;
		String swalText = null;

		String errorMsg = null; // 에러 메세지 전달용 변수

//		******************* 공지사항 목록 조회  Controller ********************
		try {
			BoardNoticeService service = new BoardNoticeService();

//			공지사항 목록 조회 Controller 
			if (command.equals("/list.do")) {
				errorMsg = "공지사항 목록 조회 중 오류 발생 ..";

//				비즈니스 로직 
				List<BoardNotice> list = service.selectList();

//				요청을 위임할 jsp 경로를 지정
				path = "/WEB-INF/views/boardNotice/noticeList.jsp";

//				요청 위임 시 전달할 값 세팅
				request.setAttribute("list", list);

//				요청 위임 객체 생성 후 위임 진행
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
			
//			******************* 공지사항 상세 조회 Controller ********************
			else if (command.equals("/view.do")) {
				errorMsg = "공지사항 상세 조회 과정에서 오류 발생";
//				파라미터로 얻어오는 값은 자료형이 String 이므로 int로 형변환
//				쿼리스트링으로 전달된 공지사항 번호를 int형으로 파싱하여 저장
				int noticeNo = Integer.parseInt(request.getParameter("no"));

//				상세조회 비즈니스 로직 수행 결과 반환
				BoardNotice notice = service.selectNotice(noticeNo);

//				조회 결과에 따른 view 연결처리 
				if (notice != null) { // 조회 성공
//					요청을 위임할 jsp 경로를 지정 및 전달값 세팅
					path = "/WEB-INF/views/boardNotice/noticeView.jsp";
					request.setAttribute("notice", notice);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				} else { // 조회 실패 시 세션을 가져와 스왈얼럿 창으로 에러 메세지 출력
					HttpSession session = request.getSession();
					session.setAttribute("swalIcon", "error");
					session.setAttribute("swalTitle", "공지사항 조회 실패");
					response.sendRedirect(request.getHeader("referer"));
				}
			}
//			******************* 공지사항  작성 화면 전환 Controller ********************
			else if (command.equals("/insertForm.do")) {
				errorMsg = "공지사항 작성 화면 전환 과정에서 오류 발생";
				path = "/WEB-INF/views/boardNotice/noticeInsert.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
			
//			******************* 공지사항  등록 Controller ********************
			else if (command.equals("/insert.do")) {
				errorMsg = "공지사항 등록 과정에서 오류 발생";
				
//				파라미터로 전달된 값 저장
				String noticeTitle = request.getParameter("noticeTitle");
				String noticeContent = request.getParameter("noticeContent");
				
//				세션에서 회원 번호 얻어오기
				HttpSession session = request.getSession();
//				작성자의 회원번호
				int noticeWriter = ((Member)session.getAttribute("loginMember")).getMemNo();
				
//				Map을 이용하여 데이터 전달(VO 대신 사용하는 경우가 많다.)
				Map<String, Object> map = new HashMap<String, Object>(); // 다형성 업캐스팅
				map.put("noticeTitle", noticeTitle);
				map.put("noticeContent", noticeContent);
				map.put("noticeWriter", noticeWriter);
				
//				공지사항 등록 비즈니스 로직 수행 후 결과 반환
				int result = service.insertNotice(map);
				
				if(result > 0) { // 삽입 성공 시 
//					result에는 삽입된 공지사항 범호가 저장 되어있다.
					path = "view.do?no=" + result; // 쿼리스트링을 이용하여 상세조회 요청
					swalIcon = "success";
					swalTitle = "공지사항이 등록 되었습니다.";
					
				}else {
					path = "list.do";
					swalIcon = "error";
					swalTitle = "공지사항 등록 실패";
				}
				
				session.setAttribute("swalIcon", swalIcon);
				session.setAttribute("swalTitle", swalTitle);
				
				response.sendRedirect(path);
				
			}
			
//			******************* 공지사항 수정 화면 출력용 Controller *******************
			else if(command.equals("/updateForm.do")) {
				errorMsg = "공지사항 수정 화면 전환 과정에서 오류 발생";
				
				int noticeNo = Integer.parseInt(request.getParameter("no"));
				
				BoardNotice notice = service.upadateView(noticeNo);
				
				if(notice != null) {
					path = "/WEB-INF/views/boardNotice/noticeUpdate.jsp";
					request.setAttribute("notice", notice);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
					
				}else {
					request.getSession().setAttribute("swalIcon", "error");
					request.getSession().setAttribute("swalText", "공지사항 수정을 위한 조회 실패");
					
					response.sendRedirect("view.do?no=" + noticeNo); //( == referer)
				}
			}
			
//			공지사항 수정 Controller
			else if (command.equals("/update.do")) {
				
				errorMsg = "공지사항 수정 과정에서 오류 발생";
				
//				파라미더 얻어오기
				int noticeNo = Integer.parseInt(request.getParameter("no"));
				String noticeTitle = request.getParameter("noticeTitle");
				String noticeContent = request.getParameter("noticeContent");
				
//				비즈니스 로직 수행
				Map<String, Object> map = new HashMap<String, Object>(); // 다형성 업캐스팅
				map.put("noticeTitle", noticeTitle);
				map.put("noticeContent", noticeContent);
				map.put("noticeNo", noticeNo);
				
				int result = service.updateNotice(map);
				
//				로직 수행 성공 시 
//				로직 수행 성공 시 "공지사항이  수정되었습니다." swal 출력
//				로직 수행 실패 시 "공지사항이  수정 실패..." swal 출력
				if(result > 0) {
					swalIcon = "success";
					swalTitle = "공지사항이 수정되었습니다.";
				}else {
					swalIcon = "error";
					swalTitle = "공지사항 수정 실패...";
					
				}
				HttpSession session = request.getSession();
				session.setAttribute("swalIcon", swalIcon);
				session.setAttribute("swalTitle", swalTitle);
				
//				수정된 공지사항 상세조회으로 redirect
				response.sendRedirect("view.do?no=" + noticeNo);
			}
			
			
//			******************* 공지사항 삭제 Controller *******************
			else if (command.equals("/delete.do")) {
				errorMsg = "공지사항 삭제 과정에서 오류 발생";
				
				int noticeNo = Integer.parseInt(request.getParameter("no"));
				
//				비즈니스 로직 수행
				int result = service.updateNoticeFl(noticeNo);
				
				if (result > 0) {
					swalIcon = "success";
					swalTitle = "공지사항이 삭제되었습니다.";
					path = "list.do";
				} else {
					
					swalIcon = "error";
					swalTitle = "공지사항이 삭제 실패...";
					path = request.getHeader("referer");
//					요청 주소로 다시 보내준다.
					
				}
				
				request.getSession().setAttribute("swalIcon", swalIcon);
				request.getSession().setAttribute("swalTitle", swalTitle);
				response.sendRedirect(path);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			path = "/WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("errorMsg", errorMsg);
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
