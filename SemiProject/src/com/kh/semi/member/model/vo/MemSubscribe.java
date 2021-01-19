package com.kh.semi.member.model.vo;

import java.sql.Date;

public class MemSubscribe {
	private int memNo;
	private int servCode;
	private Date servStDate;
	private Date servEndDate;
	private int price;
	
	public MemSubscribe() {
	}

	public MemSubscribe(int memNo, int servCode, Date servStDate, Date servEndDate, int price) {
		super();
		this.memNo = memNo;
		this.servCode = servCode;
		this.servStDate = servStDate;
		this.servEndDate = servEndDate;
		this.price = price;
	}

	public MemSubscribe(int memNo, int servCode, Date servStDate, int price) {
		super();
		this.memNo = memNo;
		this.servCode = servCode;
		this.servStDate = servStDate;
		this.price = price;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public int getServCode() {
		return servCode;
	}

	public void setServCode(int servCode) {
		this.servCode = servCode;
	}

	public Date getServStDate() {
		return servStDate;
	}

	public void setServStDate(Date servStDate) {
		this.servStDate = servStDate;
	}

	public Date getServEndDate() {
		return servEndDate;
	}

	public void setServEndDate(Date servEndDate) {
		this.servEndDate = servEndDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "MemSubscribe [memNo=" + memNo + ", servCode=" + servCode + ", servStDate=" + servStDate
				+ ", servEndDate=" + servEndDate + ", price=" + price + "]";
	}
	
	
}
