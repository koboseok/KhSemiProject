package com.kh.semi.admin.model.vo;

import java.sql.Date;

public class Report {
	private int reportNo;
	private String reportReason;
	private Date reportDt;
	private int reportBNo;
	private String reportBcNo;
	private int reportMemNo;
	private String boardCode;
	private String memNm;
	private String memEmail;
	
	public Report() {
		// TODO Auto-generated constructor stub
	}
	
	public Report(String reportReason, Date reportDt) {
		super();
		this.reportReason = reportReason;
		this.reportDt = reportDt;
	}



	public Report(String reportReason, Date reportDt, int reportBNo, String reportBcNo, int reportMemNo,
			String boardCode) {
		super();
		this.reportReason = reportReason;
		this.reportDt = reportDt;
		this.reportBNo = reportBNo;
		this.reportBcNo = reportBcNo;
		this.reportMemNo = reportMemNo;
		this.boardCode = boardCode;
	}

	public Report(int reportNo, String reportReason, Date reportDt, int reportBNo, String reportBcNo, int reportMemNo,
			String boardCode) {
		super();
		this.reportNo = reportNo;
		this.reportReason = reportReason;
		this.reportDt = reportDt;
		this.reportBNo = reportBNo;
		this.reportBcNo = reportBcNo;
		this.reportMemNo = reportMemNo;
		this.boardCode = boardCode;
	}
	
	
	
	public Report(int reportNo, String reportReason, Date reportDt, int reportBNo, String reportBcNo, int reportMemNo,
			String boardCode, String memNm, String memEmail) {
		super();
		this.reportNo = reportNo;
		this.reportReason = reportReason;
		this.reportDt = reportDt;
		this.reportBNo = reportBNo;
		this.reportBcNo = reportBcNo;
		this.reportMemNo = reportMemNo;
		this.boardCode = boardCode;
		this.memNm = memNm;
		this.memEmail = memEmail;
	}



	public int getReportNo() {
		return reportNo;
	}

	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}

	public String getReportReason() {
		return reportReason;
	}

	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}

	public Date getReportDt() {
		return reportDt;
	}

	public void setReportDt(Date reportDt) {
		this.reportDt = reportDt;
	}

	public int getReportBNo() {
		return reportBNo;
	}

	public void setReportBNo(int reportBNo) {
		this.reportBNo = reportBNo;
	}

	public String getReportBcNo() {
		return reportBcNo;
	}

	public void setReportBcNo(String reportBcNo) {
		this.reportBcNo = reportBcNo;
	}

	public int getReportMemNo() {
		return reportMemNo;
	}

	public void setReportMemNo(int reportMemNo) {
		this.reportMemNo = reportMemNo;
	}

	public String getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}
	
	public String getMemNm() {
		return memNm;
	}

	public void setMemNm(String memNm) {
		this.memNm = memNm;
	}

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	@Override
	public String toString() {
		return "Report [reportNo=" + reportNo + ", reportReason=" + reportReason + ", reportDt=" + reportDt
				+ ", reportBNo=" + reportBNo + ", reportBcNo=" + reportBcNo + ", reportMemNo=" + reportMemNo
				+ ", boardCode=" + boardCode + ", memNm=" + memNm + ", memEmail=" + memEmail + "]";
	}


	
	
}
