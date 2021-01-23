package com.kh.semi.jointReply.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kh.semi.jointReply.model.service.JointReplyService;
import com.kh.semi.jointReply.model.vo.JointReply;



@WebServlet("/jointReply/*")
public class JointReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath+"/jointReply").length());
		
		
		try {
			
			JointReplyService service = new JointReplyService();
			
			// 댓글 목록 조회 Controller=======================================================
			
			if(command.equals("/selectList.do")) {
				
				int parentBoardNo = Integer.parseInt(request.getParameter("parentBoardNo"));
				
				List<JointReply> rList = service.selectList(parentBoardNo);
				
				Gson gson = new GsonBuilder().setDateFormat("yyyy년 MM월 dd일 HH:mm").create();
				
				gson.toJson(rList, response.getWriter());
				
				
				
			}
			
			else if(command.equals("/insertReply.do")) {
				
				
				// 오라클에서 숫자로 이루어진 문자열은 자동으로 숫자로 변환되는 특징을 사용할 예정
				String replyWriter = request.getParameter("replyWriter");
				int parentBoardNo = Integer.parseInt(request.getParameter("parentBoardNo"));
				String replyContent = request.getParameter("replyContent");	
				
				JointReply reply = new JointReply();
				
				reply.setMemName(replyWriter); // 회원 번호 저장됨
				reply.setReplyContent(replyContent);
				reply.setParentBoardNo(parentBoardNo);
				
				int result = service.insertReply(reply);
				
				response.getWriter().print(result);
			
				
			}
			
			// 댓글 수정 Controller==============================================
			
			else if(command.equals("/updateReply.do")){
				
				// 파라미터 얻어오기 댓글 번호
				int replyNo = Integer.parseInt(request.getParameter("replyNo"));
				String replyContent = request.getParameter("replyContent");
				
				JointReply reply = new JointReply();
				reply.setReplyNo(replyNo);
				reply.setReplyContent(replyContent);
				
				int result = service.updateReply(reply);
				
				response.getWriter().print(result);
			
			
			}
			
			// 댓글 삭제 controller========================================
			else if(command.equals("/deleteReply.do")) {
				
				// 파라미터 얻어오기
				int replyNo = Integer.parseInt(request.getParameter("replyNo"));
				
				int result = service.updateReplyStatus(replyNo);
				
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
