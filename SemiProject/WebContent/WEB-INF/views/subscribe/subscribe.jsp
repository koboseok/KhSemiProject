<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
}

.banner2 li {
	position: relative;
	float: left;
	width: 325px;
	height: 200px;
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
	height: 20px;
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
					<span> <a href="${contextPath}/subscribe/extends">더보기</a>
					</span>
				</div>
				<ul class="banner2">
					<li><a href="${contextPath}/subscribe/info"><img
							src="${loginMember.memberId}" class="w100"
							alt="" /></a>
						<p>Example</p> <span class="hot">HOT</span></li>
					<li><a href=""> <img
							src="${contextPath}/resources/images/example.jpg" class="w100"></a>
						<p>Example</p></li>
					<li><a href=""> <img
							src="${contextPath}/resources/images/example.jpg" class="w100"></a>
						<p>Example</p></li>
					<li><a href=""> <img
							src="${contextPath}/resources/images/example.jpg" class="w100"></a>
						<p>Example</p></li>
				</ul>
			</div>

			<br> <br>



			<div class="middle_top">
				<div class="title_box">
					<p class="main_title">LIFE STYLE</p>
					<span> <a href="">더보기</a>
					</span>
				</div>
				<ul class="banner2">
					<li><a href=""><img
							src="${contextPath}/resources/images/example.jpg" class="w100"
							alt="" /></a>
						<p>Example</p> <span class="hot">HOT</span></li>
					<li><a href=""> <img
							src="${contextPath}/resources/images/example.jpg" class="w100"></a>
						<p>Example</p></li>
					<li><a href=""> <img
							src="${contextPath}/resources/images/example.jpg" class="w100"></a>
						<p>Example</p></li>
					<li><a href=""> <img
							src="${contextPath}/resources/images/example.jpg" class="w100"></a>
						<p>Example</p></li>
				</ul>
			</div>

			<br> <br>


			<div class="middle_top">
				<div class="title_box">
					<p class="main_title">NEWS LETTER</p>
					<span> <a href="">더보기</a>
					</span>
				</div>
				<ul class="banner2">
					<li><a href=""><img
							src="${contextPath}/resources/images/example.jpg" class="w100"
							alt="" /></a>
						<p>Example</p> <span class="hot">HOT</span></li>
					<li><a href=""> <img
							src="${contextPath}/resources/images/example.jpg" class="w100"></a>
						<p>Example</p></li>
					<li><a href=""> <img
							src="${contextPath}/resources/images/example.jpg" class="w100"></a>
						<p>Example</p></li>
					<li><a href=""> <img
							src="${contextPath}/resources/images/example.jpg" class="w100"></a>
						<p>Example</p></li>

				</ul>
			</div>

			<br> <br>



			<div class="middle_top">
				<div class="title_box">
					<p class="main_title">FOOD</p>
					<span> <a href="">더보기</a>
					</span>
				</div>
				<ul class="banner2">
					<li><a href=""><img
							src="${contextPath}/resources/images/example.jpg" class="w100"
							alt="" /></a>
						<p>Example</p> <span class="hot">HOT</span></li>
					<li><a href=""> <img
							src="${contextPath}/resources/images/example.jpg" class="w100"></a>
						<p>Example</p></li>
					<li><a href=""> <img
							src="${contextPath}/resources/images/example.jpg" class="w100"></a>
						<p>Example</p></li>
					<li><a href=""> <img
							src="${contextPath}/resources/images/example.jpg" class="w100"></a>
						<p>Example</p></li>

				</ul>
			</div>




		</div>
	</div>





	<jsp:include page="../common/footer.jsp"></jsp:include>








</body>
</html>