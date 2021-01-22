package com.kh.semi.subscribereply.model.vo;

public class Reply {
	
	private int ReplyNo;
	private String ReplyContent;
	private float Point;
	private int SubNo;
	private int MemberNo;
	
	public Reply() {
		
	}

	public Reply(int replyNo, String replyContent, int point, int subNo, int memberNo) {
		super();
		ReplyNo = replyNo;
		ReplyContent = replyContent;
		Point = point;
		SubNo = subNo;
		MemberNo = memberNo;
	}

	public int getReplyNo() {
		return ReplyNo;
	}

	public void setReplyNo(int replyNo) {
		ReplyNo = replyNo;
	}

	public String getReplyContent() {
		return ReplyContent;
	}

	public void setReplyContent(String replyContent) {
		ReplyContent = replyContent;
	}

	public float getPoint() {
		return Point;
	}

	public void setPoint(float point) {
		Point = point;
	}

	public int getSubNo() {
		return SubNo;
	}

	public void setSubNo(int subNo) {
		SubNo = subNo;
	}

	public int getMemberNo() {
		return MemberNo;
	}

	public void setMemberNo(int memberNo) {
		MemberNo = memberNo;
	}

	@Override
	public String toString() {
		return "Reply [ReplyNo=" + ReplyNo + ", ReplyContent=" + ReplyContent + ", Point=" + Point + ", SubNo=" + SubNo
				+ ", MemberNo=" + MemberNo + "]";
	}
	
	

}
