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
	display: inline-block;
	width: 100%;
}

.banner2 li {
	position: relative;
	float: left;
	width: 315px;
	height: 200px;
	margin-right: 20px;
	margin-bottom: 10px;
	box-shadow: 2px 2px 2px #ccc;
	box-sizing: border-box;
}

.banner2:after {
	content: "";
	display: block;
	clear: both;
}

.hot {
	background: green;
	color: white;
	position: absolute;
	top: 0;
	left: 0;
	width: 25px;
	font-size: 2pt;
	line-height: 20px;
	height: 20px;
	text-align: center;
}

.banner2 li p {
	text-align: center;
	margin-top: 10px;
	background: white;
}

#container_wr {
	margin: 45px 45px 150px;
}

.banner2 li:hover {
	cursor: pointer;
	transform: scale(1.2);
	-o-transform: scale(1.2);
	-moz-transform: scale(1.2);
	-webkit-transform: scale(1.2);
	transition: transform .35s
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
				</div>



				<ul class="banner2">


					<c:forEach var="conSubscribe" items="${exList}">
						<li><img
							src="${contextPath}/resources/images/${conSubscribe.subImage}"
							class="w100" alt="" />
							<p>${conSubscribe.subName}</p>
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

			location.href = "${contextPath}/subscribe/info.do?name=" + subName;
		});
	</script>







</body>
</html>