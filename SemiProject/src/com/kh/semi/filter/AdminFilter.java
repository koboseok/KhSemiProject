package com.kh.semi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.member.model.vo.Member;


@WebFilter(urlPatterns= {"/admin/*"})
public class AdminFilter implements Filter {


    public AdminFilter() {}
    public void init(FilterConfig fConfig) throws ServletException {}
	public void destroy() {}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		HttpSession session = req.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		if(loginMember == null || !loginMember.getMemGrade().contentEquals("A")) {
			//로그인이 되어있지 않거나 회원 등급이 "A"가 아닌 경우
			//메인 페이지로 강제 이동
			res.sendRedirect(req.getContextPath());
		} else {
		chain.doFilter(request, response);
		}
	}


}