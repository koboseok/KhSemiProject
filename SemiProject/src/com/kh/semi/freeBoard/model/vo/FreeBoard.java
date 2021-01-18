package com.kh.semi.freeBoard.model.vo;

import java.sql.Timestamp;

public class FreeBoard {
	
	private int fBoardNo;
	private String fBoardTitle;
	private String fBoardContent;
	private Timestamp fCreateDate;
	private Timestamp fModifyDate;
	private int fReadCount;
	private char fBoardFL;
	private int memNo;
	private String memName;
	private String memGrade;
	


	private char boardCode;
	
	

	public FreeBoard() {
		
	}



	public FreeBoard(int fBoardNo, String fBoardTitle, String fBoardContent, Timestamp fCreateDate,
			Timestamp fModifyDate, int fReadCount, char fBoardFL, int memNo, String memName, String memGrade,
			char boardCode) {
		super();
		this.fBoardNo = fBoardNo;
		this.fBoardTitle = fBoardTitle;
		this.fBoardContent = fBoardContent;
		this.fCreateDate = fCreateDate;
		this.fModifyDate = fModifyDate;
		this.fReadCount = fReadCount;
		this.fBoardFL = fBoardFL;
		this.memNo = memNo;
		this.memName = memName;
		this.memGrade = memGrade;
		this.boardCode = boardCode;
	}



	public int getfBoardNo() {
		return fBoardNo;
	}



	public void setfBoardNo(int fBoardNo) {
		this.fBoardNo = fBoardNo;
	}



	public String getfBoardTitle() {
		return fBoardTitle;
	}



	public void setfBoardTitle(String fBoardTitle) {
		this.fBoardTitle = fBoardTitle;
	}



	public String getfBoardContent() {
		return fBoardContent;
	}



	public void setfBoardContent(String fBoardContent) {
		this.fBoardContent = fBoardContent;
	}



	public Timestamp getfCreateDate() {
		return fCreateDate;
	}



	public void setfCreateDate(Timestamp fCreateDate) {
		this.fCreateDate = fCreateDate;
	}



	public Timestamp getfModifyDate() {
		return fModifyDate;
	}



	public void setfModifyDate(Timestamp fModifyDate) {
		this.fModifyDate = fModifyDate;
	}



	public int getfReadCount() {
		return fReadCount;
	}



	public void setfReadCount(int fReadCount) {
		this.fReadCount = fReadCount;
	}



	public char getfBoardFL() {
		return fBoardFL;
	}



	public void setfBoardFL(char fBoardFL) {
		this.fBoardFL = fBoardFL;
	}



	public int getMemNo() {
		return memNo;
	}



	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}



	public String getMemName() {
		return memName;
	}



	public void setMemName(String memName) {
		this.memName = memName;
	}



	public String getMemGrade() {
		return memGrade;
	}



	public void setMemGrade(String memGrade) {
		this.memGrade = memGrade;
	}



	public char getBoardCode() {
		return boardCode;
	}



	public void setBoardCode(char boardCode) {
		this.boardCode = boardCode;
	}



	@Override
	public String toString() {
		return "FreeBoard [fBoardNo=" + fBoardNo + ", fBoardTitle=" + fBoardTitle + ", fBoardContent=" + fBoardContent
				+ ", fCreateDate=" + fCreateDate + ", fModifyDate=" + fModifyDate + ", fReadCount=" + fReadCount
				+ ", fBoardFL=" + fBoardFL + ", memNo=" + memNo + ", memName=" + memName + ", memGrade=" + memGrade
				+ ", boardCode=" + boardCode + "]";
	}
	
	

	
	
}



