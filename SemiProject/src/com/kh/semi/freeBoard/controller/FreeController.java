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
				
//				제출되는 form태그의 encType이 multipart/form-data 형식이면
//				기존에 사용하던 request 객체로 파라미터를 얻어올 수 없다.
//				-> cos.jar에서 제공하는 MultipartRequest 객체를 사용하면 파라미터를 얻어올 수 있다.

//				1. MultipartRequest 객체 생성하기
//				1-1. 전송 파일 용량 지정 (byte)
				int maxSize = 20 * 1024 * 1024; // 20MB == 20 * 1024KB == 20 * 1024 * 1024
				
//				1-2. 서버에 업로드된 파일을 저장할 경로 지정
				String root = request.getSession().getServletContext().getRealPath("/");
//				실제 배포되는 최상위 주소를 가져와라
				String imgPath = root + "resources/uploadImages/";
				
				System.out.println("filePath :" + imgPath);
				
//				1-3. 파일명 변환을 위한 클래스 작성하기
//				cos.jar에서 중복되는 파일이 업로드 되었을 때 파일명을 바꿔주는 
//				DefaultFileRenamePolicy 클래스를 제공해 주지만 ex) a.jpg , a(1).jpg , a(2).jpg
				
//				파일명에 업로드된 시간을 표기할 수 있도록 변경하는 별도의 클래스를 작성할 예정이다.
				
//				1-4. MultipartRequest 객체 생성
//				-> 객체 생성과 동시에 파라미터로 넘어온 내용 중 파일이 서버에 바로 저장된다.
				MultipartRequest multiRequest = new MultipartRequest(request, imgPath,  maxSize, 
																		"UTF-8",new MyFileRenamePolicy());
//				2. 생성한 MultipartRequest 객체에서 파일 정보만을 얻어와
//				별도의 List에 모두 저장하기
				
//				2-1. 파일 정보를 모두 저장할 List 객체 생성
				List<FRAttachment> fileList = new ArrayList<FRAttachment>();
				
//				2-2. MultipartRequest에서 업로드된 파일의 name 속성값 모두 반환 받기
				Enumeration<String> files = multiRequest.getFileNames();
//				img0~3까지의 이미지 파일을 하나씩 꺼낸다.
//				Iterator : 컬렉션 요소 반복 접근자
//				Enumeration : Iterator의 과거 버전 
				
//				2-3. 얻어온 Enumeration 객체에 요소를 하나씩 반복 접근하여
//					업로드된 파일 정보를 Attachment 객체에 저장한 후 fList에 추가하기
				while(files.hasMoreElements()) { // 다음 요소가 있다면 
//					현재 접근한 요소 값 반환
					String name = files.nextElement(); // img0
//					System.out.println("name : " + name);
//					System.out.println("원본 파일명 : " + multiRequest.getOriginalFileName(name));
//					System.out.println("변경된 파일명 : " + multiRequest.getFilesystemName(name));
					
//					제출받은 file태그 요소 중 업로드된 파일이 있을 경우
					if(multiRequest.getFilesystemName(name) != null) {
						
//						Attachment 객체에 파일 정보 저장
						FRAttachment temp = new FRAttachment();
						
						temp.setImgName(multiRequest.getFilesystemName(name));
						temp.setImgPath(imgPath);
						
//						name 속성에 따라 fileLevel 지정
						int fileLevel = 0;
						switch(name) {
						case "img0" : fileLevel = 0; break;
						case "img1" : fileLevel = 1; break;
						case "img2" : fileLevel = 2; break;
						case "img3" : fileLevel = 3; break;
						}
						
						temp.setImgLevel(fileLevel);
						
//						fList에 추가
						fileList.add(temp);
					} 
					
				}// end while

//				3. 파일 정보를 제외한 게시글 정보를 얻어와 저장하기
				String fBoardTitle = multiRequest.getParameter("fBoardTitle");
				String fBoardContent = multiRequest.getParameter("fBoardContent");
				
