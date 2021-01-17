package com.kh.semi.member.model.service;

import static com.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.Map;

import com.kh.semi.member.model.dao.MemberDAO;
import com.kh.semi.member.model.vo.Member;

public class MemberService {

	private MemberDAO dao = new MemberDAO();
	
	/** 회원가입 service
	 * @param member
	 * @param service
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member member, Map<String, Object> service) throws Exception {
		int result = 0;
		
		Connection conn = getConnection();
		
		//회원 가입
		result = dao.signUp(conn, member);
		
		//가입 성공시 회원 구독 정보 입력 
		if(result>0) {
			result = dao.insertMemSub(conn, service, member.getMemNo());
			
			if(result>0) commit(conn);
			else rollback(conn);
		
		} else {
			result = -1;
		}
		
		close(conn);
		
		return result;
	}

}
