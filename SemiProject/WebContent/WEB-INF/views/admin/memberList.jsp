<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
<style>
input[type="number"]::-webkit-outer-spin-button, input[type="number"]::-webkit-inner-spin-button
	{
	-webkit-appearance: none;
	margin: 0;
}

input[type="date"]::-webkit-outer-spin-button, input[type="date"]::-webkit-inner-spin-button
	{
	-webkit-appearance: none;
	margin: 0;
}

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

#list-table td:not(:nth-of-type(6)) {
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

.modal-align-box {
	display: inline-block;
	width: 100px;
	margin: 5px;
}

.modal-align-box-sm {
	margin-left: 20px;
	display: inline-block;
	width: 60px;
}

#boardNo {
	display: inline-block;
	height: calc(1.5em + 0.75rem + 2px);
	width: 100px;
	padding: 10px;
	margin: 10px;
	background: url(data : image/ svg + xml, % 3csvg xmlns =
		'http://www.w3.org/2000/svg' viewBox = '0 0 4 5' % 3e % 3cpath fill =
		'%23343a40' d = 'M2 0L0 2h4zm0 5L0 3h4z'/ % 3e % 3c/ svg % 3e)
		no-repeat right 0.75rem center/8px 10px;
	background-color: #fff;
	border: 1px solid #ced4da;
	border-radius: 0.25rem;
}

#reportDate {
	display: inline-block;
	height: calc(1.5em + 0.75rem + 2px);
	width: 200px;
	padding: 10px;
	margin-top: 0px;
	background-color: #fff;
	border: 1px solid #ced4da;
	border-radius: 0.25rem;
}

#btnWrapper {
	text-align: center;
}

.page-link {
	color : orange;
}

</style>

