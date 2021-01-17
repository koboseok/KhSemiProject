<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공동 구매 게시판</title>
<style>
#board-area {
	min-height: 700px;
	margin-bottom: 100px;
}

#board-content {
	padding-bottom: 150px;
}

.boardImgArea {
	height: 300px;
}

.boardImg {
	width: 100%;
	height: 100%;
	max-width: 300px;
	max-height: 300px;
	margin: auto;
}

/* 이미지 화살표 색 조정
	-> fill='%23000' 부분의 000을
	   RGB 16진수 값을 작성하여 변경 가능 
	 */
.carousel-control-prev-icon {
	background-image:
		url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23000' viewBox='0 0 8 8'%3E%3Cpath d='M5.25 0l-4 4 4 4 1.5-1.5-2.5-2.5 2.5-2.5-1.5-1.5z'/%3E%3C/svg%3E")
		!important;
}

.carousel-control-next-icon {
	background-image:
		url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23000' viewBox='0 0 8 8'%3E%3Cpath d='M2.75 0l-1.5 1.5 2.5 2.5-2.5 2.5 1.5 1.5 4-4-4-4z'/%3E%3C/svg%3E")
		!important;
}

.replyWrite>table {
	width: 90%;
	align: center;
}

#replyContentArea {
	width: 90%;
}

#replyContentArea>textarea {
	resize: none;
	width: 100%;
}

#replyBtnArea {
	width: 100px;
	text-align: center;
}

#replyListArea {
	list-style-type: none;
}

.wdr {
	font-size: 0.9em;
}






/* 이미지 선택 색 변경*/
.carousel-indicators>li {
	background-color: #ccc !important;
}



</style>
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container  my-5">

		<div>

			<h1 style="color: orange">공동 구매 게시판</h1>

			<div id="board-area">

				<!--Board No.-->
				<br>
				<h5 style="color:gray ">No.</h5>
				<br>
				<h6 style="color: goldenrod ">카테고리</h6>
				<!-- Title -->
				<h2>제목</h2>

				<br>
				<!-- Writer -->
				<p class="wdr" style="color:gray ">
					작성자 &nbsp;|&nbsp;
					<!-- Date -->

					<span> 작성일 <%--  <fmt:formatDate value="" pattern="yyyy년 MM월 dd일 HH:mm:ss"/> --%>
						<%-- <br> 마지막 수정일 : <fmt:formatDate value="${board.boardModifyDate}" pattern="yyyy년 MM월 dd일 HH:mm:ss"/>  --%>
					</span> &nbsp;|&nbsp; <span>조회수</span>
				</p>
				<br>
				<hr>
				<div>
					<!-- 이미지 출력 -->
					<%-- 	<c:if test="${!empty  }">  
				<div class="carousel slide boardImgArea" id="board-image">
					
					<!-- 이미지 선택 버튼 -->
					<ol class="carousel-indicators">
						<c:forEach var="file" items="" varStatus="">
							
							<li data-slide-to="" data-target="#board-image"  
									<c:if test=""> class="active" </c:if> 
							>                      
							</li>
						
						</c:forEach>
					</ol>
					 --%>

					<!-- 출력되는 이미지 -->
					<div class="carousel-inner">
						<%-- 	<c:forEach var="file" items="${fList}" varStatus="vs"> --%>

						<div class="carousel-item <c:if test="">active</c:if>">

							<img class="d-block w-100 boardImg" id=""
								src="${contextPath}/resources/uploadImages/">
						</div>

						<%-- </c:forEach> --%>

					</div>

					<!-- 좌우 화살표 -->
					<!-- <a class="carousel-control-prev" href="#board-image" data-slide="prev"><span class="carousel-control-prev-icon"></span> <span class="sr-only">Previous</span></a> 
					<a class="carousel-control-next" href="#board-image" data-slide="next"><span class="carousel-control-next-icon"></span> <span class="sr-only">Next</span></a> -->
				</div>
				<%-- </c:if> --%>



				<!-- Content -->
				<div id="board-content">${board.boardContent }</div>


				<hr>



				<div class = "buttonArea">
					<%-- 로그인된 회원과 해당 글 작성자가 같은 경우--%>
					<%-- <c:if test="${!empty  }"> --%>
					<button id="deleteBtn"
						class="btn btn-danger btn btn-primary float-right">삭제</button>
					<%-- 게시글 수정 후 상세조회 페이지로 돌아오기 위한 url 조합 --%>
					<%-- <c:if test="${!empty }"> --%>
					<%-- 검색을 통해 들어온 상세 조회 페이지인 경우 --%>
					<%-- 	<c:set var="searchStr" value="" />
						</c:if>		 --%>

					<button type="button" id="modifyBnt"
						class="btn btn-secondary btn btn-primary float-right "
						onclick="location.href = '${contextPath}/jointBoard/updateForm.do'">수정</button>
					<%-- 	</c:if> --%>



					<%-- 		<c:choose>
						<c:when test="${!empty param.sk && !empty param.sv }">
							<c:url var="goToList" value="../search.do">
								<c:param name="cp">${param.cp}</c:param>
								<c:param name="sk">${param.sk}</c:param>
								<c:param name="sv">${param.sv}</c:param>
							</c:url>
						</c:when>
						
						<c:otherwise>
							<c:url var="goToList" value="list.do">
								<c:param name="cp">${param.cp}</c:param>
							</c:url>
						</c:otherwise>
					</c:choose> --%>


					<button type="button" id="listBtn"
						class="btn btn-warning btn btn-primary float-right"
						onclick="location.href = '${contextPath}/jointBoard/list.do'">목록으로</button>

				</div>
			</div>
		</div>

	</div>


<jsp:include page="../reply/jointReply.jsp"></jsp:include>


	<script>
		
	</script>
</body>
</html>
