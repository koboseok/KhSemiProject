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
	private int memberNo;
	private String memberName;
	private String memberGrade;
	


	private char boardCode;
	
	

	public FreeBoard() {
		
	}


	public FreeBoard(int fBoardNo, String fBoardTitle, String fBoardContent, Timestamp fCreateDate,
			Timestamp fModifyDate, int fReadCount, char fBoardFL, int memberNo, String memberName, char boardCode) {
		super();
		this.fBoardNo = fBoardNo;
		this.fBoardTitle = fBoardTitle;
		this.fBoardContent = fBoardContent;
		this.fCreateDate = fCreateDate;
		this.fModifyDate = fModifyDate;
		this.fReadCount = fReadCount;
		this.fBoardFL = fBoardFL;
		this.memberNo = memberNo;
		this.memberName = memberName;
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

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public char getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(char boardCode) {
		this.boardCode = boardCode;
	}


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberGrade() {
		return memberGrade;
	}


	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}

	@Override
	public String toString() {
		return "FreeBoard [fBoardNo=" + fBoardNo + ", fBoardTitle=" + fBoardTitle + ", fBoardContent=" + fBoardContent
				+ ", fCreateDate=" + fCreateDate + ", fModifyDate=" + fModifyDate + ", fReadCount=" + fReadCount
				+ ", fBoardFL=" + fBoardFL + ", memberNo=" + memberNo + ", memberName=" + memberName + ", memberGrade="
				+ memberGrade + ", boardCode=" + boardCode + "]";
	}

	
	
	
}



