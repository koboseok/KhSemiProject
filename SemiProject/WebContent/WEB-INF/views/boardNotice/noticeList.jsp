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

/* 목록에 마우스를 올릴 경우 손가락 모양으로 변경*/
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

		<div class="my-5">
			<ul class="pagination">
				<li><a class="page-link" href="#">&lt;</a></li>
				<li><a class="page-link" href="#">1</a></li>
				<li><a class="page-link" href="#">2</a></li>
				<li><a class="page-link" href="#">3</a></li>
				<li><a class="page-link" href="#">4</a></li>
				<li><a class="page-link" href="#">5</a></li>
				<li><a class="page-link" href="#">&gt;</a></li>
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
