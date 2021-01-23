package com.kh.semi.privateBoard.model.vo;

import java.sql.Timestamp;

public class Board {
	
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String memName;
	private int readCount;
	private String categoryName;
	private Timestamp boardCreateDate;
	private Timestamp boardModifyDate;
	private String boardStatus;
	
	
	public Board() {
		// TODO Auto-generated constructor stub
	}


	
	public Board(int boardNo, String boardTitle, String memName, int readCount, String categoryName,
			Timestamp boardCreateDate) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.memName = memName;
		this.readCount = readCount;
		this.categoryName = categoryName;
		this.boardCreateDate = boardCreateDate;
	}



	public Board(int boardNo, String boardTitle, String boardContent, String memName, int readCount,
			String categoryName, Timestamp boardCreateDate, Timestamp boardModifyDate, String boardStatus) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.memName = memName;
		this.readCount = readCount;
		this.categoryName = categoryName;
		this.boardCreateDate = boardCreateDate;
		this.boardModifyDate = boardModifyDate;
		this.boardStatus = boardStatus;
	}


	public int getBoardNo() {
		return boardNo;
	}


	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}


	public String getBoardTitle() {
		return boardTitle;
	}


	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}


	public String getBoardContent() {
		return boardContent;
	}


	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}


	public String getMemName() {
		return memName;
	}


	public void setMemName(String memName) {
		this.memName = memName;
	}


	public int getReadCount() {
		return readCount;
	}


	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public Timestamp getBoardCreateDate() {
		return boardCreateDate;
	}


	public void setBoardCreateDate(Timestamp boardCreateDate) {
		this.boardCreateDate = boardCreateDate;
	}


	public Timestamp getBoardModifyDate() {
		return boardModifyDate;
	}


	public void setBoardModifyDate(Timestamp boardModifyDate) {
		this.boardModifyDate = boardModifyDate;
	}


	public String getBoardStatus() {
		return boardStatus;
	}


	public void setBoardStatus(String boardStatus) {
		this.boardStatus = boardStatus;
	}


	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", memName=" + memName + ", readCount=" + readCount + ", categoryName=" + categoryName
				+ ", boardCreateDate=" + boardCreateDate + ", boardModifyDate=" + boardModifyDate + ", boardStatus="
				+ boardStatus + "]";
	}


	
	


}
