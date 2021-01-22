package com.kh.semi.jointBoard.model.vo;

import java.sql.Timestamp;

public class JointBoard {
	private int jBoardNo;
	private String jBoardTitle;
	private String jBoardContent;
	private Timestamp jCreateDate;
	private Timestamp jModifyDate;
	private int jReadCount;
	private char jBoardFL;
	private int memNo;
	private String memName;
	private String memGrade;
	private String boardCode;
	private String jCategoryName;

	JointBoard(){}

	public JointBoard(int jBoardNo, String jBoardTitle, String jBoardContent, Timestamp jCreateDate,
			Timestamp jModifyDate, int jReadCount, char jBoardFL, int memNo, String memName, String memGrade,
			String boardCode, String jCategoryName) {
		super();
		this.jBoardNo = jBoardNo;
		this.jBoardTitle = jBoardTitle;
		this.jBoardContent = jBoardContent;
		this.jCreateDate = jCreateDate;
		this.jModifyDate = jModifyDate;
		this.jReadCount = jReadCount;
		this.jBoardFL = jBoardFL;
		this.memNo = memNo;
		this.memName = memName;
		this.memGrade = memGrade;
		this.boardCode = boardCode;
		this.jCategoryName = jCategoryName;
	}

	public int getjBoardNo() {
		return jBoardNo;
	}

	public void setjBoardNo(int jBoardNo) {
		this.jBoardNo = jBoardNo;
	}

	public String getjBoardTitle() {
		return jBoardTitle;
	}

	public void setjBoardTitle(String jBoardTitle) {
		this.jBoardTitle = jBoardTitle;
	}

	public String getjBoardContent() {
		return jBoardContent;
	}

	public void setjBoardContent(String jBoardContent) {
		this.jBoardContent = jBoardContent;
	}

	public Timestamp getjCreateDate() {
		return jCreateDate;
	}

	public void setjCreateDate(Timestamp jCreateDate) {
		this.jCreateDate = jCreateDate;
	}

	public Timestamp getjModifyDate() {
		return jModifyDate;
	}

	public void setjModifyDate(Timestamp jModifyDate) {
		this.jModifyDate = jModifyDate;
	}

	public int getjReadCount() {
		return jReadCount;
	}

	public void setjReadCount(int jReadCount) {
		this.jReadCount = jReadCount;
	}

	public char getjBoardFL() {
		return jBoardFL;
	}

	public void setjBoardFL(char jBoardFL) {
		this.jBoardFL = jBoardFL;
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

	public String getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}

	public String getjCategoryName() {
		return jCategoryName;
	}

	public void setjCategoryName(String jCategoryName) {
		this.jCategoryName = jCategoryName;
	}

	@Override
	public String toString() {
		return "JointBoard [jBoardNo=" + jBoardNo + ", jBoardTitle=" + jBoardTitle + ", jBoardContent=" + jBoardContent
				+ ", jCreateDate=" + jCreateDate + ", jModifyDate=" + jModifyDate + ", jReadCount=" + jReadCount
				+ ", jBoardFL=" + jBoardFL + ", memNo=" + memNo + ", memName=" + memName + ", memGrade=" + memGrade
				+ ", boardCode=" + boardCode + ", jCategoryName=" + jCategoryName + ", getjBoardNo()=" + getjBoardNo()
				+ ", getjBoardTitle()=" + getjBoardTitle() + ", getjBoardContent()=" + getjBoardContent()
				+ ", getjCreateDate()=" + getjCreateDate() + ", getjModifyDate()=" + getjModifyDate()
				+ ", getjReadCount()=" + getjReadCount() + ", getjBoardFL()=" + getjBoardFL() + ", getMemNo()="
				+ getMemNo() + ", getMemName()=" + getMemName() + ", getMemGrade()=" + getMemGrade()
				+ ", getBoardCode()=" + getBoardCode() + ", getjCategoryName()=" + getjCategoryName() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	

	
	
}
