package com.kh.semi.navsubscribe.model.vo;

public class Subscribe {

	private int SubNo;
	private String SubName;
	private String CategoryName;
	private String SubImage;
	private String SubContent;
	
	
	public Subscribe() {
		
	}


	public Subscribe(int subNo, String subName, String categoryName, String subImage, String subContent) {
		super();
		SubNo = subNo;
		SubName = subName;
		CategoryName = categoryName;
		SubImage = subImage;
		SubContent = subContent;
	}
	
	


	public int getSubNo() {
		return SubNo;
	}


	public void setSubNo(int subNo) {
		SubNo = subNo;
	}


	public String getSubName() {
		return SubName;
	}


	public void setSubName(String subName) {
		SubName = subName;
	}


	public String getCategoryName() {
		return CategoryName;
	}


	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}


	public String getSubImage() {
		return SubImage;
	}


	public void setSubImage(String subImage) {
		SubImage = subImage;
	}


	public String getSubContent() {
		return SubContent;
	}


	public void setSubContent(String subContent) {
		SubContent = subContent;
	}


	@Override
	public String toString() {
		return "Subscribe [SubNo=" + SubNo + ", SubName=" + SubName + ", CategoryName=" + CategoryName + ", SubImage="
				+ SubImage + ", SubContent=" + SubContent + "]";
	}

	
	
	
}
