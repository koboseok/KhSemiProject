<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<style>
.pagination {
	justify-content: center;
}

/* 검색창 스타일 */
#searchForm>* {
	top: 0;
	vertical-align: top;
}

select[name='searchKey'] {
	width: 100px;
	display: inline-block;
}

input[name='searchValue'] {
	width: 25%;
	display: inline-block;
}

#searchBtn {
	width: 100px;
	display: inline-block;
}

.list-wrapper {
	height: 540px;
}

/* 목록에 마우스를 올릴 경우 손가락 모양으로 변경 ㄴ*/
#list-table td:hover{
	cursor : pointer;
}
</style>

</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container my-5 ">

		<h1>공지사항</h1>

		<div class="list-wrapper">
		
			<div class="mb-5">
				<form action="search" method="GET" class="text-center"
					id="searchForm">
					<select name="searchKey" class="form-control">
						<option value="title">글제목</option>
						<option value="content">내용</option>
						<option value="titcont">제목+내용</option>
					</select> <input type="text" name="searchValue" class="form-control">
					<button class="form-control btn btn-warning" id="searchBtn">검색</button>
				</form>


			</div>
			<div>

			<table class="table table-hover table-striped my-5" id="list-table">
				<thead>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>조회수</th>
						<th>작성일</th>
					</tr>
				</thead>

				<tbody>
					<!-- 공지사항 목록 -->
					<%-- 공지사항이 존재할 때와 존재하지 않을때 맞는 출력 형식을 지정해야한다. --%>

					<c:choose>
						<c:when test="${empty list }">
							<tr>
								<td colspan="5" align = "center">존재하는 공지사항이 없습니다.</td>
							</tr>
						</c:when>
						<%-- 공지사항이 존재할 때 --%>
						<c:otherwise>
							<c:forEach var = "notice" items="${list}">
								<tr>
									<td>${notice.noticeNo }</td>
									<td>${notice.noticeTitle }</td>
									<td>${notice.memNm }</td>
									<td>${notice.readCount }</td>
									<td>${notice.noticeCreateDate}</td>
								</tr>	
							</c:forEach>
						</c:otherwise>
						
					</c:choose>
				</tbody>
			</table>
			</div>


		<%-- 로그인된 계정이 관리자 등급인 경우 --%>
		<c:if test="${!empty loginMember && loginMember.memGrade == 'A'}">
		<button type="button" class="btn btn-warning float-right"
			id="insertBtn" onclick="location.href = 'insertForm.do';">글쓰기</button>
		</c:if>
		
		<c:choose>
				<%-- 검색 내용이 파라미터에 존재할 때   ==  검색을 통해 만들어진 페이지인가?  --%>
				<c:when test="${!empty param.sk && !empty param.sv }">
					<c:url var="pageUrl" value="/freeboard/freeBoardSearch.do"/>
					
					<%-- 쿼리스트링으로 사용할 내용을 변수에 저장 --%>
					<c:set var="searchStr" value="&sk=${param.sk}&sv=${param.sv}" />
				</c:when>
			
				<c:otherwise>
					<c:url var="pageUrl" value="/freeBoard/list.do"/>
				</c:otherwise>
			</c:choose>
			
			
			
			<!-- 화살표에 들어갈 주소를 변수로 생성 -->
			<%-- 
				검색을 안했을 때 : /board/list.do?cp=1
				검색을 했을 때 : /search.do?cp=1&sk=title&sv=49
			--%>
			
			<c:set var="firstPage" value="${pageUrl}?cp=1${searchStr}"/>
			<c:set var="lastPage" value="${pageUrl}?cp=${pInfo.maxPage}${searchStr}"/>
			
			<%-- EL을 이용한 숫자 연산의 단점 : 연산이 자료형에 영향을 받지 않는다--%>
			<%-- 
				<fmt:parseNumber>   : 숫자 형태를 지정하여 변수 선언 
				integerOnly="true"  : 정수로만 숫자 표현 (소수점 버림)
			--%>
			
			<fmt:parseNumber var="c1" value="${(pInfo.currentPage - 1) / 10 }"  integerOnly="true" />
			<fmt:parseNumber var="prev" value="${ c1 * 10 }"  integerOnly="true" />
			<c:set var="prevPage" value="${pageUrl}?cp=${prev}${searchStr}" />
			
			
			<fmt:parseNumber var="c2" value="${(pInfo.currentPage + 9) / 10 }" integerOnly="true" />
			<fmt:parseNumber var="next" value="${ c2 * 10 + 1 }" integerOnly="true" />
			<c:set var="nextPage" value="${pageUrl}?cp=${next}${searchStr}" />
			
			
			<div class="my-5">
				<ul class="pagination">
				
					<%-- 현재 페이지가 10페이지 초과인 경우 --%>
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
					
					
					<%-- 다음 페이지가 마지막 페이지 이하인 경우 --%>
					<c:if test="${next <= pInfo.maxPage}">
						<li> <!-- 다음 페이지로 이동 (>) -->
							<a class="page-link" href="${nextPage}">&gt;</a>
						</li>
						
						<li> <!-- 마지막 페이지로 이동(>>) -->
							<a class="page-link" href="${lastPage}">&gt;&gt;</a>
						</li>
						
					</c:if>
				

				</ul>
			</div>



		
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>

	<script>
		// 공지사항 상세보기 기능 (jquery를 통해 작업)
		// list-table의 후손인 td가 클릭 되었을때 ( 광역 )
		$("#list-table td").on("click",function(){
			
			// 현재 클릭이 된 td 태그  == this // 부모의 자식 중 0번째 인덱스 (== 1번째 자식)에 있는 text() 값을 가져와라
			var noticeNo = $(this).parent().children().eq(0).text();
			// console.log(noticeNo);
			
			// 얻어온 공지사항 글번호를 쿼리스트링으로 작성하여 상세 조회 요청
			location.href = "${contextPath}/notice/view.do?no=" + noticeNo;
			
		});
		
		
	</script>


</body>
</html>
