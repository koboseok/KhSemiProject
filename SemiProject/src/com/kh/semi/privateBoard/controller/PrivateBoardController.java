package com.kh.semi.privateBoard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/privateBoard/*")
public class PrivateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		String path = "/WEB-INF/views/privateBoard/privateMain.jsp";
//		RequestDispatcher view = request.getRequestDispatcher(path);
//		view.forward(request,response);
	
		
		
		String path2 = "/WEB-INF/views/privateBoard/privateBoardView.jsp";
		RequestDispatcher view2 = request.getRequestDispatcher(path2);
		
		view2.forward(request,response);
		
		
//		String path3 = "/WEB-INF/views/privateBoard/privateBoardInsert.jsp";
//		RequestDispatcher view3 = request.getRequestDispatcher(path3);
//		view3.forward(request,response);
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
