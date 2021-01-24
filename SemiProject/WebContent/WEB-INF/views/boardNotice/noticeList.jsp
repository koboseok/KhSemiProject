<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<style>

@font-face {
	font-family: 'SDSamliphopangche_Outline';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts-20-12@1.0/SDSamliphopangche_Outline.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

#boardStyle{
	  font-family:'SDSamliphopangche_Outline';
}

.pagination {
	justify-content: center;
}

#searchForm {
	position: relative;
}


#list-table th {
	text-align: center;
}

#list-table td:not(:nth-of-type(3)) {
	text-align: center;
}

.list-wrapper{
	min-height: 540px;
}

#list-table td:hover{
	cursor : pointer;
}

#list-table tbody tr:hover{
	background-color : orange;
	color : white;

}



/* 세로 가운데 정렬*/
#list-table td{
  vertical-align: middle;
  /* vertical-align : inline, inline-block 요소에만 적용 가능(td는 inline-block)*/
}
</style>

</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container my-5">
		
		<h1>
		<span id="boardStyle">
		공지 사항
		</span>
		</h1>
		
		
			<div class="list-wrapper">
				<table class="table table-hover table-striped my-5" id="list-table">
					<thead>
						<tr id="boardStyle">
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
							<c:when test="${empty list }">
								<tr>
									<td colspan="5">존재하는 게시글이 없습니다.</td>
								</tr>
							</c:when>
							
							
							<c:otherwise>	<%-- 조회된 게시글 목록이 있을 때 --%>
								
								<c:forEach var="notice" items="${list}">
									<tr>
										<td>${notice.noticeNo}</td>
										
										<td>
											
											
											
											
											${notice.noticeTitle}
										</td>
										
										
										<td>${notice.memNm}</td>
										<td>${notice.readCount}</td>
										<td>
											<%-- 날짜 출력 모양 지정 --%>
											<fmt:formatDate var="createDate" value="${notice.noticeCreateDate}"  pattern="yyyy-MM-dd"/>                          
											${createDate}
										
										</td>
										
									</tr>
								</c:forEach>
							
							</c:otherwise>
						</c:choose>
					
					</tbody>
				</table>
			</div>


			<%-- 로그인이 되어있는 경우 --%>
			<c:if test="${!empty loginMember && loginMember.memGrade == 'A'}">
				<button type="button" class="btn btn-warning float-right" id="insertBtn"
				 onclick="location.href = '${contextPath}/notice/insertForm.do'" style = "background-color : orange">글쓰기</button>
			</c:if>
			
			
			<%---------------------- Pagination ----------------------%>
			<%-- 페이징 처리 주소를 쉽게 사용할 수 있도록 미리 변수에 저장 --%>
			<%-- <c:choose>
				검색 내용이 파라미터에 존재할 때   ==  검색을 통해 만들어진 페이지인가? 
				<c:when test="${!empty param.sk && !empty param.sv }">
					<c:url var="pageUrl" value="/notice/noticeSearch.do"/>
					
					쿼리스트링으로 사용할 내용을 변수에 저장
					<c:set var="searchStr" value="&sk=${param.sk}&sv=${param.sv}" />
				</c:when>
			
				<c:otherwise>
					<c:url var="pageUrl" value="/notice/list.do"/>
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
					
					<!-- 페이지 목록 -->
					<c:forEach var="page" begin="${pInfo.startPage}" end="${pInfo.endPage}" >
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
					</c:forEach>
					
					
					다음 페이지가 마지막 페이지 이하인 경우
					<c:if test="${next <= pInfo.maxPage}">
						<li> <!-- 다음 페이지로 이동 (>) -->
							<a class="page-link" href="${nextPage}">&gt;</a>
						</li>
						
						<li> <!-- 마지막 페이지로 이동(>>) -->
							<a class="page-link" href="${lastPage}">&gt;&gt;</a>
						</li>
						
					</c:if>
				

				</ul>
			</div> --%>
		
		
			<!-- 검색창 -->
			<div class="my-5">
				
				<form action="${contextPath }/notice/noticeSearch.do" method="GET" class="text-center" id="searchForm">
					<select name="sk" class="form-control" style="width: 100px; display: inline-block;">
						<option value="title">글제목</option>
						<option value="content">내용</option>
						<option value="titcont">제목+내용</option>
						<option value="writer">작성자</option>
					</select>
					<input type="text" name="sv" class="form-control" style="width: 25%; display: inline-block;">
					<button class="form-control btn btn-warning" style="width: 100px; display: inline-block; background-color : orange">검색</button>
				</form>

			</div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>


	<script>
		// 게시글 상세보기 기능 (jquery를 통해 작업)
		
		$("#list-table td").on("click", function(){
			
			// 게시글 번호 얻어오기
			var noticeNo = $(this).parent().children().eq(0).text();
			//console.log(boardNo);
			
			var url = "${contextPath}/notice/view.do?cp=${pInfo.currentPage}&no=" + noticeNo + "${searchStr}";
			
			location.href = url;
			
		});
		
		
		
		// 검색 내용이 있을 경우 검색창에 해당 내용을 작성해두는 기능
		(function(){
			var searchKey = "${param.sk}"; 
			// 파라미터 중 sk가 있을 경우   ex)  "49"
			// 파라미터 중 sk가 없을 경우   ex)  ""
			var searchValue = "${param.sv}";
			
			// 검색창 select의 option을 반복 접근
			$("select[name=sk] > option").each(function(index, item){
				// index : 현재 접근중인 요소의 인덱스
				// item : 현재 접근중인 요소
							// content            content
				if( $(item).val() == searchKey  ){
					$(item).prop("selected", true);
				}
			});		
			
			// 검색어 입력창에 searcValue 값 출력
			$("input[name=sv]").val(searchValue);
		})();
		
		
		
		
		
		
		
	</script>
</body>
</html>
