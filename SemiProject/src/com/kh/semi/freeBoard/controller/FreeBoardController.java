package com.kh.semi.freeBoard.controller;

import java.io.IOException;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kh.semi.freeBoard.model.vo.Attachment;
import com.kh.semi.freeBoard.model.vo.Board;
import com.kh.semi.freeBoard.model.vo.PageInfo;
import com.kh.semi.common.MyFileRenamePolicy;
import com.kh.semi.freeBoard.model.service.FreeBoardService;
import com.kh.semi.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/freeBoard/*")
public class FreeBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String uri = request.getRequestURI();			
		String contextPath = request.getContextPath();	
		String command = uri.substring((contextPath + "/freeBoard").length() );

		String path = null;
		RequestDispatcher view = null;

		String swalIcon = null;
		String swalTitle = null;
		String swalText = null;

		String errorMsg = null;

		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");

		try {
			FreeBoardService service = new FreeBoardService();


			String cp = request.getParameter("cp");


			// 목록조회
			if(command.equals("/main.do")) {
				errorMsg = "게시판 목록 조회 과정에서 오류 발생";







				PageInfo pInfo = service.getPageInfo(cp);

				List<Board> bList = service.selectBoardList(pInfo);

				if(bList != null) {

					List<Attachment> fList = service.selectThumbnailList(pInfo);

					if(!fList.isEmpty()) {

						request.setAttribute("fList", fList);

					}

				}


				path = "/WEB-INF/views/freeBoard/freeMain.jsp";

				request.setAttribute("bList", bList);
				request.setAttribute("pInfo", pInfo);

				view = request.getRequestDispatcher(path);
				view.forward(request, response);





			}

			//게시글 상세 조회
			else if (command.equals("/view.do")) {
				errorMsg = "게시글 상세 조회 과정에서 오류 발생";



				if(loginMember == null) {


					request.getSession().setAttribute("swalIcon", "warning");
					request.getSession().setAttribute("swalTitle", "회원만 열람가능합니다.");
					response.sendRedirect("main.do?cp=" + cp);



				}else {
					


				int boardNo = Integer.parseInt(request.getParameter("no"));

				Board board = service.selectBoard(boardNo);

				if(board != null) { // 상세 조회 성공 시

					List<Attachment> fList = service.selectBoardFiles(boardNo);

					if(!fList.isEmpty()) {

						request.setAttribute("fList", fList);

					}


					path = "/WEB-INF/views/freeBoard/freeBoardView.jsp";
					request.setAttribute("board", board);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);

				}else {

					request.getSession().setAttribute("swalIcon", "error");
					request.getSession().setAttribute("swalTitle", "게시글 상세 조회 실패");
					response.sendRedirect("main.do?cp=1");
				}



			}





		}








		else if (command.equals("/insertForm.do")) {
			path = "/WEB-INF/views/freeBoard/freeBoardInsert.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}

		// 게시글 등록			
		else if (command.equals("/insert.do")) {

			errorMsg = "게시글 삽입 과정에서 오류 발생";


			int maxSize = 20 * 1024 * 1024; 

			// 파일 경로 지정
			String root = request.getSession().getServletContext().getRealPath("/");

			// 실제 배포되는 파일 주소
			String filePath = root + "resources/uploadImages/";

			System.out.println("filePath :" + filePath);



			MultipartRequest multiRequest
			= new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());


			// 파일 저장
			List<Attachment> fList = new ArrayList<Attachment>();

			// 파일 하나씩 꺼내기
			Enumeration<String> files = multiRequest.getFileNames();





			while(files.hasMoreElements()) { 
				//					
				String name = files.nextElement(); // img0
				//	


				if(multiRequest.getFilesystemName(name) != null) {

					//						// 파일 Attachment에 저장
					Attachment temp = new Attachment();

					temp.setFileName(multiRequest.getFilesystemName(name));
					temp.setFilePath(filePath);



					int fileLevel = 0;
					switch(name) {
					case "img0" : fileLevel = 0; break;
					case "img1" : fileLevel = 1; break;
					case "img2" : fileLevel = 2; break;
					case "img3" : fileLevel = 3; break;
					}

					temp.setFileLevel(fileLevel);


					fList.add(temp);
				} 

			}


			// 게시글 정보 파라미터에 저장
			String boardTitle = multiRequest.getParameter("boardTitle");
			String boardContent = multiRequest.getParameter("boardContent");


			int boardWriter = loginMember.getMemNo();

			//				fList , boardTitle, boardContent, categoryCode , boardWriter

			//				Map 객체를 생성하여 얻어온 정보들을 모두 저장
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("fList" , fList);
			map.put("boardTitle" , boardTitle);
			map.put("boardContent" , boardContent);
			map.put("boardWriter" , boardWriter);

			//				4. 게시글 등록 비즈니스 로직 수행 후 결과 반환받기
			int result = service.insertBoard(map);

			if(result > 0) { // DB 삽입 성공 시 result에 삽입한 글 번호가 저장되어있다.
				swalIcon = "success";
				swalTitle = "게시글 등록 성공";
				path = "view.do?cp=1&no=" + result;

			}else {
				swalIcon = "error";
				swalTitle = "게시글 등록에 싫패했습니다.";
				path = "main.do"; // 게시글 목록
			}
			request.getSession().setAttribute("swalIcon", swalIcon);
			request.getSession().setAttribute("swalTitle", swalTitle);

			response.sendRedirect(path);
		}

		//			*********** 게시글 수정 화면 전환 Controller ***********
		else if(command.equals("/updateForm.do")) {

			errorMsg = "게시글 수정 화면 전환 과정에서 오류 발생";


			// 글번호 얻어오기	
			int boardNo = Integer.parseInt(request.getParameter("no"));

			Board board = service.updateView(boardNo);

			//  수정 하려는 게시글 성공
			if(board != null) {

				// 이미지 파일 목록 조회
				List<Attachment> fList = service.selectBoardFiles(boardNo);

				if(!fList.isEmpty()) {
					request.setAttribute("fList", fList);
				}

				request.setAttribute("board" , board);

				path = "/WEB-INF/views/freeBoard/freeBoardUpdate.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);

			} else {
				request.getSession().setAttribute("swalIcon", "error");
				request.getSession().setAttribute("swalTitle", "게시글 수정 화면 전환 실패");
				response.sendRedirect(request.getHeader("referer"));
				//					상세 조회 -> 수정 화면  -> error -> 상세조회
			}

		}

		else if (command.equals("/update.do")) {
			errorMsg = "게시글 수정 과정에서 오류 발생";

			int maxSize = 20 * 1024 * 1024;
			String root = request.getSession().getServletContext().getRealPath("/");
			String filePath = root + "resources/uploadImages/";

			MultipartRequest mRequest = new MultipartRequest(request, filePath , maxSize , "UTF-8",
					new MyFileRenamePolicy());


			String boardTitle = mRequest.getParameter("boardTitle");
			String boardContent = mRequest.getParameter("boardContent");
			int boardNo = Integer.parseInt(mRequest.getParameter("no"));

			//				4. 전달 받은 파일 정보를 List에 저장
			List<Attachment> fList = new ArrayList<Attachment>();

			Enumeration<String> files = mRequest.getFileNames();
			//				input type = "file"인 모든 요소의 name 속성값을 받환받아 files에 저장

			while(files.hasMoreElements()) {

				//					현재 접근중인 name 속성 값을 변수에 저장한다.
				String name = files.nextElement();


				if(mRequest.getFilesystemName(name) != null ) {

					Attachment temp = new Attachment();

					// 변경된 파일이름 저장
					temp.setFileName(mRequest.getFilesystemName(name));

					// 파일 경로 저장
					temp.setFilePath(filePath);


					//게시글 번호 temp 에 저장
					temp.setParentBoardNo(boardNo);

					//						파일 레벨 temp에 저장
					switch(name) {
					case "img0" : temp.setFileLevel(0); break;
					case "img1" : temp.setFileLevel(1); break;
					case "img2" : temp.setFileLevel(2); break;
					case "img3" : temp.setFileLevel(3); break;

					}
					//						temp를 fList에 추가
					fList.add(temp);
				}
			} // end while



			int boardWriter = ((Member)request.getSession().getAttribute("loginMember")).getMemNo();


			Map<String, Object> map = new HashMap<String, Object>();

			map.put("boardTitle" , boardTitle);
			map.put("boardContent" , boardContent);
			map.put("boardNo" , boardNo);
			map.put("fList" , fList);
			map.put("boardWriter" , boardWriter);



			// map에 있는 정보 넘어감
			int result = service.updateBoard(map);



			path = "view.do?cp=" + cp + "&no=" + boardNo;

			if(result > 0 ) {
				swalIcon = "success";
				swalTitle = "게시글 수정 성공";


				String sk = mRequest.getParameter("sk");
				String sv = mRequest.getParameter("sv");

				if(sk != null && sv != null) {
					path += "&sk=" + sk + "&sv=" + sv;
				}

			}else {
				swalIcon = "error";
				swalTitle = "게시글 수정 실패";
			}

			request.getSession().setAttribute("swalIcon", swalIcon);
			request.getSession().setAttribute("swalTitle", swalTitle);

			response.sendRedirect(path);


		}


		// 게시글 삭제
		else if (command.equals("/delete.do")) {
			errorMsg = "게시글 수정 과정에서 오류 발생";

			int boardNo = Integer.parseInt(request.getParameter("no"));

			int result = service.deleteBoard(boardNo);


			if(result > 0) {
				swalIcon = "success";
				swalTitle = "게시글 삭제 성공";
				path = "main.do"; // 게시글 목록
			}else {
				swalIcon = "error";
				swalTitle = "게시글 삭제 실패";
				path = request.getHeader("referer");
			}

			request.getSession().setAttribute("swalIcon", swalIcon);
			request.getSession().setAttribute("swalTitle", swalTitle);
			response.sendRedirect(path);





		}





	}catch (Exception e) {
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

