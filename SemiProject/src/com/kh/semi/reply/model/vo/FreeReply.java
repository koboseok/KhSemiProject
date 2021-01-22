package com.kh.semi.reply.model.vo;

import java.sql.Timestamp;

public class FreeReply {
	
	private int fReplyNo; 		 		// 댓글 번호
	private String fReplyContent; 		// 댓글 내용
	private Timestamp fReplyCreateDate;	// 댓글 작성일
	private int fBoardNo;			// 댓글이 작성된 게시글 번호
	private String memName;			// 댓글 작성 회원
	private String fReplyFL;			// 댓글 상태
	private int memNo;
	
	
	public FreeReply(){}

	public FreeReply(int fReplyNo, String fReplyContent, Timestamp fReplyCreateDate, int fBoardNo, String memName,
			String fReplyFL) {
		super();
		this.fReplyNo = fReplyNo;
		this.fReplyContent = fReplyContent;
		this.fReplyCreateDate = fReplyCreateDate;
		this.fBoardNo = fBoardNo;
		this.memName = memName;
		this.fReplyFL = fReplyFL;
	}

	public int getfReplyNo() {
		return fReplyNo;
	}

	public void setfReplyNo(int fReplyNo) {
		this.fReplyNo = fReplyNo;
	}

	public String getfReplyContent() {
		return fReplyContent;
	}

	public void setfReplyContent(String fReplyContent) {
		this.fReplyContent = fReplyContent;
	}

	public Timestamp getfReplyCreateDate() {
		return fReplyCreateDate;
	}

	public void setfReplyCreateDate(Timestamp fReplyCreateDate) {
		this.fReplyCreateDate = fReplyCreateDate;
	}

	public int getfBoardNo() {
		return fBoardNo;
	}

	public void setfBoardNo(int fBoardNo) {
		this.fBoardNo = fBoardNo;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getfReplyFL() {
		return fReplyFL;
	}

	public void setfReplyFL(String fReplyFL) {
		this.fReplyFL = fReplyFL;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	@Override
	public String toString() {
		return "FreeReply [fReplyNo=" + fReplyNo + ", fReplyContent=" + fReplyContent + ", fReplyCreateDate="
				+ fReplyCreateDate + ", fBoardNo=" + fBoardNo + ", memName=" + memName + ", fReplyFL=" + fReplyFL
				+ ", memNo=" + memNo + "]";
	}
	
	
	
	

}
