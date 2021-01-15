<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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
.info {
	margin: 45px 45px 150px;
}

.info div {
	display: table;
}

.info div span {
	display: table-cell;
	vertical-align: middle;
}

.top {
	text-align: center;
	margin-top: 20px;
	outline: 1px solid #9F9F9F;
	width: 100%;
	height: 80px;
	display: table;
	font-weight: bold;
}

.middle {
	margin-top: 20px;
	width: 100%;
	height: 500px;
	position: relative;
}

.middle-left {
	position: absolute;
	top: 0px;
	width: 49%;
	height: 500px;
}

.middle-right {
	position: absolute;
	top: 0px;
	left: 51%;
	width: 49%;
	height: 500px;
}

.middle-right-1 {
	outline: 1px solid #9F9F9F;
	width: 100%;
	height: 350px;
}

.middle-right-2 {
	outline: 1px solid #9F9F9F;
	margin-top: 10px;
	width: 100%;
	height: 140px;
}



img {
	display: block;
	width: 75%;
	height: 50%;
	margin: 0px auto;
}
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>

	<div class="info">

		<div class="top">
			<span>구독 서비스 명</span>
		</div>

		<div class="middle">

			<div class="middle-left">
				<span> <a href=""><img
						src="${contextPath}/resources/images/example.jpg"></a>
				</span>
			</div>

			<div class="middle-right">

				<div class="middle-right-1">
					<span>구독 서비스 설명</span>
				</div>
				<div class="middle-right-2">
					<span>평점, 이용자 수</span>
				</div>
			</div>

		</div>

	

	</div>


	<jsp:include page="../common/reply.jsp"></jsp:include>
	
	
	<jsp:include page="../common/footer.jsp"></jsp:include>








</body>
</html>