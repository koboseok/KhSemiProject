<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	width: 100%;
	height: 80px;
	display: table;
	font-weight: bold;
	background-color: #2f2f2f;
	color: white;
	font-size: 20px;
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
	width: 100%;
	height: 350px;
	background-color: #2f2f2f;
	color: white;
	padding-left: 25px;
}

.middle-right-2 {
	outline: 1px solid #9F9F9F;
	margin-top: 10px;
	width: 100%;
	height: 140px;
	background-color: #2f2f2f;
	color: white;
}

.middle-right-2 .star {
	display: inline-block;
	outline: 1px solid #9F9F9F;
	width: 50%;
	height: 100%;
}

.middle-right-2 .user {
	display: inline-block;
	outline: 1px solid #9F9F9F;
	width: 50%;
	height: 100%;
}

#sub-img {
	display: block;
	width: 75%;
	height: 50%;
	margin: 0px auto;
}

#content {
	text-align: center;
	margin-top: 50px;
}

</style>
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>

	<div class="info">

		<div class="top">
			<span>${subscribe.subName}</span>
		</div>

		<div class="middle">

			<div class="middle-left">
				<span> <a href=""><img
						src="${contextPath}/resources/images/${subscribe.subImage}" id="sub-img"></a>
				</span>
			</div>

			<div class="middle-right">

				<div class="middle-right-1">
					<span>${subscribe.subContent}</span>
				</div>
				<div class="middle-right-2">
					<div class="star">
						<p id="content">사용자 평점 : ${replyInfo.point}</p>
					</div>
					<div class="user">
						<p id="content">서비스 사용자 수 : ${replyInfo.memberNo}</p>
					</div>
				</div>
			</div>

		</div>



	</div>


	<jsp:include page="../subscribe/subscribe-reply.jsp"></jsp:include>


	<jsp:include page="../common/footer.jsp"></jsp:include>









</body>
</html>