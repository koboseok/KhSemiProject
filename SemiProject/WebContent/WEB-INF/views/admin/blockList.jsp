<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<script src="https://kit.fontawesome.com/955b087c12.js"
	crossorigin="anonymous"></script>
<style>

.pagination {
	justify-content: center;
}

#searchForm {
	position: relative;
}

#searchForm>* {
	top: 0;
}

#list-table th {
	text-align: center;
}

#list-table td:not(:nth-of-type(3)) {
	text-align: center;
}

.list-wrapper {
	min-height: 540px;
}

#list-table td:hover {
	cursor: pointer;
}

#list-table td {
	vertical-align: middle;
}

.th-lg {
	width: 15%;
}

td {
text-align:center;
}
</style>

</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container my-5">

		<h2 class="entry-title text-center py-5">
			<span class="title-font">불량회원관리</span>
		</h2>

		<button class="btn btn-secondary btn-sm" id="toBlockBtn">일반회원으로 되돌리기</button>

		<div class="list-wrapper">
			<table class="table table-hover table-striped my-3" id="list-table">
				<thead>
					<tr>
						<th></th>
						<th>No.</th>
						<th>회원번호</th>
						<th>닉네임</th>
						<th>이메일</th>
						<th>신고테이블</th>
						<th>게시글/댓글</th>
						<th>신고글번호</th>
						<th>신고사유</th>
						<th>신고날짜</th>
					</tr>
				</thead>

				<%-- 게시글 목록 출력 --%>
				<tbody>
					<tr>
						<td><input type="radio"></td>
						<td>1</td>
						<td>51</td>
						<td>badUser01</td>
						<td>badUser1@naver.com</td>
						<td>010-0000-0000</td>
						<td>자유게시판</td>
						<td>3110</td>
						<td>욕설/도배/소액사기</td>
						<td>2021-01-13</td>

					</tr>
					<%-- <c:choose>
						<c:when test="${empty mList}">
							<tr>
								<td colspan="10">존재하는 회원이 없습니다.</td>
							</tr>
						</c:when>
						조회된 게시글 목록이 있을 때
						<c:otherwise>
							<c:forEach var="board" items="">--%>

					<%--</c:forEach>
						</c:otherwise>
					</c:choose> --%>
				</tbody>
			</table>
		</div>



		<%-- 			<%---------------------- Pagination ----------------------
			페이징 처리 주소를 쉽게 사용할 수 있도록 미리 변수에 저장
			<c:choose>
				검색 내용이 파라미터에 존재할 때 == 검색을 통해 만들어진 페이지일 때
				<c:when test="${!empty param.sk && !empty param.sv}">
					<c:url var="pageUrl" value="/search.do"/>
					쿼리스트링으로 사용할 내용을 변수에 저장
					<c:set var="searchStr" value="&sk=${param.sk}&sv=${param.sv}"/>
				</c:when>
				<c:otherwise>
					<c:url var="pageUrl" value="/board/list.do"/>
				</c:otherwise>
			</c:choose>
			

			<!-- 화살표에 들어갈 주소를 변수로 생성 -->		
			
			검색을 안 했을 때 : /board/list.do?cp=1
			검색을 했을 때 : /search.do?cp=1&sk=title&sv=49	
			<c:set var="firstPage" value="${pageUrl}?cp=1${searchStr}"/>
			<c:set var="lastPage" value="${pageUrl}?cp=${pInfo.maxPage}${searchStr}"/>
			
			EL을 사용한 숫자 연산의 단점: 연산이 자료형의 영향을 받지 않는다.
			<fmt:parseNumber> : 숫자 형태를 지정하여 변수 선언 
				integerOnly="true" : 정수로만 숫자 표현 (소수점 버림)
			
			<fmt:parseNumber var="c1" value="${(pInfo.currentPage-1)/pInfo.pageSize}" integerOnly="true"/>
			<fmt:parseNumber var="prev" value="${c1 * 10}" integerOnly="true"/>
			<c:set var="prevPage" value="${pageUrl}?cp=${prev}${searchStr}"/>
			
			prevPage 0이 나와도 괜찮은 이유: 10페이지 초과인 경우에 if가 걸려있어서
			
			<fmt:parseNumber var="c2" value="${(pInfo.currentPage+9) /10}" integerOnly="true"/>
			<fmt:parseNumber var="next" value="${c2*10+1}"/>
			<c:set var="nextPage" value="${pageUrl}?cp=${next}${searchStr}"/>
			
			<div class="my-5">
				<ul class="pagination">
				현재 페이지가 10페이지 초과인 경우
				
					<c:if test="${pInfo.currentPage>10}">
						<li> 첫 페이지로 이동 (<<)
							<a class="page-link" href="${firstPage}">&lt;&lt;</a>
						</li>
						<li> 이전 페이지의 가장 마지막 번호로 이동 (<)
							<a class="page-link" href="${prevPage}">&lt;</a>
						</li>
					</c:if>

				<!-- 페이지 목록 -->
				현재 페이지 == 이동페이지 같을 때 a태그의 href 속성을 없앰
					<c:forEach var="page" begin="${pInfo.startPage}" end="${pInfo.endPage}">
						<c:choose>
							<c:when test="${pInfo.currentPage==page}">
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
						<li> 다음 페이지로 이동 (>)
							<a class="page-link" href="${nextPage}">&gt;</a>
						</li>
						<li> 마지막 페이지로 이동 (>>)
							<a class="page-link" href="${lastPage}">&gt;&gt;</a>
						</li>
					</c:if>
				</ul>
			</div>
		
		 --%>
		<!-- 검색창 -->
		<div class="my-5"></div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>


	<script>
	   
	      
	      
	</script>
</body>
</html>
