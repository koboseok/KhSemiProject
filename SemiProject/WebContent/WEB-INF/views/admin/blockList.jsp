<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>불량 회원 관리</title>
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
	text-align: center;
}
</style>

</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container my-5">

		<h2 class="entry-title text-center py-5">
			<span id="title-font">불량회원관리</span>
		</h2>

		<button class="btn btn-dark btn-sm" style="width: 165px"
			id="toRestoreBtn">일반회원으로 되돌리기</button>
		<a href="${contextPath}/admin/memberList.do">
			<button class="btn btn-dark btn-sm" style="width: 140px"
				id="MemberBtn">일반회원 관리</button>
		</a>
		<input type="hidden" name="selectMember" id="selectMember"
			value="false"> <a href="convertMember.do">
			<button class="btn btn-secondary btn-sm" id="toRestoreBtn1"
				style="display: none"></button>
		</a>
		
		
		<div class="list-wrapper">
			<table class="table table-hover table-striped my-3" id="list-table">
				<thead>
					<tr>
						<th></th>
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

					<c:choose>
						<c:when test="${empty bmList}">
							<tr>
								<td colspan="10">존재하는 회원이 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="bMember" items="${bmList}">
								<tr>
									<td><input type="radio" name="selectMem"></td>
									<td>${bMember.reportMemNo}</td>
									<td>${bMember.memNm}</td>
									<td>${bMember.memEmail}</td>
									<td><c:choose>
											<c:when test="${bMember.boardCode == 'F'}">
											자유 게시판
											</c:when>
											<c:when test="${bMember.boardCode == 'J'}">
											공동구매 게시판
											</c:when>
											<c:when test="${bMember.boardCode == 'P'}">
											건의 게시판
											</c:when>
											<c:otherwise>
											한 줄 평가
											</c:otherwise>
										</c:choose>
									</td>
									<td><c:choose>
											<c:when test="${bMember.reportBcNo == 'B'}">
											게시글
											</c:when>
											<c:otherwise>
											댓글
											</c:otherwise>
									</c:choose></td>
									<td>${bMember.reportBNo}</td>
									<td>${bMember.reportReason}</td>
									<td>${bMember.reportDt}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>

			
			<c:choose>
				<c:when test="${!empty param.sk && !empty param.sv}">
					<c:url var="pageUrl" value="/admin/bMemberSearch.do"/>
					<c:set var="searchStr" value="&sk=${param.sk}&sv=${param.sv}"/>
				</c:when>
				<c:otherwise>
					<c:url var="pageUrl" value="/admin/blockList.do"/>
				</c:otherwise>
			</c:choose>
			

			<c:set var="firstPage" value="${pageUrl}?cp=1${searchStr}"/>
			<c:set var="lastPage" value="${pageUrl}?cp=${pInfo.maxPage}${searchStr}"/>
			
			
			<fmt:parseNumber var="c1" value="${(pInfo.currentPage-1)/pInfo.pageSize}" integerOnly="true"/>
			<fmt:parseNumber var="prev" value="${c1 * 5}" integerOnly="true"/>
			<c:set var="prevPage" value="${pageUrl}?cp=${prev}${searchStr}"/>
						
			<fmt:parseNumber var="c2" value="${(pInfo.currentPage+4) /5}" integerOnly="true"/>
			<fmt:parseNumber var="next" value="${c2*5+1}"/>
			<c:set var="nextPage" value="${pageUrl}?cp=${next}${searchStr}"/>
			
			<div class="my-5">
				<ul class="pagination">				
					<c:if test="${pInfo.currentPage>5}">
						<li> 
							<a class="page-link" href="${firstPage}">&lt;&lt;</a>
						</li>
						<li> 
							<a class="page-link" href="${prevPage}">&lt;</a>
						</li>
					</c:if>

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


				<c:if test="${next <= pInfo.maxPage}">
						<li> 
							<a class="page-link" href="${nextPage}">&gt;</a>
						</li>
						<li> 
							<a class="page-link" href="${lastPage}">&gt;&gt;</a>
						</li>
					</c:if>
				</ul>
			</div>
		
			<!-- 검색창 -->
		<div class="my-5">
					<div class="my-5">
				<form action="${contextPath}/admin/bMemberSearch.do" method="GET" class="text-center" id="searchForm">
					<select name="sk" class="form-control" style="width: 120px; display: inline-block;">
						<option value="memNo">회원번호</option>
						<option value="memEmail">닉네임</option>
						<option value="memName">이메일</option>
					</select>
					<input type="text" name="sv" class="form-control" autocomplete="off" style="width: 25%; display: inline-block;">
					<button class="form-control btn btn-dark" style="width: 100px; display: inline-block;">검색</button>
				</form>
			</div>
		</div>
		
		
		
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>


	<script>
		var selectMemNo;

		$("#list-table td").on("click", function() {
			//td를 클릭하면 라디오박스 체크
			$(this).parent().children().eq(0).children().prop("checked", true);
			//td를 클릭하면 회원번호 저장
			selectMemNo = $(this).parent().children().eq(1).text();
			console.log(selectMemNo);
			//td를 클릭하면 불량회원으로 설정 가능하게 함
			$('#selectMember').prop('value', true);
		});

		//td가 눌리지 않았을 때 모달창 뜨지 않게 하기 + confirm창으로 등급 변경을 확인
		$("#toRestoreBtn").on("click", function() {
			if($('#selectMember').val() == "true") {
				var result = window.confirm("회원번호 " + selectMemNo + "번을 일반 회원으로 되돌리시겠습니까?");
				if(result) {
					$.ajax({
						url : "convertMember.do",
						data : {"selectMemNo" : selectMemNo},
						type : "post",
						success : function(result) {
							if(result>0) {
								swal({icon : "success", title : "일반 회원으로 전환 성공"})
								.then(function() {
									location.href = "memberList.do"
								});
							} else {
								swal({icon : "error", title : "일반 회원으로 전환 실패"})
								.then(function() {
									location.href = "memberList.do"
								});
							}
						},
						error : console.log("ajax 통신 실패")
					})
					//$('#toRestoreBtn1').click();
				}	
			}
		});
		
	</script>
</body>
</html>
