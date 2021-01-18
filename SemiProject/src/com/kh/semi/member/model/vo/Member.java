package com.kh.semi.member.model.vo;

import java.sql.Date;

public class Member {
	private int memNo; //회원번호
	private String memEmail; //이메일
	private String memPwd; //비밀번호
	private String memName; //별명
	private String memPhone; //전화번호
	private String memGrade; //등급
	private String memStatus; //상태
	private String memCheck; //인증
	private Date memEnrollDate; //가입일
	
	private String memSub1;
	private String memSub2;
	private String memSub3;
	private String memSub4;
	private String memSub5;
	
	

	public Member(int memNo, String memEmail, String memName, String memPhone) {
		super();
		this.memNo = memNo;
		this.memEmail = memEmail;
		this.memName = memName;
		this.memPhone = memPhone;
	}


	public Member(int memNo, String memEmail, String memPwd, String memName, String memPhone, String memGrade,
			String memStatus, String memCheck, Date memEnrollDate, String memSub1) {
		super();
		this.memNo = memNo;
		this.memEmail = memEmail;
		this.memPwd = memPwd;
		this.memName = memName;
		this.memPhone = memPhone;
		this.memGrade = memGrade;
		this.memStatus = memStatus;
		this.memCheck = memCheck;
		this.memEnrollDate = memEnrollDate;
		this.memSub1 = memSub1;
	}


	public Member(int memNo, String memEmail, String memPwd, String memName, String memPhone, String memGrade,
			String memStatus, String memCheck, Date memEnrollDate, String memSub1, String memSub2) {
		super();
		this.memNo = memNo;
		this.memEmail = memEmail;
		this.memPwd = memPwd;
		this.memName = memName;
		this.memPhone = memPhone;
		this.memGrade = memGrade;
		this.memStatus = memStatus;
		this.memCheck = memCheck;
		this.memEnrollDate = memEnrollDate;
		this.memSub1 = memSub1;
		this.memSub2 = memSub2;
	}


	public Member(int memNo, String memEmail, String memPwd, String memName, String memPhone, String memGrade,
			String memStatus, String memCheck, Date memEnrollDate, String memSub1, String memSub2, String memSub3) {
		super();
		this.memNo = memNo;
		this.memEmail = memEmail;
		this.memPwd = memPwd;
		this.memName = memName;
		this.memPhone = memPhone;
		this.memGrade = memGrade;
		this.memStatus = memStatus;
		this.memCheck = memCheck;
		this.memEnrollDate = memEnrollDate;
		this.memSub1 = memSub1;
		this.memSub2 = memSub2;
		this.memSub3 = memSub3;
	}


	public Member(int memNo, String memEmail, String memPwd, String memName, String memPhone, String memGrade,
			String memStatus, String memCheck, Date memEnrollDate, String memSub1, String memSub2, String memSub3,
			String memSub4) {
		super();
		this.memNo = memNo;
		this.memEmail = memEmail;
		this.memPwd = memPwd;
		this.memName = memName;
		this.memPhone = memPhone;
		this.memGrade = memGrade;
		this.memStatus = memStatus;
		this.memCheck = memCheck;
		this.memEnrollDate = memEnrollDate;
		this.memSub1 = memSub1;
		this.memSub2 = memSub2;
		this.memSub3 = memSub3;
		this.memSub4 = memSub4;
	}


	public Member(int memNo, String memEmail, String memPwd, String memName, String memPhone, String memGrade,
			String memStatus, String memCheck, Date memEnrollDate, String memSub1, String memSub2, String memSub3,
			String memSub4, String memSub5) {
		super();
		this.memNo = memNo;
		this.memEmail = memEmail;
		this.memPwd = memPwd;
		this.memName = memName;
		this.memPhone = memPhone;
		this.memGrade = memGrade;
		this.memStatus = memStatus;
		this.memCheck = memCheck;
		this.memEnrollDate = memEnrollDate;
		this.memSub1 = memSub1;
		this.memSub2 = memSub2;
		this.memSub3 = memSub3;
		this.memSub4 = memSub4;
		this.memSub5 = memSub5;
	}


	public Member() {
	}
	
	
//  로그인용
	public Member(int memNo, String memEmail, String memName, String memPhone, String memGrade, String memStatus,
			String memCheck, Date memEnrollDate) {
		super();
		this.memNo = memNo;
		this.memEmail = memEmail;
		this.memName = memName;
		this.memPhone = memPhone;
		this.memGrade = memGrade;
		this.memStatus = memStatus;
		this.memCheck = memCheck;
		this.memEnrollDate = memEnrollDate;
	}



	public Member(int memNo, String memEmail, String memPwd, String memName, String memPhone, String memGrade,
			String memStatus, String memCheck, Date memEnrollDate) {
		super();
		this.memNo = memNo;
		this.memEmail = memEmail;
		this.memPwd = memPwd;
		this.memName = memName;
		this.memPhone = memPhone;
		this.memGrade = memGrade;
		this.memStatus = memStatus;
		this.memCheck = memCheck;
		this.memEnrollDate = memEnrollDate;
	}

	

	public Member(String memEmail, String memPwd, String memName, String memPhone) {
		super();
		this.memEmail = memEmail;
		this.memPwd = memPwd;
		this.memName = memName;
		this.memPhone = memPhone;
	}

	
	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public String getMemPwd() {
		return memPwd;
	}

	public void setMemPwd(String memPwd) {
		this.memPwd = memPwd;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemPhone() {
		return memPhone;
	}

	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}

	public String getMemGrade() {
		return memGrade;
	}

	public void setMemGrade(String memGrade) {
		this.memGrade = memGrade;
	}

	public String getMemStatus() {
		return memStatus;
	}

	public void setMemStatus(String memStatus) {
		this.memStatus = memStatus;
	}

	public String getMemCheck() {
		return memCheck;
	}

	public void setMemCheck(String memCheck) {
		this.memCheck = memCheck;
	}

	public Date getMemEnrollDate() {
		return memEnrollDate;
	}

	public void setMemEnrollDate(Date memEnrollDate) {
		this.memEnrollDate = memEnrollDate;
	}
	
	public String getMemSub1() {
		return memSub1;
	}


	public void setMemSub1(String memSub1) {
		this.memSub1 = memSub1;
	}


	public String getMemSub2() {
		return memSub2;
	}


	public void setMemSub2(String memSub2) {
		this.memSub2 = memSub2;
	}


	public String getMemSub3() {
		return memSub3;
	}


	public void setMemSub3(String memSub3) {
		this.memSub3 = memSub3;
	}


	public String getMemSub4() {
		return memSub4;
	}


	public void setMemSub4(String memSub4) {
		this.memSub4 = memSub4;
	}


	public String getMemSub5() {
		return memSub5;
	}


	public void setMemSub5(String memSub5) {
		this.memSub5 = memSub5;
	}


	@Override
	public String toString() {
		return "Member [memNo=" + memNo + ", memEmail=" + memEmail + ", memPwd=" + memPwd + ", memName=" + memName
				+ ", memPhone=" + memPhone + ", memGrade=" + memGrade + ", memStatus=" + memStatus + ", memCheck="
				+ memCheck + ", memEnrollDate=" + memEnrollDate + ", memSub1=" + memSub1 + ", memSub2=" + memSub2
				+ ", memSub3=" + memSub3 + ", memSub4=" + memSub4 + ", memSub5=" + memSub5 + "]";
	}



	
}
