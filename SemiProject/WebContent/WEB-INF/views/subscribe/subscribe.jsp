<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>SIMS</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
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
div {
	display: block;
}

p {
	margin: 0;
	padding: 0;
	word-break: break-all;
}

ul {
	margin: 0;
	padding: 0;
	list-style: none;
	display: inline-block;
	margin-block-start: 1em;
	margin-block-end: 1em;
	margin-inline-start: 0px;
	margin-inline-end: 0px;
	padding-inline-start: 40px;
	margin-block-start: 1em;
}

li {
	list-style: none;
	display: list-item;
}

img {
	vertical-align: middle;
	font-size: 1em;
}

a {
	color: #000;
	text-decoration: none
}

.title_box {
	background: white;
	height: 30px;
	line-height: 30px;
	padding-left: 5px;
	margin-bottom: 10px;
	box-shadow: 1px 1px 1px 1px #ccc;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

.title_box span {
	float: right;
	padding-right: 5px;
}

.w100 {
	width: 100%;
	height: 150px;
}

.main_title {
	float: left;
	font-weight: bold;
}

.banner2 {
	width: 100%;
	padding-right: 5px;
}

.banner2 li {
	position: relative;
	float: left;
	width: 24%;
	height: 20%;
	margin-right: 10px;
	margin-bottom: 10px;
	box-shadow: 2px 2px 2px #ccc;
	box-sizing: border-box;
}

.banner2:after {
	content: "";
	display: block;
	clear: both;
}

.banner2 li p {
	text-align: center;
	height: 20px;
	background: white;
}

#container_wr {
	margin: 45px 45px 150px;
}

.banner2 li:hover {
	cursor: pointer;
	transform: scale(1.1);
	-o-transform: scale(1.1);
	-moz-transform: scale(1.1);
	-webkit-transform: scale(1.1);
	transition: transform .35s
}

.title_box span:hover {
	cursor: pointer;
	color: green;
}
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>


	<div id="container_wr">
		<div id="container">

			<ul class="sub3_menu"></ul>

			<div class="middle_top">
				<div class="title_box">
					<p class="main_title">CONTENTS</p>
					<span>더보기 </span>
				</div>
				<ul class="banner2">
					<c:forEach var="subscribe" items="${conList}">
						<li><img
							src="${contextPath}/resources/images/${subscribe.subImage}"
							class="w100" alt="" />
							<p>${subscribe.subName }</p></li>
					</c:forEach>
				</ul>
			</div>

			<br> <br>


			<div class="middle_top">
				<div class="title_box">
					<p class="main_title">LIFE STYLE</p>
					<span>더보기 </span>
				</div>
				<ul class="banner2">
					<c:forEach var="subscribe" items="${lifeList}">
						<li><img
							src="${contextPath}/resources/images/${subscribe.subImage}"
							class="w100" alt="" />
							<p>${subscribe.subName }</p></li>
					</c:forEach>
				</ul>
			</div>

			<br> <br>


			<div class="middle_top">
				<div class="title_box">
					<p class="main_title">NEWS LETTER</p>
					<span>더보기 </span>
				</div>
				<ul class="banner2">
					<c:forEach var="subscribe" items="${newsList}">
						<li><img
							src="${contextPath}/resources/images/${subscribe.subImage}"
							class="w100" alt="" />
							<p>${subscribe.subName }</p></li>
					</c:forEach>
				</ul>
			</div>

			<br> <br>



			<div class="middle_top">
				<div class="title_box">
					<p class="main_title">FOOD</p>
					<span>더보기 </span>
				</div>
				<ul class="banner2">
					<c:forEach var="subscribe" items="${foodList}">
						<li><img
							src="${contextPath}/resources/images/${subscribe.subImage}"
							class="w100" alt="" />
							<p>${subscribe.subName }</p></li>
					</c:forEach>
				</ul>
			</div>




		</div>
	</div>





	<jsp:include page="../common/footer.jsp"></jsp:include>


	<script>
		$(".banner2 li *").on("click", function() {
			var subName = $(this).parent().children().eq(1).text();
			console.log(subName);

			location.href = "${contextPath}/subscribe/info.do?name="+subName;
		});

		
		
		$(".title_box span*").on("click", function() {
			var conName = $(this).parent().children().eq(0).text();
			console.log(conName);

			location.href = "${contextPath}/subscribe/extends.do?cname="+conName;
		});
	</script>




</body>
</html>