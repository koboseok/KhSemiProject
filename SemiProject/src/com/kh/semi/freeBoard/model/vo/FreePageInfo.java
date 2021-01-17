package com.kh.semi.freeBoard.model.vo;

public class FreePageInfo {  // 페이징 처리하는 vo
	
	// 얻어오기
	private int currentPage;	// 현재 페이지 번호를 저장할 변수
	private int listCount;		// 전체 게시글 수를 저장할 변수
	
	// 설정하기
	private int limit = 10;		// 한 페이지에 보여질 게시글 목록 수
	private int pageSize = 10;	// 페이징바에 표시될 페이지 수
	
	// 계산할 값
	private int maxPage;		// 전체 목록 페이지의수 == 마지막 페이지
	private int startPage;		// 페이징바 시작 페이지 번호
	private int endPage;		// 페이징바 끝 페이지 번호
	
	// 기본 생성자 사용 X
	
	
	// return 으로 전달받은 값 여기에 전달
	public FreePageInfo(int currentPage, int listCount) {
		super();
		this.currentPage = currentPage;
		this.listCount = listCount;
		
		// 전달받은 값과 명시적으로 선언된 값을 이용하여
		// makePageInfo() 수행
		makePageInfo();
		// makePageInfo();  --> 페이징 처리에 필요한 값을 계산하는 메소드
		
	}

	
	
	public FreePageInfo(int currentPage, int listCount, int limit, int pageSize) {
		super();
		this.currentPage = currentPage;
		this.listCount = listCount;
		this.limit = limit;
		this.pageSize = pageSize;
		
		makePageInfo();
	}

	
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
		makePageInfo();
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
		makePageInfo();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		makePageInfo();
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	@Override
	public String toString() {
		return "PageInfo [currentPage=" + currentPage + ", listCount=" + listCount + ", limit=" + limit + ", pageSize="
				+ pageSize + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	
	
	// 페이징 처리에 필요한 값을 계산하는 메소드
	private void makePageInfo() {
		
		// maxPage : 총 페이지 수 == 마지막 페이지
		maxPage =  (int)Math.ceil((double)listCount / limit);
		
		
		// startPage : 페이징바 시작 번호
		// 페이징바에 페이지를 10개씩 보여줄 경우 
		// 1, 11, 21, 31, ...
		// 현재 페이지 11p -> 시작 페이지 11
		// 현재 페이지 15p -> 시작 페이지 11
		// 현재 페이지 20p -> 시작 페이지 11
		startPage = (currentPage -1) / pageSize * pageSize + 1;
				 

		// endPage : 페이징바의 끝 번호
		// 페이징바에 페이지를 10개씩 보여줄 경우 
		// 10, 20, 30, 40, ....
		// 현재 페이지 11p -> 끝 페이지 20
		// 현재 페이지 15p -> 끝 페이지 20
		// 현재 페이지 20p -> 끝 페이지 20
		endPage = startPage + pageSize - 1;
		
		// 총 페이지의 수가 end페이지 보다 작을 경우
		if(maxPage <= endPage) {
			endPage = maxPage;
		}
		
	}
	
	
	
	
	
	
	
	

}
