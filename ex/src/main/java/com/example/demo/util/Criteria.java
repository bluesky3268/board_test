package com.example.demo.util;

import lombok.Getter;

@Getter
public class Criteria {    

	private int pageNum;
	private int startPage;
	private int limit;
	private int endPage;
	private int size;
	private int totalPage;
	
	public Criteria() {
		this(1, 10);
	}
	
	public Criteria(int pageNum, int size) {
		this.pageNum = pageNum;
		this.size = size;
	}
	
	public void setStartPage(int page) {
		this.startPage = ((page - 1) / 10) * 10 + 1;
	}

	public void setEndPage(int startPage) {
		this.endPage = startPage + this.size - 1;
	}
	
	public void setLimit(int page) {
		this.limit = (page - 1) * 10;
	}
	
	public void setTotalPage(int totalCount) {
		int tpage = totalCount / size;
		if(totalCount % size > 0) {
			tpage++;
		}
		this.totalPage = tpage;
	}
}
