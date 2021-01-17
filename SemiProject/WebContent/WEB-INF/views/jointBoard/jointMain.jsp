
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공동 구매 게시판</title>
<style>

.pagination {
	justify-content: center;
}

#searchForm {
	position: relative;
}

#joint-table th {
	text-align: center;
}

#joint-table td {
	text-align: center;
	 vertical-align: middle;
  /* vertical-align : inline, inline-block 요소에만 적용 가능(td는 inline-block)*/
}

.joint-wrapper{
	min-height: 540px;
}

#joint-table td:hover{
	cursor : pointer;
}

</style>

</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container my-5">
		
		<h1 style = "color : orange">공동구매 게시판</h1>
		
			<div class="joint-wrapper">
					
				<!-- 검색창 -->
			<div class="my-5">
				
				<form action="${contextPath }/search.do" method="GET" class="text-center" id="searchForm">
				
					<select name="fsk" class="form-control" style="width: 100px; display: inline-block;">
						<option value="title">글제목</option>
						<option value="content">내용</option>
						<option value="titcont">제목+내용</option>
						<option value="writer">작성자</option>
					</select>
					<input type="text" name="fsv" class="form-control" style="width: 25%; display: inline-block;">
					<button class="btn btn-warning" style="width: 100px; display: inline-block;">검색</button>
				</form>
			</div>
			
			
					<%-- 로그인이 되어있는 경우 --%>
		<%-- 	<c:if test="${!empty  }"> --%>
				<button type="button" class="btn btn-warning float-right" id="insertBtn"
				 onclick="location.href = '${contextPath}/jointBoard/insertForm.do'">글쓰기</button>
		<%-- 	</c:if> --%>
		
		
				<table class="table table-hover my-5" id="joint-table">
					<thead>
						<tr>
							<th>글번호</th>
							<td>카테고리</td>
							<th>제목</th>
							<th>작성자</th>
							<th>조회수</th>
							<th>작성일</th>
						</tr>
					</thead>
					
					<%-- 게시글 목록 출력 --%>
					<tbody>
					
						<%-- <c:choose>
					 <c:when test="${empty }">
								<tr>
									<td colspan="6">존재하는 게시글이 없습니다.</td>
								</tr>
							</c:when> 
							
							
							<c:otherwise>	조회된 게시글 목록이 있을 때
								
								<c:forEach var="board" items=""> --%>
									<tr>
										<td>글번호</td>
										<td>카테고리</td>
										<td>제목</td>
										<td>작성자</td>
										<td>조회수</td>
										<td>
											<%-- 날짜 출력 모양 지정 --%>
									<%-- 		<fmt:formatDate var="createDate" value=""  pattern="yyyy-MM-dd"/>                          
											<fmt:formatDate var="today" value="<%=  %>"  pattern="yyyy-MM-dd"/>                          
											
											<c:choose>
												글 작성일이 오늘이 아닐 경우
												<c:when test="${ != today}"> --%>
													작성일
												<%-- </c:when>
												
												글 작성일이 오늘일 경우
												<c:otherwise>
													<fmt:formatDate value=""  pattern="HH:mm"/>                          
												</c:otherwise>
											</c:choose> --%>
										
										</td>
										
									</tr>
				<%-- 				</c:forEach>
							</c:otherwise>
						</c:choose>
					 --%>
					</tbody>
				</table>
			</div>
		
			
		<%-- 	
			-------------------- Pagination --------------------
			페이징 처리 주소를 쉽게 사용할 수 있도록 미리 변수에 저장
			<c:choose>
				검색 내용이 파라미터에 존재할 때   ==  검색을 통해 만들어진 페이지인가? 
				<c:when test="${!empty  }">
					<c:url var="pageUrl" value=""/>
					
					쿼리스트링으로 사용할 내용을 변수에 저장
					<c:set var="searchStr" value="&sk=${param.sk}&sv=${param.sv}" />
				</c:when>
			
				<c:otherwise>
					<c:url var="pageUrl" value="/board/list.do"/>
				</c:otherwise>
			</c:choose>
			
			
			
			<!-- 화살표에 들어갈 주소를 변수로 생성 -->
			
				검색을 안했을 때 : /board/list.do?cp=1
				검색을 했을 때 : /search.do?cp=1&sk=title&sv=49
			
			
			<c:set var="firstPage" value="${pageUrl}?cp=1${searchStr}"/>
			<c:set var="lastPage" value="${pageUrl}?cp=${pInfo.maxPage}${searchStr}"/>
			
			EL을 이용한 숫자 연산의 단점 : 연산이 자료형에 영향을 받지 않는다
			
				<fmt:parseNumber>   : 숫자 형태를 지정하여 변수 선언 
				integerOnly="true"  : 정수로만 숫자 표현 (소수점 버림)
			
			
			<fmt:parseNumber var="c1" value="${(pInfo.currentPage - 1) / 10 }"  integerOnly="true" />
			<fmt:parseNumber var="prev" value="${ c1 * 10 }"  integerOnly="true" />
			<c:set var="prevPage" value="${pageUrl}?cp=${prev}${searchStr}" />
			
			
			<fmt:parseNumber var="c2" value="${(pInfo.currentPage + 9) / 10 }" integerOnly="true" />
			<fmt:parseNumber var="next" value="${ c2 * 10 + 1 }" integerOnly="true" />
			<c:set var="nextPage" value="${pageUrl}?cp=${next}${searchStr}" />
			
			
			<div class="my-5">
				<ul class="pagination">
				
					현재 페이지가 10페이지 초과인 경우
					<c:if test="${pInfo.currentPage > 10}">
						<li> <!-- 첫 페이지로 이동(<<) -->
							<a class="page-link" href="${firstPage}">&lt;&lt;</a>
						</li>
						
						<li> <!-- 이전 페이지로 이동 (<) -->
							<a class="page-link" href="${prevPage}">&lt;</a>
						</li>
					</c:if>
					
					 --%>
					 
					 
					 
					 
					<!-- 페이지 목록 -->
			<%-- 		<c:forEach var="page" begin="${pInfo.startPage}" end="${pInfo.endPage}" >
						<c:choose>
							<c:when test="${pInfo.currentPage == page }">
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
					</c:forEach> --%>
					
					
					<%-- 다음 페이지가 마지막 페이지 이하인 경우 --%>
					<%-- <c:if test="${next <= pInfo.maxPage}">
						<li> <!-- 다음 페이지로 이동 (>) -->
							<a class="page-link" href="${nextPage}">&gt;</a>
						</li>
						
						<li> <!-- 마지막 페이지로 이동(>>) -->
							<a class="page-link" href="${lastPage}">&gt;&gt;</a>
						</li>
						
					</c:if> --%>
				

				</ul>
			</div>
		
		
		
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>


	<script>
		// 게시글 상세보기 기능 (jquery를 통해 작업)
		
		$("#joint-table td").on("click", function(){
			
		/* 	// 게시글 번호 얻어오기
			var boardNo = $(this).parent().children().eq(0).text();
			//console.log(boardNo); */
			
			var url = "${contextPath}/jointBoard/view.do";
			
			location.href = url;
			
		});
		
		
		
		// 검색 내용이 있을 경우 검색창에 해당 내용을 작성해두는 기능
		/* (function(){
			var searchKey = "${param.fsk}"; 
			
			var searchValue = "${param.fsv}";
			
			// 검색창 select의 option을 반복 접근
			$("select[name=fsk] > option").each(function(index, item){
				// index : 현재 접근중인 요소의 인덱스
				// item : 현재 접근중인 요소
							// content            content
				if( $(item).val() == fsk){
					$(item).prop("selected", true);
				}
			});		
			
			// 검색어 입력창에 searcValue 값 출력
			$("input[name=fsv]").val(searchValue);
		})();
		 */
		
		
		
		
		
		
	</script>
</body>
</html>
