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
	
	
	public Report() {
		// TODO Auto-generated constructor stub
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

	@Override
	public String toString() {
		return "Report [reportNo=" + reportNo + ", reportReason=" + reportReason + ", reportDt=" + reportDt
				+ ", reportBNo=" + reportBNo + ", reportBcNo=" + reportBcNo + ", reportMemNo=" + reportMemNo
				+ ", boardCode=" + boardCode + "]";
	}
	
	
}
