package com.kh.semi.freeBoard.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.freeBoard.model.service.FreeBoardService;
import com.kh.semi.freeBoard.model.vo.FreeBoard;
import com.kh.semi.freeBoard.model.vo.FreePageInfo;





@WebServlet("/freeBoard/*")
public class FreeController extends HttpServlet {
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
			FreeBoardService service = new FreeBoardService();

			// 현재 페이지 얻어오기(currentPage)
			String cp = request.getParameter("cp");
			
			
			
			// 게시글 목록 조회(메인)==================================================
			if(command.equals("/main.do")) {
				errorMsg = "게시판 목록 조회 과정에서 오류 발생";
			
				// 페이징 처리를 위한 값 계산 Service 호출 
				FreePageInfo fPInfo = service.getFreePageInfo(cp); // 매개변수로 현재 페이지 전달
				 
				// 게시글 목록 조회 // 현재 페이지에 있는 목록을 조회하기 위해 fPInfo 가져옴(currentPage, limit)
				List<FreeBoard> fList = service.selectFBoardList(fPInfo);
				 
				
			
				
				
				path = "/WEB-INF/views/freeBoard/freeMain.jsp";
				request.setAttribute("fPInfo", fPInfo);
				request.setAttribute("fList", fList);
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
				
				
				
			// 게시글 상세 조회=======================================================	
			}else if(command.equals("/view.do")){
				
				errorMsg = "게시글 상세 조회 과정에서 오류 발생";
				
			    int fBoardNo = Integer.parseInt(request.getParameter("fNo"));
				
			    // 상세조회 비즈니스 로직
			    
			    FreeBoard fBoard = service.selectFBoard(fBoardNo);
			    
			    if(fBoard != null) {
					path = "/WEB-INF/views/freeBoard/freeBoardView.jsp";
					request.setAttribute("fBoard", fBoard);
					view = request.getRequestDispatcher(path); // 요청 위임 객체 생성
					view.forward(request, response); // forward 진행
			    	
			    }else {
			    	request.getSession().setAttribute("swalIcon", "error");
			    	request.getSession().setAttribute("swalTitle", "게시글 상세조회 실패하였습니다.");
			    	response.sendRedirect("main.do?cp=1"); // 목록으로 보내기
			    }
			    
			    
			   
				
				
		  // 게시글 작성 화면 전환 Controller =================================================================
			}else if(command.equals("/insertForm.do")) {

				path = "/WEB-INF/views/freeBoard/freeBoardInsert.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
				
			// 게시글 동록 Controller ( + 파일 업로드)============================================================
				
			}else if(command.equals("/insert.do")) {
				
				path = "/WEB-INF/views/freeBoard/freeBoardInsert.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			
				
			// 게시글 수정 화면 전환 Controller==============================================================
			}else if(command.equals("/updateForm.do")){
				
				errorMsg = "게시글 삽입  과정에서 오류 발생";
				
				
			}
			
			
			
			
			
			
		}catch(Exception e){
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
