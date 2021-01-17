package com.kh.semi.subscribe.model.vo;

public class Subscribe {

	private int SubNo;
	private String SubName;
	private String CategoryName;
	private String SubImage;
	
	
	public Subscribe() {
		
	}


	public Subscribe(int subNo, String subName, String categoryName, String subImage) {
		super();
		SubNo = subNo;
		SubName = subName;
		CategoryName = categoryName;
		SubImage = subImage;
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


	@Override
	public String toString() {
		return "Subscribe [SubNo=" + SubNo + ", SubName=" + SubName + ", CategoryName=" + CategoryName + ", SubImage="
				+ SubImage + "]";
	}
	
	
	
}
