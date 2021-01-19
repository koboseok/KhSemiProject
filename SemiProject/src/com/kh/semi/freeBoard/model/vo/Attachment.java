package com.kh.semi.freeBoard.model.vo;

import java.util.Date;

public class Attachment {
	private int imgNo;
	private String imgName;
	private int imgLevel;
	private Date imgDt;
	private String imgPath;
	private int boardNo;
	
	public Attachment() {
		// TODO Auto-generated constructor stub
	}

	
	
	public Attachment(int imgNo, String imgName, int imgLevel) {
		super();
		this.imgNo = imgNo;
		this.imgName = imgName;
		this.imgLevel = imgLevel;
	}



	public Attachment(int imgNo, String imgName, int imgLevel, Date imgDt, String imgPath, int boardNo) {
		super();
		this.imgNo = imgNo;
		this.imgName = imgName;
		this.imgLevel = imgLevel;
		this.imgDt = imgDt;
		this.imgPath = imgPath;
		this.boardNo = boardNo;
	}

	public int getImgNo() {
		return imgNo;
	}

	public void setImgNo(int imgNo) {
		this.imgNo = imgNo;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public int getImgLevel() {
		return imgLevel;
	}

	public void setImgLevel(int imgLevel) {
		this.imgLevel = imgLevel;
	}

	public Date getImgDt() {
		return imgDt;
	}

	public void setImgDt(Date imgDt) {
		this.imgDt = imgDt;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	@Override
	public String toString() {
		return "Attachment [imgNo=" + imgNo + ", imgName=" + imgName + ", imgLevel=" + imgLevel + ", imgDt=" + imgDt
				+ ", imgPath=" + imgPath + ", boardNo=" + boardNo + "]";
	}
	
	
}
