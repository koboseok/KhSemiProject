<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공동 구매 게시판</title>
<style>
.boardTitle>img {
	width: 50px;
	height: 50px;
}

-


#joint-list-table th {
	text-align: center;
}

#free-list-table td:not(:nth-of-type(3)) {
	text-align: center;
}

.free-list-wrapper {
	min-height: 540px;
}

#free-list-table td {
	cursor: pointer;
}

#free-list-table td {
	vertical-align: middle;
}


</style>

</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container my-5" align="center">

		<h1>공동 구매 게시판</h1>

		<div class="my-5">
			<form action="" method="GET" class="text-center" id="searchForm">
				<select name="" class="form-control"
					style="width: 100px; display: inline-block;">
					<option value="title">글제목</option>
					<option value="content">내용</option>
					<option value="titcont">제목+내용</option>
					<option value="writer">작성자</option>
				</select> <input type="text" name="sv" class="form-control"
					style="width: 25%; display: inline-block;">
			<button type="button" class="btn btn-warning">검색</button>
			</form>


		</div>
		<div class="joint-list-wrapper">
				<button type="button" class="btn btn-warning btn btn-primary float-right" 
				id="insertBtn" onclick="location.href ='${contextPath}/jointBoard/insert.do'">글쓰기</button>
			<table class="table table-hover my-5" id="joint-list-table">
				<thead>
					<tr>
						<th>글번호</th>
						<th>카테고리</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
				</thead>

				<%-- 게시글 목록 출력 --%>
				<tbody>
					<tr>
						<td>글번호 나오는 곳</td>
						<td>카테고리 나오는 곳</td>
						<td class="boardTitle">제목 나오는 곳</td>
						<td>작성자 나오는 곳</td>
						<td>작성일 나오는 곳</td>
						<td>조회수 나오는 곳</td>
					</tr>


				</tbody>
			</table>
		</div>
	</div>
	
	
</body>
</html>
