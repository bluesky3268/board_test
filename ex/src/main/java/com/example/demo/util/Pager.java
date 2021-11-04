//package com.example.demo.util;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Getter
//@NoArgsConstructor
//public class Pager {
//	private Criteria criteria;
//    private int totalCount; // 전체 데이터 수
//    private int startPage; // 현재 페이지 블럭의 시작페이지
//    private int endPage; // 현재 페이지 블럭의 마지막 페이지
//    private boolean next;
//    private boolean prev;
//    private int displayPageNum = 5;
//
//    public Criteria getCriteria() {
//	    return criteria;
//	}
//	public void setCriteria(Criteria criteria) {
//	    this.criteria = criteria;
//	}
//	public int getTotalCount() {
//	    return totalCount;
//	}
//	public void setTotalCount(int totalCount) {
//	    this.totalCount = totalCount;
//	    calcData();
//	}
//	
//	private void calcData() {
//	    
//	    endPage = (int) (Math.ceil(criteria.getPage() / (double) displayPageNum) * displayPageNum);
//	
//	    startPage = (endPage - displayPageNum) + 1;
//	    if(startPage <= 0) startPage = 1;
//	    
//	    int tempEndPage = (int) (Math.ceil(totalCount / (double) criteria.getDataNum()));
//	    if (endPage > tempEndPage) {
//	        endPage = tempEndPage;
//	    }
//	
//	    prev = startPage == 1 ? false : true;
//	    next = endPage * criteria.getDataNum() < totalCount ? true : false;
//	    
//	}
//	
//
//	public void setStartPage(int startPage) {
//	    this.startPage = startPage;
//	}
//
//	public void setEndPage(int endPage) {
//	    this.endPage = endPage;
//	}
//	public boolean isPrev() {
//	    return prev;
//	}
//	public void setPrev(boolean prev) {
//	    this.prev = prev;
//	}
//	public boolean isNext() {
//	    return next;
//	}
//	public void setNext(boolean next) {
//	    this.next = next;
//	}
//
//	public void setDisplayPageNum(int displayPageNum) {
//	    this.displayPageNum = displayPageNum;
//	}
//
//	
//	
//}