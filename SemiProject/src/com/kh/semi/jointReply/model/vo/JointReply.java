package com.kh.semi.jointReply.model.vo;

import java.sql.Timestamp;

public class JointReply {
	
	private int replyNo; 		 		// 댓글 번호
	private String replyContent; 		// 댓글 내용
	private Timestamp replyCreateDate;	// 댓글 작성일
	private int parentBoardNo;			// 댓글이 작성된 게시글 번호
	private String memName;			// 댓글 작성 회원
	private String replyStatus;			// 댓글 상태

	
	public JointReply() {}


	public JointReply(int replyNo, String replyContent, Timestamp replyCreateDate, int parentBoardNo, String memName,
			String replyStatus) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.replyCreateDate = replyCreateDate;
		this.parentBoardNo = parentBoardNo;
		this.memName = memName;
		this.replyStatus = replyStatus;
	}


	public int getReplyNo() {
		return replyNo;
	}


	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}


	public String getReplyContent() {
		return replyContent;
	}


	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}


	public Timestamp getReplyCreateDate() {
		return replyCreateDate;
	}


	public void setReplyCreateDate(Timestamp replyCreateDate) {
		this.replyCreateDate = replyCreateDate;
	}


	public int getParentBoardNo() {
		return parentBoardNo;
	}


	public void setParentBoardNo(int parentBoardNo) {
		this.parentBoardNo = parentBoardNo;
	}


	public String getMemName() {
		return memName;
	}


	public void setMemName(String memName) {
		this.memName = memName;
	}


	public String getReplyStatus() {
		return replyStatus;
	}


	public void setReplyStatus(String replyStatus) {
		this.replyStatus = replyStatus;
	}


	@Override
	public String toString() {
		return "FreeReply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", replyCreateDate="
				+ replyCreateDate + ", parentBoardNo=" + parentBoardNo + ", memName=" + memName + ", replyStatus="
				+ replyStatus + "]";
	}



	
	
	

}
