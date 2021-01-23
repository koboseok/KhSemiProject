package com.kh.semi.subscribereply.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kh.semi.subscribereply.model.service.SubscribeReplyService;
import com.kh.semi.subscribereply.model.vo.SubscribeReply;

@WebServlet("/subscribereply/*")
public class SubscribeReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/subscribereply").length());

		try {
			SubscribeReplyService service = new SubscribeReplyService();

			if (command.equals("/selectList.do")) {

				String parentSubscribe = request.getParameter("parentSubscribe");
				List<SubscribeReply> srList = service.selectList(parentSubscribe);

				Gson gson = new GsonBuilder().setDateFormat("yyyy년 MM월 dd일 HH:mm").create();
				gson.toJson(srList, response.getWriter());
			}

			else if (command.contentEquals("/insertReply.do")) {
				int replyWriter = Integer.parseInt(request.getParameter("replyWriter"));
				String replyContent = request.getParameter("replyContent");
				String parentSubscribe = request.getParameter("parentSubscribe");
				int replyPoint = Integer.parseInt(request.getParameter("replyPoint"));

				SubscribeReply inReply = new SubscribeReply();
				inReply.setMemberNo(replyWriter);
				inReply.setReplyContent(replyContent);
				inReply.setServiceName(parentSubscribe);
				inReply.setPoint(replyPoint);

				int result = service.insertReply(inReply);

				response.getWriter().print(result);

			}

			else if (command.equals("/deleteReply.do")) {
				int ReplyNo = Integer.parseInt(request.getParameter("ReplyNo"));

				int result = service.updateReplyStatus(ReplyNo);

				response.getWriter().print(result);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
