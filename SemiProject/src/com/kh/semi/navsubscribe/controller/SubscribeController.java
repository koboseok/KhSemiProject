package com.kh.semi.navsubscribe.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.subscribereply.model.vo.Reply;
import com.kh.semi.navsubscribe.model.service.SubscribeService;
import com.kh.semi.navsubscribe.model.vo.Subscribe;

@WebServlet("/subscribe/*")
public class SubscribeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();

		String command = uri.substring((contextPath + "/subscribe").length());

		String path = null;
		RequestDispatcher view = null;

		String swalIcon = null;
		String swalTitle = null;
		String swalText = null;

		String errorMsg = null;

		try {
			SubscribeService service = new SubscribeService();

			if (command.equals("/main.do")) {
				errorMsg = "구독서비스 목록 조회 중 오류 발생";

				List<Subscribe> conList = service.selectConList();
				List<Subscribe> lifeList = service.selectLifeList();
				List<Subscribe> newsList = service.selectNewsList();
				List<Subscribe> foodList = service.selectFoodList();

				path = "/WEB-INF/views/subscribe/subscribe.jsp";

				request.setAttribute("conList", conList);
				request.setAttribute("lifeList", lifeList);
				request.setAttribute("newsList", newsList);
				request.setAttribute("foodList", foodList);

				view = request.getRequestDispatcher(path);
				view.forward(request, response);

			}

			else if (command.equals("/extends.do")) {
				errorMsg = "구독서비스 확장 목록 조회 중 오류 발생";

				String conName = request.getParameter("cname");
				List<Subscribe> exList = service.extendSubscribe(conName);

				path = "/WEB-INF/views/subscribe/subscribeExtends.jsp";

				request.setAttribute("exList", exList);

				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
			
			
			else if (command.equals("/info.do")) {
				errorMsg = "구독서비스 상세 정보 조회 중 오류 발생";

				String subName = request.getParameter("name");
				Subscribe subscribe = service.selectInfo(subName);

				String reply = request.getParameter("name");
				Reply replyInfo = service.selectReplyInfo(reply);

				path = "/WEB-INF/views/subscribe/subscribeInfo.jsp";

				request.setAttribute("subscribe", subscribe);
				request.setAttribute("replyInfo", replyInfo);

				view = request.getRequestDispatcher(path);
				view.forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
			path = "/WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("errorMsg", errorMsg);
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
