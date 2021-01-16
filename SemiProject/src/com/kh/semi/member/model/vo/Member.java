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
	
	public Member() {
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

	@Override
	public String toString() {
		return "Member [memNo=" + memNo + ", memEmail=" + memEmail + ", memPwd=" + memPwd + ", memName=" + memName
				+ ", memPhone=" + memPhone + ", memGrade=" + memGrade + ", memStatus=" + memStatus + ", memCheck="
				+ memCheck + ", memEnrollDate=" + memEnrollDate + "]";
	}

	
}
