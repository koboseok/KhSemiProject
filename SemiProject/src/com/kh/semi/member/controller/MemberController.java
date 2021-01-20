package com.kh.semi.member.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.member.model.service.MemberService;
import com.kh.semi.member.model.vo.MemSubscribe;
import com.kh.semi.member.model.vo.Member;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI();         
		String contextPath = request.getContextPath();   
		String command = uri.substring((contextPath + "/member").length() );

		String path = null;
		RequestDispatcher view = null;

		String swalIcon = null;
		String swalTitle = null;
		String swalText = null;

		String errorMsg = null;

		try {

			MemberService mService = new MemberService();
			//String cp = request.getParameter("cp");

			//회원가입 페이지 이동 Controller ******************************************
			if(command.equals("/signUpForm.do")) {
				errorMsg = "회원가입 페이지로 이동하는 과정에서 오류 발생";

				path="/WEB-INF/views/member/signUpForm.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);

			}
			//소개 페이지로 이동 Controller ******************************************
			else if(command.equals("/aboutSims.do")) {
				errorMsg = "소개 페이지로 이동하는 과정에서 오류 발생";

				path="/WEB-INF/views/member/aboutSims.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			} 
			//회원가입 Controller **************************************************
			else if(command.contentEquals("/signUp.do")) {
				errorMsg = "회원가입 과정에서 오류 발생";

				String memEmail = request.getParameter("email");
				String memPwd = request.getParameter("pwd1");
				String memName = request.getParameter("name");

				String phone1 = request.getParameter("phone1");
				String phone2 = request.getParameter("phone2");
				String phone3 = request.getParameter("phone3");
				String memPhone = phone1 + "-" + phone2 + "-" + phone3;

				Member member = new Member(memEmail, memPwd, memName, memPhone);	

				int result = mService.signUp(member);

				if(result>0) {
					if(request.getParameter("serviceName") != "") {
						
						List<MemSubscribe> list = new ArrayList<MemSubscribe>();
						MemSubscribe memSub = null;
						
						String[] serviceName = request.getParameterValues("serviceName");
						String[] serviceCg = request.getParameterValues("serviceCharge");
						String[] servicePd = request.getParameterValues("paymentDate");
						
						//이메일로 멤버 번호를 가져오기
						int memNo = mService.getMemNo(memEmail);
						int serviceNo = 0;
						int serviceCharge = 0;
						Date servicePayDay = new Date(System.currentTimeMillis());
						
						for(int i=0; i<serviceName.length; i++) {
							serviceNo = mService.getServCode(serviceName[i]);
							
							if(serviceCg[i] != "") {
							serviceCharge = Integer.parseInt(serviceCg[i]); }
							
							if(servicePd[i] != "") {
							servicePayDay = Date.valueOf(request.getParameter("paymentDate")); }
							
							memSub = new MemSubscribe(memNo, serviceNo, servicePayDay, serviceCharge);
							list.add(memSub);
						}
						
						result = mService.insertMemSub(list);
					}
				}

				if(result>0) {
					swalIcon = "success";
					swalTitle = "회원가입 성공!";
					swalText = memName + "님의 가입을 환영합니다!";
				} else {
					swalIcon = "error";
					swalTitle = "회원가입 실패...";
					swalText = "문제가 지속될 경우 고객센터로 문의 바랍니다.";
				}

				HttpSession session = request.getSession();
				session.setAttribute("swalIcon", swalIcon);
				session.setAttribute("swalTitle", swalTitle);
				session.setAttribute("swalText", swalText);

				response.sendRedirect(request.getContextPath());

			}
			//이메일 중복검사 Controller **************************************************
			else if(command.equals("/emailDupCheck.do")) {
				errorMsg = "아이디 중복검사 중 오류가 발생하였습니다.";

				String email = request.getParameter("email");

				int result = mService.emailDupCheck(email);
				response.getWriter().print(result);
			}
			//별명 중복검사 Controller **************************************************
			else if(command.equals("/nameDupCheck.do")) {
				errorMsg = "별명 중복 검사 중 오류가 발생하였습니다.";

				String name = request.getParameter("name");

				int result = mService.nameDupCheck(name);
				response.getWriter().print(result);
			}




			//			로그인 ********************************************
			else if (command.equals("/login.do")) {


				//				1.POST 방식으로 전달된 데이터의 문자 인코딩 변경
				//				request.setCharacterEncoding("UTF-8");

				//				2.파라미터를 얻어와 변수에 저장
				String memEmail = request.getParameter("memEmail");
				String memPwd = request.getParameter("memPwd");
				String save = request.getParameter("save");
				//				체크 시 on , 아니면 null값으로 넘어온다.
				//				System.out.println(memEmail + "/" + memEmail + "/" + save);

				//				JDBC 수행
				//				아이디와 비밀번호를 하나의 VO에 담에서 service로 전달
				//				3.아이디와 비밀번호를 Member 객체에 세팅
				Member member = new Member();

				member.setMemEmail(memEmail);;
				member.setMemPwd(memPwd);

				//				* try-catch를 이용하여 service, dao에서 발생한 예외를 처리
				//				-> 응답 화면을 에러 페이지로 연결

				//					4. Member 객체를 Service로 전달하여 결과를 반환받기
				//					(로그인이란 ? id/pw 가 일치하는 회원정보를 DB에서 조회해 오는 것)
				Member loginMember = new MemberService().loginMember(member);

				System.out.println(loginMember);

				//					로그인 정보는 로그아웃 또는 브라우저가 종료될 때 까지 유지되어야한다.
				//					--> Session을 활용함 

				//					5.응답 데이터 문서 타입 지정
				response.setContentType("text/html; charset=UTF-8");


				//					6.Session 객체를 얻어와 로그인 정보를 추가한다.
				HttpSession session = request.getSession();

				//					6-1. 로그인이 성공 했을때만 Session에 로그인 정보 추가하기



				if (loginMember != null) {

					//						6-2. 30분동안 동작이 없을 경우 Session을 만료 시킨다.
					session.setMaxInactiveInterval(60 * 30);
					//						session.setMaxInactiveInterval(60 * 1); // 테스트용 1분 후 만료
					//													 초단위로 작성해야한다.

					//						6-3. Session에 로그인 정보 추가
					session.setAttribute("loginMember", loginMember);

					//						cookie -> 사용자 컴퓨터에 저장된다.
					//						session -> 서버에 저장된다.
					//						서버가  접속한 브라우저 마다 session을 구분하는 방법
					//						-> 해당 브라우저의 쿠키 파일에 session을 구분할 수 있는 
					//							session id를 저장시켜 두었다가 접속 될 때 마다 쿠키에서 자동으로
					//								session id를 얻어간다.

					//						6_4. 아이디를 쿠키에 저장하기

					//						2) 쿠키 객체 생성
					Cookie cookie = new Cookie("saveId", memEmail);

					//						1) 아아디 저장 checkbox가 체크 되었는지 확인
					if (save != null) {

						//							3) 1주일 동안 쿠키가 유효하도록 설정 ( 쿠키 생명 주기 설정 )
						cookie.setMaxAge(60 * 60 * 24 * 7); // 초 단위 --> 7일로 세팅


					} else {
						//							4) 아이디 저장이 check가 안된 경우 기존에 있던 쿠키 파일 삭제
						cookie.setMaxAge(0); // 생성과 동시에 삭제
					}


					//						5) 쿠키 유효 디렉토리 지정
					cookie.setPath(request.getContextPath());
					//												/wsp

					//						6) 생성된 쿠키를 클라이언트로 전달(응답)
					response.addCookie(cookie);

				} else {
					//						7. 로그인이 실패했을 때 "아이디 또는 비밀번호를 확인해주세요."라고 경고창 띄우기
					//						-> Session에 "로그인 실패"라는 문자열을 담아서 리다이렉트하기 
					//						session.setAttribute("text", "아이디 또는 비밀번호를 확인해주세요.");

					//						sweet alert 사용하기
					session.setAttribute("swalIcon", "error");
					session.setAttribute("swalTitle", "로그인 실패");
					session.setAttribute("swalText", "아이디 또는 비밀번호를 확인해주세요.");

				}

				if(loginMember.getMemGrade().equals("B") ) {
					session.setAttribute("swalIcon", "error");
					session.setAttribute("swalTitle", "로그인 실패");
					session.setAttribute("swalText", "불량 회원으로 등록된 아이디 입니다.");

				}




				//					* forward 같은 경우에는 이동하는 페이지로
				//						request, response 객체를 그대로 위임하고 , 주소를 위임 전 주소로 유지한다.

				//					* redirect는 이전 request, response를 폐기하고 새롭게 만들어서 지정된 주소로 새로운 요청을 보낸다.
				//						응답하는 화면을 만드는것이 아닌 경로를 지정해주는 (방향성을 잡아주는)역할
				//					-> 새롭게 요청을 보내기 때문에 이전 요청 주소가 아닌 새로운 요청 주소가 주소창에 나타난다.

				//					redirect 방식을 이용하여 로그인을 요청했던 페이지로 이동
				//					referer : 사이트 방문 흔적
				//					request.getHeader("referer") : 요청 전 페이지 주소가 담겨있다.
				response.sendRedirect(request.getHeader("referer"));


			}

			//			로그아웃 ***************************************
			else if(command.equals("/logout.do")) {
				//				세션 만료(세션 무효화)
				request.getSession().invalidate();

				//				로그아웃 후 메인 또는 로그아웃을 수행한 페이지로 리다이렉트
				response.sendRedirect(request.getContextPath()); // 메인

			}




			//			마이페이지 조회 **********************************
			else if (command.equals("/myPage.do")) {
				errorMsg = "마이 페이지로 이동하는 과정에서 오류 발생";


				path="/WEB-INF/views/member/myPage.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);

			}

			//			비밀번호 변경 페이지 *****************************
			else if (command.equals("/changePwd.do")) {
				errorMsg = "비밀번호 변경 페이지로 이동하는 과정에서 오류 발생";

				path="/WEB-INF/views/member/changePwd.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);

			}

			//			비밀번호 변경 ***********************************
			else if (command.equals("/updatePwd.do")) {
				errorMsg = "비밀번호 변경 과정에서 오류 발생";

				//				현재 비밀번호, 새로운 비밀번호 , 회원번호(식별자)
				//				changePwd.jsp에 현재 비밀번호의 id
				String currentPwd = request.getParameter("currentPwd");
				//				changePwd.jsp에 새로운 비밀번호의 id
				String newPwd = request.getParameter("newPwd1");

				//회원번호를 가져오기 위한 현재 로그인한 회원의 정보를 얻어온다.
				HttpSession session = request.getSession();
				Member loginMember = (Member) session.getAttribute("loginMember");


				loginMember.setMemPwd(currentPwd);
				//비지니스 로직 처리 후 결과 반환 받기
				int result = new MemberService().updatePwd(loginMember, newPwd);

				//경고창을 띄우기 위해 변수 설정
				swalIcon = null;
				swalTitle = null;

				if (result > 0) { // 비밀번호 변경 성공
					swalIcon = "success";
					swalTitle = "비밀번호가 변경되었습니다.";
				} else if (result == 0) { // 비밀번호 변경 실패
					swalIcon = "error";
					swalTitle = "비밀번호 변경이 실패되었습니다.";

				} else { // 현재 비밀번호 불일치
					swalIcon = "warning";
					swalTitle = "현재 비밀번호가 일치하지 않습니다.";
				}

				session.setAttribute("swalIcon", swalIcon);
				session.setAttribute("swalTitle", swalTitle);

				response.sendRedirect(request.getHeader("referer"));
				//비밀번호 변경 시 요청을 했던 페이지로 다시 돌아가라 .

			}
			//			회원 탈퇴  페이지******************************
			else if(command.equals("/secession.do")) {

				errorMsg = "탈퇴 페이지로 이동하는 과정에서 오류 발생";


				path="/WEB-INF/views/member/secession.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}



			//			회원 탈퇴 ***********************************
			else if (command.equals("/updateStatus.do")) {
				errorMsg = "회원탈퇴 페이지로 이동하는 과정에서 오류 발생";

				//				현재 비밀번호
				String currentPwd = request.getParameter("currentPwd");
				//				회원번호를 가져오기 위한 현재 로그인한 회원의 정보를 얻어온다. ( 식별자 )
				HttpSession session = request.getSession();
				Member loginMember = (Member) session.getAttribute("loginMember");
				//				loginMember 객체에 현재 비밀번호 세팅
				loginMember.setMemPwd(currentPwd);



				int result = new MemberService().updateStatus(loginMember);

				//					경고창을 띄우기 위해 변수 설정
				swalIcon = null;
				swalTitle = null;
				String url = null;

				if (result > 0) { // 탈퇴 성공
					swalIcon = "success";
					swalTitle = "탈퇴 성공";

					//						로그아웃 == 세션 무효화
					session.invalidate();
					//						세션 무효화 시 현재 얻어온 세션을 사용할 수 없는 문제점이 있다.
					//						-> 새로운 세션 얻어오기 
					session = request.getSession();
					url = request.getContextPath();


				} else if (result == 0) { // 탈퇴 실패
					swalIcon = "error";
					swalTitle = "탈퇴 실패";
					//						탈퇴 페이지 유지
					url = "secession.do";


				} else { // 현재 비밀번호 불일치
					swalIcon = "warning";
					swalTitle = "현재 비밀번호가 일치하지 않습니다.";
					//						탈퇴 페이지 유지
					url = "secession.do";
				}

				session.setAttribute("swalIcon", swalIcon);
				session.setAttribute("swalTitle", swalTitle);

				response.sendRedirect(url);

			}

		} catch(Exception e) {
			e.printStackTrace();
			path = "/WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("errorMsg", errorMsg);
			view = request.getRequestDispatcher(path);
			view.forward(request,response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