</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container my-5">

		<h2 class="entry-title text-center py-5">
			<span id="title-font">회원관리</span>
		</h2>
		<button class="btn btn-dark btn-sm" style="width:140px" id="toBlockBtn">불량
			회원으로 설정</button>
			
		<a href="blockList.do">	
		<button class="btn btn-dark btn-sm" style="width:140px" id="toBlockBtn">불량
			회원 관리</button>
		</a>	
		<a data-toggle="modal" href="#report-modal">
			<button class="btn btn-secondary btn-sm" id="toBlockBtn1"
				style="display: none"></button>
		</a>

		<div class="list-wrapper">
			<table class="table table-hover table-striped my-3" id="list-table">
				<thead>
					<tr>
						<th></th>
						<th>회원번호</th>
						<th>이메일</th>
						<th>닉네임</th>
						<th>전화번호</th>
						<th>구독 중인 서비스 목록</th>
					</tr>
				</thead>

				<%-- 회원 목록 출력 --%>
				<tbody>
					<c:choose>
						<c:when test="${empty mList}">
							<tr>
								<td colspan="10">존재하는 회원이 없습니다.</td>
							</tr>
						</c:when>
						<%--조회된  목록이 있을 때  --%>
						<c:otherwise>
							<c:forEach var="member" items="${mList}">
								<tr>
									<td><input type="radio" name="selectMem" id="selectMem"></td>
									<td>${member.memNo}</td>
									<td>${member.memEmail}</td>
									<td>${member.memName}</td>
									<td>${member.memPhone}</td>
									<td>${member.memSub}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>

		<input type="hidden" name="selectMember" id="selectMember"
			value="false">

		<%-- 불량회원으로 바꾸는 modal --%>


		<div class="modal fade" id="report-modal" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">

				<div class="modal-content">

					<div class="modal-header">
						<h4 class="modal-title" id="modalLabel">불량회원 설정</h4>
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span>
						</button>
					</div>

					<div class="modal-body">
						<form class="block-form" method="POST" action="reportMember.do" onsubmit="return addInput(this)">
							<span class="modal-align-box"><label for="boardCode">게시판
							</label></span> <select class="custom-select" id="boardCode" name="boardCode"
								style="width: 130px;" required>
								<option value="F">자유게시판</option>
								<option value="J">공동구매 게시판</option>
								<option value="P">비공개 건의 게시판</option>
								<option value="E">한 줄 평가란</option>
							</select> <br> <span class="modal-align-box" style="width: 100px">
								<label for="boardCm">게시글/댓글 </label>
							</span> <select class="custom-select" id="boardCm" name="boardCm"
								style="width: 130px;" required>
								<option value="B">게시글</option>
								<option value="C">댓글</option>
							</select> 
							<span class="modal-align-box-sm"><label for="boardNo">글 번호</label></span> 
							<input type="number" class="custom-select" id="boardNo" name="boardNo" required> <br> 
							<span class="modal-align-box"><label for="reportDate">신고 날짜</label></span> 
							<input type="date" class="custom-select" id="reportDate"
								name="reportDate" required> <br>
							<span class="modal-align-box" style="margin-top: 15px;">
							<label for="reportReason">신고 사유</label></span><br> 
							<input type="text" class="form-control" id="reportReason" name="reportReason" autocomplete="off" required>
							<hr>

							<div id="btnWrapper">
								<button type="submit" id="submitBtn" class="btn-center btn btn-warning">
									확인</button>
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">취소</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<c:choose>
			<c:when test="${!empty param.sk && !empty param.sv}">
				<c:url var="pageUrl" value="/admin/memberSearch.do" />
				<c:set var="searchStr" value="&sk=${param.sk}&sv=${param.sv}" />
			</c:when>
			<c:otherwise>
				<c:url var="pageUrl" value="/admin/memberList.do" />
			</c:otherwise>
		</c:choose>


		<!-- 화살표에 들어갈 주소를 변수로 생성 -->

		<c:set var="firstPage" value="${pageUrl}?cp=1${searchStr}" />
		<c:set var="lastPage"
			value="${pageUrl}?cp=${pInfo.maxPage}${searchStr}" />


		<fmt:parseNumber var="c1"
			value="${(pInfo.currentPage-1)/pInfo.pageSize}" integerOnly="true" />
		<fmt:parseNumber var="prev" value="${c1 * 5}" integerOnly="true" />
		<c:set var="prevPage" value="${pageUrl}?cp=${prev}${searchStr}" />


		<fmt:parseNumber var="c2" value="${(pInfo.currentPage+4) /5}"
			integerOnly="true" />
		<fmt:parseNumber var="next" value="${c2*5+1}" />
		<c:set var="nextPage" value="${pageUrl}?cp=${next}${searchStr}" />

		<div class="my-5">
			<ul class="pagination">
				<c:if test="${pInfo.currentPage>5}">
					<li><a class="page-link" href="${firstPage}">&lt;&lt;</a></li>
					<li><a class="page-link" href="${prevPage}">&lt;</a></li>
				</c:if>

				<!-- 페이지 목록 -->
				<c:forEach var="page" begin="${pInfo.startPage}"
					end="${pInfo.endPage}">
					<c:choose>
						<c:when test="${pInfo.currentPage==page}">
							<li><a class="page-link">${page}</a></li>
						</c:when>

						<c:otherwise>
							<li><a class="page-link"
								href="${pageUrl}?cp=${page}${searchStr}">${page}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>


				<c:if test="${next <= pInfo.maxPage}">
					<li><a class="page-link" href="${nextPage}">&gt;</a></li>
					<li><a class="page-link" href="${lastPage}">&gt;&gt;</a></li>
				</c:if>
			</ul>
		</div>


		<!-- 검색창 -->
		<div class="my-5">
					<div class="my-5">
				<form action="${contextPath}/admin/memberSearch.do" method="GET" class="text-center" id="searchForm">
					<select name="sk" class="form-control" style="width: 120px; display: inline-block;">
						<option value="memNo">회원번호</option>
						<option value="memEmail">이메일</option>
						<option value="memName">닉네임</option>
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
		//td를 클릭하면 불량회원으로 설정 가능하게 함
		$('#selectMember').prop('value', true);
     });
	      
	//td가 눌리지 않았을 때 모달창 뜨지 않게 하기
	$("#toBlockBtn").on("click", function() {
		if($('#selectMember').val() == "true") {
			$('#toBlockBtn1').click();
		}
	});
	
	/* $("#submitBtn").on("click", function() {
		$.ajax({
            url : "changeGrade.do",
            data : {"selectMemNo" : selectMemNo}, 
            type : "post",
            success : function(result) {
                console.log("회원 선택 완료")
            },
            error: function() {
                console.log("회원선택 실패");
            }
        });
	}) */
	
	function addInput(el){
		var hidden = $("<input type='hidden' name='selectMemNo'>").val(selectMemNo);
		$(el).append($(hidden));
		
		//return false;
	}
	
	</script>
</body>
</html>
