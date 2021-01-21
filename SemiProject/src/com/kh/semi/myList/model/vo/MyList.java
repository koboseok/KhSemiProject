package com.kh.semi.myList.model.vo;

import java.sql.Date;

public class MyList {
	private int memNo;
	private String servCode;
	private String servNm;
	private Date startDt;
	private Date endDt;
	private int price;
	private String servImg;
	private String startDt2;
	
	public MyList() {
		// TODO Auto-generated constructor stub
	}
	

	public MyList(int memNo, String servCode, String servNm, Date startDt, Date endDt, int price, String servImg,
			String startDt2) {
		super();
		this.memNo = memNo;
		this.servCode = servCode;
		this.servNm = servNm;
		this.startDt = startDt;
		this.endDt = endDt;
		this.price = price;
		this.servImg = servImg;
		this.startDt2 = startDt2;
	}


	public MyList(int memNo, String servCode, String servNm, Date startDt, Date endDt, int price, String servImg) {
		super();
		this.memNo = memNo;
		this.servCode = servCode;
		this.servNm = servNm;
		this.startDt = startDt;
		this.endDt = endDt;
		this.price = price;
		this.servImg = servImg;
	}
	

	public String getStartDt2() {
		return startDt2;
	}


	public void setStartDt2(String startDt2) {
		this.startDt2 = startDt2;
	}


	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public String getServCode() {
		return servCode;
	}

	public void setServCode(String servCode) {
		this.servCode = servCode;
	}

	public String getServNm() {
		return servNm;
	}

	public void setServNm(String servNm) {
		this.servNm = servNm;
	}

	public Date getStartDt() {
		return startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public Date getEndDt() {
		return endDt;
	}

	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getServImg() {
		return servImg;
	}

	public void setServImg(String servImg) {
		this.servImg = servImg;
	}

	@Override
	public String toString() {
		return "MyList [memNo=" + memNo + ", servCode=" + servCode + ", servNm=" + servNm + ", startDt=" + startDt
				+ ", endDt=" + endDt + ", price=" + price + ", servImg=" + servImg + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
