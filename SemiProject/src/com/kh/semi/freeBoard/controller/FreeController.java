package com.kh.semi.freeBoard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.freeBoard.model.service.FreeBoardService;
import com.kh.semi.freeBoard.model.vo.FreeBoard;
import com.kh.semi.freeBoard.model.vo.FreePageInfo;
import com.kh.semi.freeBoard.model.vo.FRAttachment;
import com.kh.semi.common.MyFileRenamePolicy;
import com.kh.semi.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;





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
				 
//				3. 게시글 목록이 조회 되었을 때
//				해당 게시글 목록 요소에 작성된 썸네일 이미지 목록 얻어오는 서비스 진행
				if(fList != null) {
//				썸네일 이미지 목록 조회 서비스 호출
				List<FRAttachment> tList = service.selectThumbnailList(fPInfo);
//				글번호가 매개변수로 전달돼야한다.
				
//				썸네일 이미지 목록이 비어있지 않은 경우
				if(!fList.isEmpty()) {
					
					request.setAttribute("fList", fList);
					
					
					}
				}
				
				
				path = "/WEB-INF/views/freeBoard/freeMain.jsp";
				request.setAttribute("fPInfo", fPInfo);
				request.setAttribute("fList", fList);
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
				
				
				
			// 게시글 상세 조회=======================================================	
			}else if(command.equals("/view.do")){
				
				errorMsg = "게시글 상세 조회 과정에서 오류 발생";
				
			    int fBoardNo = Integer.parseInt(request.getParameter("no"));
				
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
			/*
			 * path = "/WEB-INF/views/freeBoard/freeBoardInsert.jsp"; view =
			 * request.getRequestDispatcher(path); view.forward(request, response);
			 * 
			 */				
			}else if(command.equals("/insert.do")) {
				errorMsg = "게시글 삽입 과정에서 오류 발생";
				
				
				int maxSize = 20 * 1024 * 1024; 
				
				String root = request.getSession().getServletContext().getRealPath("/");
				
				String imgPath = root + "resources/uploadImages/";
				
				System.out.println("imgPath :" + imgPath);
				

//				1-4. MultipartRequest 객체 생성
//				-> 객체 생성과 동시에 파라미터로 넘어온 내용 중 파일이 서버에 바로 저장된다.
				MultipartRequest multiRequest = new MultipartRequest(request, imgPath,  maxSize, 
																		"UTF-8",new MyFileRenamePolicy());
				
//				2-1. 파일 정보를 모두 저장할 List 객체 생성
				List<FRAttachment> fileList = new ArrayList<FRAttachment>();
				
//				2-2. MultipartRequest에서 업로드된 파일의 name 속성값 모두 반환 받기
				Enumeration<String> files = multiRequest.getFileNames();

				

				while(files.hasMoreElements()) { // 다음 요소가 있다면 
				
					String name = files.nextElement(); // img0

					if(multiRequest.getFilesystemName(name) != null) {
						

						FRAttachment temp = new FRAttachment();
						
						temp.setImgName(multiRequest.getFilesystemName(name));
						temp.setImgPath(imgPath);
						
						int fileLevel = 0;
						switch(name) {
						case "img0" : fileLevel = 0; break;
						case "img1" : fileLevel = 1; break;
						case "img2" : fileLevel = 2; break;
						case "img3" : fileLevel = 3; break;
						}
						
						temp.setImgLevel(fileLevel);
						

						fileList.add(temp);
					} 
					
				}// end while

//				3. 파일 정보를 제외한 게시글 정보를 얻어와 저장하기
				String fBoardTitle = multiRequest.getParameter("fBoardTitle");
				String fBoardContent = multiRequest.getParameter("fBoardContent");
				
//				세션에서 로그인한 회원의 번호를 얻어오기
				Member loginMember = (Member)request.getSession().getAttribute("loginMember");
				int memNo = loginMember.getMemNo();
				
//				fList , boardTitle, boardContent, categoryCode , boardWriter
				
//				Map 객체를 생성하여 얻어온 정보들을 모두 저장
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("fileList" , fileList);
				map.put("fBoardTitle" , fBoardTitle);
				map.put("fBoardContent" , fBoardContent);
				map.put("memNo" ,memNo);
				 
				System.out.println(map);
//				4. 게시글 등록 비즈니스 로직 수행 후 결과 반환받기
				int result = service.insertFBoard(map);
				
				if(result > 0) { // DB 삽입 성공 시 result에 삽입한 글 번호가 저장되어있다.
					swalIcon = "success";
					swalTitle = "게시글 등록 성공";
					path = "view.do?cp=1&no=" + result;
				}else {
					swalIcon = "error";
					swalTitle = "게시글 등록 실패";
					path = "main.do"; // 게시글 목록
				}
				request.getSession().setAttribute("swalIcon", swalIcon);
				request.getSession().setAttribute("swalTitle", swalTitle);
				response.sendRedirect(path);
				
				
			// 게시글 수정 화면 전환 Controller==============================================================
			}else if(command.equals("/updateForm.do")){
				
//				errorMsg = "게시글 수정 화면 전환 과정에서 오류 발생";
				
//				수정화면이 미리 이전 내용을 작성될 수 있게
//				글 번호를 이용하여 이전 내용을 조회해온다.
				
				int fBoardNo = Integer.parseInt(request.getParameter("no"));
				
				FreeBoard freeBoard = service.updateFView(fBoardNo);
				
//				업데이트 화면 출력용 게시글 조회가 성공한 경우
				if(freeBoard != null) {
//					해당 게시글에 작성된 이미지(파일) 목록 정보 조회
					List<FRAttachment> fileList = service.selectBoardFiles(fBoardNo);
					
					if(!fileList.isEmpty()) {
						request.setAttribute("fileList", fileList);
					}
					
					request.setAttribute("freeBoard" , freeBoard);
					
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
			
//			게시글 수정 *******************************************
			
			else if (command.equals("/update.do")) {
				errorMsg = "게시글 수정 과정에서 오류 발생";
				

				int maxSize = 20 * 1024 * 1024; 
				String root = request.getSession().getServletContext().getRealPath("/");
				String imgPath = root + "resources/images/";
				

				MultipartRequest mRequest = new MultipartRequest(request, imgPath , maxSize , "UTF-8",
																			new MyFileRenamePolicy());

				String fBoardTitle = mRequest.getParameter("fBoardTitle");
				String fBoardContent = mRequest.getParameter("fBoardContent");
				int fBoardNo = Integer.parseInt(mRequest.getParameter("no"));
				

				List<FRAttachment> fileList = new ArrayList<FRAttachment>();
				
				Enumeration<String> files = mRequest.getFileNames();

				
				while(files.hasMoreElements()) {
					

					String name = files.nextElement();
					

					if(mRequest.getFilesystemName(name) != null ) {
						
						FRAttachment temp = new FRAttachment();
						

						temp.setImgName(mRequest.getFilesystemName(name));
						

						temp.setImgPath(imgPath);
						
						temp.setfBoardNo(fBoardNo);
						
						switch(name) {
						case "img0" : temp.setImgLevel(0); break;
						case "img1" : temp.setImgLevel(1); break;
						case "img2" : temp.setImgLevel(2); break;
						case "img3" : temp.setImgLevel(3); break;
						
						}
						fileList.add(temp);
					}
				} // end while
				
//				5. Session에서 로그인한 회원의 번호를 얻어와 저장
				int memName = ((Member)request.getSession().getAttribute("loginMember")).getMemNo();
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("fBoardTitle" , fBoardTitle);
				map.put("fBoardContent" , fBoardContent);
				map.put("fBoardNo" , fBoardNo);
				map.put("fileList" , fileList);
				map.put("memName" , memName);
				
//				7. 준비된 값을 매개변수로 하여 게시글 수정 Service 호출
				int result = service.updateFBoard(map);
				
//				8. result 값에 따라 View 연결 처리
				path = "view.do?cp=" + cp + "&no=" + fBoardNo;
				
				if(result > 0 ) {
					swalIcon = "success";
					swalTitle = "게시글 수정 성공";
					
//					상세 페이지로 돌아갈 수 있는 주소 생성
					
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
