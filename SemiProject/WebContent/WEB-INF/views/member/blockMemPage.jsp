<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정지회원 페이지</title>
<style>
.lgimg {
	width: 500px;
	height: 300px;
}

pre {
	padding: 20px;
}

.jumbotron {
	border-style: dashed;
}

.ab-img {
	margin-bottom: 0px;
	width: 150px;
	height: 150px;
	text-align: center;
}

.ab-img:hover {
	width: 170px;
	height: 170px;
}

.ab-txt {
	margin-top: 10px;
	text-decoration: underline;
	color: black;
	padding: 0px;
	font-family: SFMono-Regular, Menlo, Monaco, Consolas, "Liberation Mono",
		"Courier New", monospace;
	font-size: small;
}

.inner-group {
	height: 200px;
}


</style>
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>

	<div class="container-fluid">
		<div class="row py-5">
			<div class="col-md-8 offset-md-2">
				<div class="jumbotron">
					<div class="text-center py-3">
						<img src="${contextPath}/resources/images/Oops!-logo.png" class="lgimg" />

					</div>
					<p class="text-center">
						<strong>신고 누적으로 정지된 아이디입니다.</strong>
					</p>
					<p class="text-center" style="color:red; font-size:1.2rem; background-color:white;">
정지 일자 : ${report.reportDt} / 
정지 사유 : ${report.reportReason}
					</p>
					<pre class="text-center">
문의 밎 해제 요청은 양식을 첨부하시어
admin@sims.com으로 보내주세요.


					</pre>
				</div>
			</div>
		</div>
	</div>


</body>
</html>