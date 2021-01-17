<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비공개 건의 게시판 (수정)</title>
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
						<h1>비공개 건의 게시판 수정</h1>
					</td>
				</tr>
			
			</thead>
			<tbody>
				<form action="${contextPath}/privateBoard/update.do" method="post">
				<div class="mb-2">
						<label class="input-group-addon mr-3 insert-label">카테고리</label> <select
							class="custom-select" id="categoryCode" name="categoryCode"
							style="width: 150px;">
							<option value="10">신고</option>
							<option value="20">요청</option>
							<option value="30">오류</option>
							<option value="40">문의</option>

						</select>
					</div>
					<div class="contentArea">
						<tr>
							<td style="color: gray">제목</td>
							<td><input type=text id = "privateTitle" class="form-control" name=title size=70
								 placeholder="제목을 입력해 주세요"></td>
						</tr>
						<tr>
							<td style="color: gray">내용</td>
							<td><textarea  id = "privateContent" class="form-control" name=content cols=80
									rows=10 placeholder="내용을 작성해 주세요"></textarea></td>
						</tr>
					</div>
			</tbody>
			<tfoot>
				<ul id="fileArea">
					<tr>
						<td></td>
						<td><input type="file" id = "img0" name = "img0"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="file" id = "img1" name = "img1"></td>
					</tr>
						<tr>
						<td></td>
						<td><input type="file" id = "img2" name = "img2"></td>
					</tr>
						<tr>
						<td></td>
						<td><input type="file" id = "img3" name = "img3"></td>
				</ul>
			</tfoot>

		</table>
		<div id="buttonArea">
			<tr>
				<td>
					<button type="submit" class="btn btn-warning">수정</button>
					<button type="button" class="btn btn-secondary">이전으로</button>
				</td>
			</tr>
		</div>
		</form>
	</div>
<script>

</script>
</body>
</html>