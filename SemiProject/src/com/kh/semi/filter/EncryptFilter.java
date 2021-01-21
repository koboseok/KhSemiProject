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

import com.kh.semi.wrapper.EncryptWrapper;


@WebFilter(urlPatterns = {"/member/login.do", "/member/signUp.do", "/member/updatePwd.do", "/member/updateStatus.do"})
public class EncryptFilter implements Filter {

    public EncryptFilter() {}
	public void destroy() {}
	public void init(FilterConfig fConfig) throws ServletException {}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//비밀번호 암호화
		//입력된 비밀번호는 요청 데이터의 HttpservletRequest에 parameter로 전달함
		//Parameter의 가공이 필요한 경우 Wrapper클래스가 필요함
		
		HttpServletRequest req = (HttpServletRequest)request;
		
		//암호화 Wrapper객체 생성
		EncryptWrapper encWrapper = new EncryptWrapper(req);
		
		chain.doFilter(encWrapper, response);
	}



}
