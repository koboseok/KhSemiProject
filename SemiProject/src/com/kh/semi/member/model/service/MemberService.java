package com.kh.semi.member.model.service;

import static com.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.semi.admin.model.vo.Report;
import com.kh.semi.member.model.dao.MemberDAO;
import com.kh.semi.member.model.vo.MemSubscribe;
import com.kh.semi.member.model.vo.Member;

public class MemberService {

	private MemberDAO dao = new MemberDAO();

	/** 회원가입 service
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member member) throws Exception {
		int result = 0;

		Connection conn = getConnection();

		//회원 가입 메소드
		result = dao.signUp(conn, member);
		
		if(result>0) commit(conn); else rollback(conn);

		close(conn);

		return result;
	}

	/** 비밀번호 변경 Service
	 * @param loginMember
	 * @param newPwd
	 * @return result
	 */
	public int updatePwd(Member loginMember, String newPwd) throws Exception  {
		Connection conn = getConnection();
		//		1) 현재 비밀번호가 일치하는지 검사
		int result = dao.checkCurrentPwd(conn, loginMember);

		//		2) 현재 비밀번호 일치 시 새 비밀번호로 수정
		if (result > 0) { // 현재 비밀번호 일치

			//			loginMember 비밀번호 필드에 newPwd를 세팅하여 재활용
			loginMember.setMemPwd(newPwd);

			result = dao.updatePwd(conn, loginMember);

			if (result > 0)
				commit(conn);
			else
				rollback(conn);

		} else { // 현재 비밀번호 불일치
			result = -1;

		}

		close(conn);

		return result;
		//		-1 , 0 , 1
	}

	/** 회원 탈퇴 Service
	 * @param loginMember
	 * @return result
	 * @throws Exception
	 */
	public int updateStatus(Member loginMember) throws Exception {
		Connection conn = getConnection();

		//		1) 현재 비밀번호가 일치하는지 검사
		int result = dao.checkCurrentPwd(conn, loginMember);

		//		2) 현재 비밀번호 일치 시 상태 변경 진행
		if (result > 0) {

			result = dao.updateStatus(conn, loginMember);

			if (result > 0)
				commit(conn);
			else
				rollback(conn);

		} else { // 현재 비밀번호 불일치
			result = -1;
		}
		close(conn);

		return result;

	}

	/** 로그인 Service
	 * @param member
	 * @return loginMember
	 * @throws Exception
	 */
	public Member loginMember(Member member) throws Exception {
		//		1) Connection 얻어오기

		Connection conn = getConnection();

		//		2) DAO 메소드를 수행하여 결과 반환 받기

		Member loginMember = dao.loginMember(conn, member);

		//		3) Connection 반환하기
		close(conn);

		//		4) DAO 수행 결과를 Controller로 반환하기

		return loginMember;
	}

	/** 이메일 중복 검사 Service
	 * @param email
	 * @return result
	 * @throws Exception
	 */
	public int emailDupCheck(String email) throws Exception {
		Connection conn = getConnection();
		int result = dao.emailDupCheck(conn, email);
		close(conn);

		return result;
	}

	/** 별명 중복 검사 Service
	 * @param name
	 * @return result
	 * @throws Exception
	 */
	public int nameDupCheck(String name) throws Exception {
		Connection conn = getConnection();
		int result = dao.nameDupCheck(conn, name);
		close(conn);

		return result;
	}

	/** 서비스명으로 서비스 코드를 찾아오는 Service
	 * @param serviceName
	 * @return servCode
	 * @throws Exception
	 */
	public int getServCode(String serviceName) throws Exception {
		Connection conn = getConnection();
		int servCode = dao.getServCode(conn, serviceName);
		close(conn);
		return servCode;
	}

	/** 가입 성공시 이메일로 회원 번호를 가져오는 Service
	 * @param memEmail
	 * @return memNo
	 * @throws Exception
	 */
	public int getMemNo(String memEmail) throws Exception {
		Connection conn = getConnection();
		int memNo = dao.getMemNo(conn, memEmail);
		close(conn);
		return memNo;
	}

	/**가입 시 구독 목록 등록 Service2
	 * @param list
	 * @return result
	 * @throws Exception
	 */
	public int insertMemSub(List<MemSubscribe> list) throws Exception {
		Connection conn = getConnection();
		int result = dao.insertMemSub(conn, list);
		
		if(result>0) commit(conn); 
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/**정지 회원 사유 출력 service
	 * @param memNo
	 * @return
	 * @throws Exception
	 */
	public Report getReportReason(int memNo) throws Exception {
		Connection conn = getConnection();
		Report report = dao.getReportReason(conn, memNo);
		close(conn);
		return report;
	}

	/** 회원 정보 수정
	 * @param member
	 * @return resulk
	 * @throws Exception
	 */
	public int updateMember(Member member) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.updateMember(conn , member);
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		return result;
	}
}
