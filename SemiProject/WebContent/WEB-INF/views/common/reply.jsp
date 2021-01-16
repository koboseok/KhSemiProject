<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>SIMS</title>
<!-- Basic -->
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<!-- Mobile Metas -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<!-- Site Metas -->
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="author" content="" />

<title>SIMS</title>

<style>
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

.starRev {
	margin: 45px 45px 150px;
}

.starR {
	background:
		url('http://miuu227.godohosting.com/images/icon/ico_review.png')
		no-repeat right 0;
	background-size: auto 100%;
	width: 30px;
	height: 30px;
	display: inline-block;
	text-indent: -9999px;
	cursor: pointer;
}

.starR.on {
	background-position: 0 0;
}
</style>
</head>
<body>

	<div class="replyWrite">
		<table align="center">
			<tr>
				<td id="replyContentArea"><textArea rows="3" id="replyContent"></textArea>
				</td>
				<td id="replyBtnArea">
					<button class="btn btn-primary" id="addReply">
						댓글<br>등록
					</button>
				</td>
			</tr>
		</table>
					
	</div>






</body>
</html>