<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유 게시판 (글쓰기)</title>
</head>
<style>
#buttonArea {
	padding: 40px 10px 40px 600px
}

.board tr>td>h1 {
	color: orange;
}
</style>

<jsp:include page="../common/header.jsp"></jsp:include>

<body>
	<div align="center">
		<table class="board">
			<thead>
				<tr>
					<td colspan="2">



						<h1>자유 게시판 등록</h1>
					</td>
				</tr>

			</thead>
			<tbody>
				<form action="${contextPath}/freeBoard/insert.do" method="post"
					enctype="multipart/form-data" role="form"
					onsubmit="return fBoardValidate();">
					<div class="contentArea">
					
					<tr>
							<td style="color: gray"><label class="input-group-addon mr-3 insert-label">작성자</label></td>
							<td style="color: gray" ><h5 class="my-0" id="writer">${loginMember.memName}</h5></td>
						</tr>
						<tr>
							<td style="color: gray" ><label class="input-group-addon mr-3 insert-label">작성일</label></td>
							<td style="color: gray" ><h5 class="my-0" id="today"></h5></td>
						</tr>
						<tr>
							<td style="color: gray">제목</td>
							<td><input type=text id="freeTitle" class="form-control"
								name=fBaordTitle size=70 placeholder="제목을 입력해 주세요"></td>
						</tr>

						<tr>
							<td style="color: gray">내용</td>
							<td><textarea id="freeContent" class="form-control"
									name=fBoardContent cols=80 rows=10 placeholder="내용을 작성해 주세요"></textarea></td>
						</tr>
					</div>
			</tbody>
			<tfoot>
				<ul id="fileArea">
					<tr>
						<td></td>
						<td><input type="file" id="img0" name="img0"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="file" id="img1" name="img1"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="file" id="img2" name="img2"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="file" id="img3" name="img3"></td>
				</ul>
			</tfoot>

		</table>
		<div id="buttonArea">
			<tr>
				<td>
					<button type="submit" class="btn btn-warning">등록</button>
					<button type="button" class="btn btn-secondary">취소</button>
				</td>
			</tr>
		</div>
		</form>
	</div>
	<script>
//유효성 검사 
function fBoardValidate() {
	if ($("#freeTitle").val().trim().length == 0) {
		alert("제목을 입력해 주세요.");
		$("#freeTitle").focus();
		return false;
	}

	if ($("#freeContent").val().trim().length == 0) {
		alert("내용을 입력해 주세요.");
		$("#freeContent").focus();
		return false;
	}
}

(function printToday(){
	// 오늘 날짜 출력 
		var today = new Date();
	var month = (today.getMonth()+1);
	var date = today.getDate();

	var str = today.getFullYear() + "-"
    		+ (month < 10 ? "0"+month : month) + "-"
    		+ (date < 10 ? "0"+date : date);
	$("#today").html(str);
})();


</script>
</body>
</html>