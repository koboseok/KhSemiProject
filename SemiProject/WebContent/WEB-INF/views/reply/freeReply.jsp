<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유 게시판 댓글</title>
</head>
<style>
/*댓글*/
.replyWrite>table {
	width: 90%;
	margin-top: 100px;
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

.rWriter {
	display: inline-block;
	margin-right: 30px;
	vertical-align: top;
}

.rDate {
	display: inline-block;
	font-size: 0.5em;
	color: gray;
}

#replyListArea {
	list-style-type: none;
}

.rContent, .replyBtnArea {
	height: 100%;
	width: 100%;
}

.replyBtnArea {
	text-align: right;
}

.replyUpdateContent {
	resize: none;
	width: 100%;
}

.reply-row {
	border-top: 1px solid #ccc;
	padding: 15px 0;
}
</style>
<div id="reply-area ">
	<!-- 댓글 작성 부분 -->
	<div class="replyWrite">
		<table align="center">
			<tr>
				<td id="replyContentArea"><textarea class="form-control"
						placeholder="댓글을 작성해 주세요." id="floatingTextarea2"
						style="height: 100px"></textarea></td>
				<td id="replyBtnArea">
					<button class="btn btn-primary" id="addReply">입력</button>
				</td>
			</tr>
		</table>
	</div>


	<!-- 댓글 출력 부분 -->
	<div class="replyList mt-5 pt-2">
		<ul id="replyListArea">

			<!-- 로그인 x 또는 댓글 작성자가 아닌 회원의 화면 -->
			<li class="reply-row">
				<div>
					<p class="rWriter">작성자</p>
					<p class="rDate">작성일</p>
				</div>

				<p class="rContent">댓글 내용</p>
			</li>


			<!-- 로그인한 회원이 댓글 작성자인 경우 -->
			<li class="reply-row">
				<div>
					<p class="rWriter">작성자</p>
					<p class="rDate">작성일</p>
				</div>

				<p class="rContent">댓글 내용</p>

				<div class="replyBtnArea">
					<button class="btn btn-outline-secondary btn-sm ml-1">수정</button>
					<button class="btn btn-outline-danger btn-sm ml-1">삭제</button>
				</div>
			</li>

		</ul>
	</div>


</div>
</body>
</html>

