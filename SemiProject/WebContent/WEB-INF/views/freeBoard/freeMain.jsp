
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유 게시판</title>
<style>

.pagination {
	justify-content: center;
}

#searchForm {
	position: relative;
}

#free-table th {
	text-align: center;
}

#free-table td {
	text-align: center;
	 vertical-align: middle;
  /* vertical-align : inline, inline-block 요소에만 적용 가능(td는 inline-block)*/
}

.free-wrapper{
	min-height: 540px;
}

#free-table td:hover{
	cursor : pointer;
}

</style>

</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container my-5">
		
		<h1 style = "color : orange">자유 게시판</h1>
		
					<%-- 로그인이 되어있는 경우 --%>
		 	
		 	<c:if test ="${!empty loginMember}">
			<button type="button" class="btn btn-warning float-right" id="insertBtn" onclick="location.href = '${contextPath}/freeBoard/insertForm.do'">글쓰기</button>
			</c:if>
		 	
			<div class="free-wrapper">
					
		
			
			
		 			<!-- 검색창 -->
			<div class="my-5">
				
				<form action="freeBoard/search.do" method="GET" class="text-center" id="searchForm">
				
					<select name="sk" class="form-control" style="width: 100px; display: inline-block;">
						<option value="title">글제목</option>
						<option value="content">내용</option>
						<option value="titcont">제목+내용</option>
						<option value="writer">작성자</option>
					</select>
					<input type="text" name="sv" class="form-control" style="width: 25%; display: inline-block;">
					<button class="btn btn-warning" style="width: 100px; display: inline-block;">검색</button>
				</form>
			</div>
		
		
				<table class="table table-hover my-5" id="free-table">
					<thead>
						<tr>
							<th>글번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>조회수</th>
							<th>작성일</th>
						</tr>
					</thead>
					
					<%-- 게시글 목록 출력 --%>
					<tbody>
					
						<c:choose>
							<c:when test="${empty fList}">
								<tr>
									<td colspan="5">존재하는 게시글이 없습니다.</td>
								</tr>
							</c:when> 
								
								
							<c:otherwise>	
									
								<c:forEach var="board" items="${fList}">
								
									<tr>
										<td>
										${board.fBoardNo }
										</td>
										<td>${board.fBoardTitle }</td>
										<td>${board.memName }</td>
										<td>${board.fReadCount }</td>
										<td>
											<%-- 날짜 출력 모양 지정 --%>
											<fmt:formatDate var="createDate" value="${board.fCreateDate }"  pattern="yyyy-MM-dd"/>                          
											<fmt:formatDate var="today" value="<%= new java.util.Date()  %>"  pattern="yyyy-MM-dd"/>                          
											<c:choose>
												<%-- 글 작성일이 오늘이 아닐 경우 --%>
												<c:when test="${createDate != today}">
													${createDate}
												</c:when>
												
												<%-- 글 작성일이 오늘일 경우 --%>
												<c:otherwise>
													<fmt:formatDate value="${board.fCreateDate}"  pattern="HH:mm"/>                          
												</c:otherwise>
											</c:choose>
										
										</td>
										
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				
			
			</div>
		
			
		 	
			<!-- -------------------- Pagination -------------------- -->
		
			<c:choose>
				<%-- 검색으로 만들어진 페이지? --%>
				<c:when test="${!empty param.sk && !empty param.sv}">
					<c:url var="pageUrl" value="freeBoard/search.do"/>
					
				<!-- 	쿼리스트링으로 사용할 내용을 변수에 저장 -->
					<c:set var="searchStr" value="&sk=${param.sk}&sv=${param.sv}" />
				</c:when>
			
				<c:otherwise>
					<c:url var="pageUrl" value="${contextPath}/freeBoard/main.do"/>
				</c:otherwise>
			</c:choose>
			
			
			
		
			<c:set var="firstPage" value="${pageUrl}?cp=1${searchStr}"/>
			<c:set var="lastPage" value="${pageUrl}?cp=${fPInfo.maxPage}${searchStr}"/>
			
		
			<fmt:parseNumber var="c1" value="${(fPInfo.currentPage - 1) / 10 }"  integerOnly="true" />
			<fmt:parseNumber var="prev" value="${ c1 * 10 }"  integerOnly="true" />
			<c:set var="prevPage" value="${pageUrl}?cp=${prev}${searchStr}" />
			
			
			<fmt:parseNumber var="c2" value="${(fPInfo.currentPage + 9) / 10 }" integerOnly="true" />
			<fmt:parseNumber var="next" value="${ c2 * 10 + 1 }" integerOnly="true" />
			<c:set var="nextPage" value="${pageUrl}?cp=${next}${searchStr}" />
			
			
			<div class="my-5">
				<ul class="pagination">
				
					<%--<< < --%>
					<c:if test="${fPInfo.currentPage > 10}">
						<li> 
							<a class="page-link" href="${firstPage}">&lt;&lt;</a>
						</li>
						
						<li> 
							<a class="page-link" href="${prevPage}">&lt;</a>
						</li>
					</c:if>
					
					  
					 
					 
					 
					 
					<!-- 페이지 목록 -->
			 		<c:forEach var="page" begin="${fPInfo.startPage}" end="${fPInfo.endPage}" >
						<c:choose>
							<c:when test="${fPInfo.currentPage == page }">
								<li>
									<a class="page-link">${page}</a>
								</li>
							</c:when>
						
							<c:otherwise>
								<li>	
									<a class="page-link" href="${pageUrl}?cp=${page}${searchStr}">${page}</a>
								</li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					
					
					<%--> >> --%>
					 <c:if test="${next <= fPInfo.maxPage}">
						<li> 
							<a class="page-link" href="${nextPage}">&gt;</a>
						</li>
						
						<li> 
							<a class="page-link" href="${lastPage}">&gt;&gt;</a>
						</li>
						
					</c:if>
				</ul>
			</div>
		
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>


	<script>
		// 게시글 상세보기 기능 (jquery를 통해 작업)
		
		
		
		$("#free-table td").on("click", function(){
			
			
			// 게시글 번호 얻어오기
			var fBoardNo = $(this).parent().children().eq(0).text();
			
			var url = "${contextPath}/freeBoard/view.do?cp=${fPInfo.currentPage}&no=" + fBoardNo ;
			
			location.href = url;
			
			
			
		});
		
		
		
		// 검색 내용이 있을 경우 검색창에 해당 내용을 작성해두는 기능
		 (function(){
			var searchKey = "${param.sk}"; 
			
			var searchValue = "${param.sv}";
			
			// 검색창 select의 option을 반복 접근
			$("select[name=sk] > option").each(function(index, item){
				// index : 현재 접근중인 요소의 인덱스
				// item : 현재 접근중인 요소
							// content            content
				if( $(item).val() == fsk){
					$(item).prop("selected", true);
				}
			});		
			
			// 검색어 입력창에 searcValue 값 출력
			$("input[name=sv]").val(searchValue);
		})();
		  
		
		
		
		
		
		
	</script>
</body>
</html>
