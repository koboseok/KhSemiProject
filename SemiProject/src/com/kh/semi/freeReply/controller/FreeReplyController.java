package com.kh.semi.reply.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kh.semi.reply.model.service.FreeReplyService;
import com.kh.semi.reply.model.vo.FreeReply;


@WebServlet("/freeBoard/reply/*")
public class FreeReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath+"/reply").length());
		
		
		try {
			
			FreeReplyService service = new FreeReplyService();
			
			// 댓글 목록 조회 Controller=======================================================
			
			if(command.equals("/selectList.do")) {
				
				int fBoardNo = Integer.parseInt(request.getParameter("fBoardNo"));
				
				List<FreeReply> fReplyList = service.selectFList(fBoardNo);
				
				Gson gson = new GsonBuilder().setDateFormat("yyyy년 MM월 dd일 HH:mm").create();
				
				gson.toJson(fReplyList, response.getWriter());
				
				
				
			}
			
			else if(command.equals("/insertReply.do")) {
				
				
				// 오라클에서 숫자로 이루어진 문자열은 자동으로 숫자로 변환되는 특징을 사용할 예정
				String memName = request.getParameter("memName");
				int fBoardNo = Integer.parseInt(request.getParameter("fBoardNo"));
				String fReplyContent = request.getParameter("fReplyContent");	
				
				FreeReply fReply = new FreeReply();
				
				fReply.setMemName(memName); // 회원 번호 저장됨
				fReply.setfReplyContent(fReplyContent);
				fReply.setfBoardNo(fBoardNo);
				
				int result = service.insertFReply(fReply);
				
				response.getWriter().print(result);
			
				
			}
			
			// 댓글 수정 Controller==============================================
			
			else if(command.equals("/updateReply.do")){
				
				// 파라미터 얻어오기 댓글 번호
				int fReplyNo = Integer.parseInt(request.getParameter("fReplyNo"));
				String fReplyContent = request.getParameter("fReplyContent");
				
				FreeReply fReply = new FreeReply();
				fReply.setfReplyNo(fReplyNo);
				fReply.setfReplyContent(fReplyContent);
				
				int result = service.updateFReply(fReply);
				
				response.getWriter().print(result);
			
			
			}
			
			// 댓글 삭제 controller========================================
			else if(command.equals("/deleteReply.do")) {
				
				// 파라미터 얻어오기
				int fReplyNo = Integer.parseInt(request.getParameter("fReplyNo"));
				
				int result = service.updateReplyStatus(fReplyNo);
				
				response.getWriter().print(result);
				
				
				
				
			}
			
			
			
			
			
			
	}catch(Exception e){
			e.printStackTrace();
		}

	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
