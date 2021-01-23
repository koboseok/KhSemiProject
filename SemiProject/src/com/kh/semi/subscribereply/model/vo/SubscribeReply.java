package com.kh.semi.subscribereply.model.vo;

public class SubscribeReply {
	
	private int ReplyNo;
	private String ReplyContent;
	private float Point;
	private int SubNo;
	private int MemberNo;
	private String MemberName;
	private String ServiceName;
	
	public SubscribeReply() {
		
	}

	public SubscribeReply(int replyNo, String replyContent, float point, int subNo, int memberNo, String memberName,
			String serviceName) {
		super();
		ReplyNo = replyNo;
		ReplyContent = replyContent;
		Point = point;
		SubNo = subNo;
		MemberNo = memberNo;
		MemberName = memberName;
		ServiceName = serviceName;
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

	public String getMemberName() {
		return MemberName;
	}

	public void setMemberName(String memberName) {
		MemberName = memberName;
	}

	public String getServiceName() {
		return ServiceName;
	}

	public void setServiceName(String serviceName) {
		ServiceName = serviceName;
	}

	@Override
	public String toString() {
		return "SubscribeReply [ReplyNo=" + ReplyNo + ", ReplyContent=" + ReplyContent + ", Point=" + Point + ", SubNo="
				+ SubNo + ", MemberNo=" + MemberNo + ", MemberName=" + MemberName + ", ServiceName=" + ServiceName
				+ "]";
	}




	

}
