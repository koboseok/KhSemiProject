package com.kh.semi.jointBoard.search.controller;

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

import com.kh.semi.jointBoard.model.vo.Attachment;
import com.kh.semi.jointBoard.model.vo.Board;
import com.kh.semi.jointBoard.model.vo.PageInfo;
import com.kh.semi.jointBoard.search.model.service.JointBoardSearchService;

@WebServlet("/jointBoard/jointBoardSearch.do")
public class JointBoardSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchKey = request.getParameter("sk");
		String searchValue = request.getParameter("sv");
		String cp = request.getParameter("cp");
		
		try {
			JointBoardSearchService service = new JointBoardSearchService();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("searchKey", searchKey);
			map.put("searchValue", searchValue);
			map.put("currentPage", cp);

			PageInfo pInfo = service.getPageInfo(map);
			
			//검색 게시글 목록 조회
			List<Board> bList = service.searchBoardList(map, pInfo);
			
			System.out.println(pInfo);
			for(Board b : bList) {
				System.out.println(b);
			}

		
			if(bList!=null) {
				List<Attachment> fList = service.searchThumbnailList(map, pInfo);
				
				if(!fList.isEmpty()) { 
					request.setAttribute("fList", fList);
				}
			}

			
			String path = "/WEB-INF/views/jointBoard/jointMain.jsp";
			request.setAttribute("bList", bList);
			request.setAttribute("pInfo", pInfo);
			
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
			
		} catch(Exception e) {
			e.printStackTrace();
			String path = "/WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("errMsg", "검색 과정에서 오류 발생");
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
