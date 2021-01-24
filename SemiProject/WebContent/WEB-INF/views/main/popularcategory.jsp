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
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<style>
.pop_section .main_container .img-box img {
	width: 400px;
	height: 250px;
}

ul {
	list-style: none;
	text-align: center;
}

.list li {
	display: inline-block;
	padding: 10px;
	margin-bottom: 5px;
	border: 1px solid #efefef;
	font-size: 12px;
	cursor: pointer;
	list-style: none;
	margin: 0px;
	padding: 0px;
	max-width: 100px;
	width: 100%;
	text-align: center;
}

.list li:hover {
	background-color: #f6f6f6;
}

.pop_section .main_container p {
	color: white;
	text-align: center;
	font-size: 30px;
	font-weight: bold;
}

.pop_section .main_container {
	text-align: center;
}

.main_container content-top {
	display: inline-block;
	width: 50%;
}

.main_container content-bottom {
	display: inline-block;
	width: 50%;
}
</style>

</head>
<body>
	<section class="pop_section">
		<div class="heading_container">
			<h2>
				service <span> category</span>
			</h2>
			<p>찾고있는 구독서비스가 있다면 이 곳에서 찾아보세요!</p>
		</div>
		<ul class="list">
			<li>CONTENTS</li>
			<li>LIFE STYLE</li>
			<li>NEWS LETTER</li>
			<li>FOOD</li>
		</ul>
		<div class="main_container layout_padding">

			<div class="content-top">
				<c:forEach var="subscribe" items="${popTopList}">
					<div class="img-box b-1">
						<img src="${contextPath}/resources/images/${subscribe.subImage}"
							alt="">
						<div class="btn-box">
							<a href="" class="btn-2"> </a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>




	<script>
		$(".list li").on("click", function() {
			var popName = $(this).eq(0).text();
			console.log(popName);

			location.href = "${contextPath}/main/pop.do?name="+popName;
		});
	</script>




</body>
</html>