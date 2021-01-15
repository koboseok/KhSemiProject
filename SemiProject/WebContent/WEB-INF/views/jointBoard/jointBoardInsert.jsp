<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공동 구매 게시판 (글쓰기)</title>
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
						<h1>공동 구매 게시판</h1>
					</td>
				</tr>

				<tr>
					<td style="color: gray">카테고리</td>
					<td><select class="form-select form-select-sm"
						aria-label=".form-select-sm example">
							<option selected>Lifesytle</option>
							<option value="1">Contents</option>
							<option value="2">Food</option>
							<option value="3">News</option>
					</select></td>
				</tr>
			</thead>
			<tbody>
				<div class="contentArea">
					<tr>
						<td style="color: gray">제목</td>
						<td><input type=text class="form-control" name=title size=70
							placeholder="제목을 입력해 주세요"></td>
					</tr>
					<tr>
						<td style="color: gray">내용</td>
						<td><textarea class="form-control" name=content cols=80
								rows=10 placeholder="내용을 작성해 주세요"></textarea></td>
					</tr>
				</div>
			</tbody>
			<tfoot>
				<ul id="fileArea">
					<tr>
						<td></td>
						<td><input type="file"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="file"></td>
					</tr>
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
	</div>

</body>
</html>