//				세션에서 로그인한 회원의 번호를 얻어오기
				Member loginMember = (Member)request.getSession().getAttribute("loginMember");
				int memName = loginMember.getMemNo();
				
//				fList , boardTitle, boardContent, categoryCode , boardWriter
				
//				Map 객체를 생성하여 얻어온 정보들을 모두 저장
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("fileList" , fileList);
				map.put("fBoardTitle" , fBoardTitle);
				map.put("fBoardContent" , fBoardContent);
				map.put("memName" ,memName);
				 
//				4. 게시글 등록 비즈니스 로직 수행 후 결과 반환받기
				int result = service.insertFBoard(map);
				
				if(result > 0) { // DB 삽입 성공 시 result에 삽입한 글 번호가 저장되어있다.
					swalIcon = "success";
					swalTitle = "게시글 등록 성공";
					path = "view.do?cp=1&no=" + result;
				}else {
					swalIcon = "error";
					swalTitle = "게시글 등록 실패";
					path = ".do"; // 게시글 목록
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
				
//				1. MultipartRequest 객체 생성에 필요한 값 설정
				int maxSize = 20 * 1024 * 1024; // 최대 크기 20MB
				String root = request.getSession().getServletContext().getRealPath("/");
				String imgPath = root + "resources/uploadImages/";
				
//				2. MultipartRequest 객체 생성
//				-> 생성과 동시에 전달받은 파일이 서버에 저장된다.
				MultipartRequest mRequest = new MultipartRequest(request, imgPath , maxSize , "UTF-8",
																			new MyFileRenamePolicy());
//				3. 파일 정보를 제외한 파라미터 얻어오기 
				String fBoardTitle = mRequest.getParameter("fBoardTitle");
				String fBoardContent = mRequest.getParameter("fBoardContent");
				int categoryCode = Integer.parseInt(mRequest.getParameter("categoryCode"));
				int fBoardNo = Integer.parseInt(mRequest.getParameter("no"));
				
//				4. 전달 받은 파일 정보를 List에 저장
				List<FRAttachment> fileList = new ArrayList<FRAttachment>();
				
				Enumeration<String> files = mRequest.getFileNames();
//				input type = "file"인 모든 요소의 name 속성값을 받환받아 files에 저장
				
				while(files.hasMoreElements()) {
					
//					현재 접근중인 name 속성 값을 변수에 저장한다.
					String name = files.nextElement();
					
//					현재 name 속성이 일치하는 요소로 업로드된 파일이 있다면
					if(mRequest.getFilesystemName(name) != null ) {
						
						FRAttachment temp = new FRAttachment();
						
//						변경된 파일 이름 temp에 저장
						temp.setImgName(mRequest.getFilesystemName(name));
						
//						지정한 파일 경로 temp에 저장
						temp.setImgPath(imgPath);
						
//						해당 게시글 번호 temp에 저장
						temp.setfBoardNo(fBoardNo);
						
//						파일 레벨 temp에 저장
						switch(name) {
						case "img0" : temp.setImgLevel(0); break;
						case "img1" : temp.setImgLevel(1); break;
						case "img2" : temp.setImgLevel(2); break;
						case "img3" : temp.setImgLevel(3); break;
						
						}
//						temp를 fList에 추가
						fileList.add(temp);
					}
				} // end while
				
//				5. Session에서 로그인한 회원의 번호를 얻어와 저장
				int boardWriter = ((Member)request.getSession().getAttribute("loginMember")).getMemNo();
				
//				6. 준비된 값들을 하나의 Map에 저장
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("fBoardTitle" , fBoardTitle);
				map.put("fBoardContent" , fBoardContent);
				map.put("fBoardNo" , fBoardNo);
				map.put("fileList" , fileList);
				map.put("memN" , boardWriter);
				
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
