package com.kh.semi.freeBoard.model.vo;

public class FreeAttachment {
	private int fileNo;
	private String filePath;
	private String fileName;
	private int fileLevel;
	private int parentBoardNo;
	
	
	public FreeAttachment() {}
	
	
	

//	게시글에 포함된 이미지 목록 조회용
	
	public FreeAttachment(int fileNo, String fileName, int fileLevel) {
		super();
		this.fileNo = fileNo;
		this.fileName = fileName;
		this.fileLevel = fileLevel;
	}





	public FreeAttachment(int fileNo, String filePath, String fileName, int fileLevel, int parentBoardNo) {
		super();
		this.fileNo = fileNo;
		this.filePath = filePath;
		this.fileName = fileName;
		this.fileLevel = fileLevel;
		this.parentBoardNo = parentBoardNo;
	}


	public int getFileNo() {
		return fileNo;
	}


	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public int getFileLevel() {
		return fileLevel;
	}


	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}


	public int getParentBoardNo() {
		return parentBoardNo;
	}


	public void setParentBoardNo(int parentBoardNo) {
		this.parentBoardNo = parentBoardNo;
	}


	@Override
	public String toString() {
		return "Attachment [fileNo=" + fileNo + ", filePath=" + filePath + ", fileName=" + fileName + ", fileLevel="
				+ fileLevel + ", parentBoardNo=" + parentBoardNo + "]";
	}
	
	
	

}
