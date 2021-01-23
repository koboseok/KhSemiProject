<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 게시글 조회</title>
</head>
<style>
#content-main {
	height: 830px;
	margin-top: 100px;
	margin-bottom: 400px;
}

.lgImg {
	width: 500px;
	height: 160px;
}

.btn {
	position: relative;
	left: 620px;
}
</style>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>


	<div class="container" id="content-main">


		<div class="text-center py-5">
			<img src="${contextPath}/resources/images/My Page-logo.png"
				class="lgImg" />
		</div>


		<div class="row my-5">
			<jsp:include page="sideMenu.jsp"></jsp:include>

			<div class="col-sm-8">
				<h3 id="title-font">내 게시글 조회</h3>
				<div class="bg-white rounded shadow-sm container p-3">

					<!-- 아이디 -->
					<div class="row mb-3 form-row">
						<div class="col-md-3">
							<h6 id="title-font">자유 게시판</h6>
						</div>
					</div>
					<div class="col-md-6">
						<table class="table table-hover table-striped my-5"
							id="list-table" style="width: 700px;">
							<thead>
								<tr>
									<th>글번호</th>
									<th>카테고리</th>
									<th>제목</th>
									<th>작성자</th>
									<th>조회수</th>
									<th>작성일</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${empty bList}">
										<tr>
											<td colspan="6">존재하는 게시글이 없습니다.</td>
										</tr>
									</c:when>
									<%--조회된 게시글 목록이 있을 때 --%>
									<c:otherwise>
										<c:forEach var="board" items="${bList}">
											<tr>
												<td>${board.boardNo}</td>
												<td>${board.categoryName}</td>
												<td class="boardTitle">
													<%--썸네일 출력--%> <c:forEach var="thumbnail" items="${fList}">
														<%--현재 출력하려는 게시글 번호와 썸네일 목록 중 게시글 번호가 일치하는 썸네일 정보가 있다면--%>
														<c:if test="${board.boardNo == thumbnail.parentBoardNo}">
															<img
																src="${contextPath}/resources/uploadImages/${thumbnail.fileName}">
														</c:if>
													</c:forEach> ${board.boardTitle}
												</td>
												<td>${board.memName}</td>
												<td>${board.readCount}</td>
												<td>
													<%--날짜 출력 모양 지정--%> <fmt:formatDate var="createDate"
														value="${board.boardCreateDate}" pattern="yyyy-MM-dd" /> <fmt:formatDate
														var="today" value="<%=new java.util.Date()%>"
														pattern="yyyy-MM-dd" /> <c:choose>
														<%--글 작성일이 오늘이 아닌 경우 --%>
														<c:when test="${createDate != today}">
												${createDate}
												</c:when>

														<%--글 작성일이 오늘인 경우 --%>
														<c:otherwise>
															<fmt:formatDate value="${board.boardCreateDate}"
																pattern="HH:mm" />
															<%--var가 없어서 바로 출력 --%>
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

					<hr>
					<!-- 비밀번호 -->
					<div class="row mb-3 form-row">
						<div class="col-md-3">
							<h6 id="title-font">공동 구매 게시판</h6>
						</div>
					</div>
					<div class="col-md-6">
						<table class="table table-hover table-striped my-5"
							id="list-table" style="width: 700px;">
							<tr>
								<th>글번호</th>
								<th>카테고리</th>
								<th>제목</th>
								<th>작성자</th>
								<th>조회수</th>
								<th>작성일</th>
							</tr>
							
							<tbody>
								<c:choose>
									<c:when test="${empty bList}">
										<tr>
											<td colspan="6">존재하는 게시글이 없습니다.</td>
										</tr>
									</c:when>
									<%--조회된 게시글 목록이 있을 때 --%>
									<c:otherwise>
										<c:forEach var="board" items="${bList}">
											<tr>
												<td>${board.boardNo}</td>
												<td>${board.categoryName}</td>
												<td class="boardTitle">
													<%--썸네일 출력--%> <c:forEach var="thumbnail" items="${fList}">
														<%--현재 출력하려는 게시글 번호와 썸네일 목록 중 게시글 번호가 일치하는 썸네일 정보가 있다면--%>
														<c:if test="${board.boardNo == thumbnail.parentBoardNo}">
															<img
																src="${contextPath}/resources/uploadImages/${thumbnail.fileName}">
														</c:if>
													</c:forEach> ${board.boardTitle}
												</td>
												<td>${board.memName}</td>
												<td>${board.readCount}</td>
												<td>
													<%--날짜 출력 모양 지정--%> <fmt:formatDate var="createDate"
														value="${board.boardCreateDate}" pattern="yyyy-MM-dd" /> <fmt:formatDate
														var="today" value="<%=new java.util.Date()%>"
														pattern="yyyy-MM-dd" /> <c:choose>
														<%--글 작성일이 오늘이 아닌 경우 --%>
														<c:when test="${createDate != today}">
												${createDate}
												</c:when>

														<%--글 작성일이 오늘인 경우 --%>
														<c:otherwise>
															<fmt:formatDate value="${board.boardCreateDate}"
																pattern="HH:mm" />
															<%--var가 없어서 바로 출력 --%>
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

					<hr>
					<div class="row mb-3 form-row">
						<div class="col-md-3">
							<h6 id="title-font">비공개 건의 게시판</h6>
						</div>
					</div>
					<div class="col-md-6">
						<table class="table table-hover table-striped my-5"
							id="list-table" style="width: 700px;">
							<tr>
								<th>글번호</th>
								<th>카테고리</th>
								<th>제목</th>
								<th>작성자</th>
								<th>조회수</th>
								<th>작성일</th>
							</tr>
							
							<tbody>
								<c:choose>
									<c:when test="${empty bList}">
										<tr>
											<td colspan="6">존재하는 게시글이 없습니다.</td>
										</tr>
									</c:when>
									<%--조회된 게시글 목록이 있을 때 --%>
									<c:otherwise>
										<c:forEach var="board" items="${bList}">
											<tr>
												<td>${board.boardNo}</td>
												<td>${board.categoryName}</td>
												<td class="boardTitle">
													<%--썸네일 출력--%> <c:forEach var="thumbnail" items="${fList}">
														<%--현재 출력하려는 게시글 번호와 썸네일 목록 중 게시글 번호가 일치하는 썸네일 정보가 있다면--%>
														<c:if test="${board.boardNo == thumbnail.parentBoardNo}">
															<img
																src="${contextPath}/resources/uploadImages/${thumbnail.fileName}">
														</c:if>
													</c:forEach> ${board.boardTitle}
												</td>
												<td>${board.memName}</td>
												<td>${board.readCount}</td>
												<td>
													<%--날짜 출력 모양 지정--%> <fmt:formatDate var="createDate"
														value="${board.boardCreateDate}" pattern="yyyy-MM-dd" /> <fmt:formatDate
														var="today" value="<%=new java.util.Date()%>"
														pattern="yyyy-MM-dd" /> <c:choose>
														<%--글 작성일이 오늘이 아닌 경우 --%>
														<c:when test="${createDate != today}">
												${createDate}
												</c:when>

														<%--글 작성일이 오늘인 경우 --%>
														<c:otherwise>
															<fmt:formatDate value="${board.boardCreateDate}"
																pattern="HH:mm" />
															<%--var가 없어서 바로 출력 --%>
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

				</div>
			</div>

		</div>
	</div>








	<jsp:include page="../common/footer.jsp"></jsp:include>
<body>

</body>
</html>