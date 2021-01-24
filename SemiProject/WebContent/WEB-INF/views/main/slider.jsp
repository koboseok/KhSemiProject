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
.carousel-indicators li {
	margin-right: 50px;
}
</style>

</head>
<body>

	<!-- slider section -->

	<section class="slider_section">
		<div class="slider_container">
			<div id="carouselExampleIndicators" class="carousel slide"
				data-ride="carousel">
				<ol class="carousel-indicators">
					<li data-target="#carouselExampleIndicators" data-slide-to="0"
						class="active">01</li>
					<li data-target="#carouselExampleIndicators" data-slide-to="1">
						02</li>
					<li data-target="#carouselExampleIndicators" data-slide-to="2">
						03</li>
				</ol>
				<div class="carousel-inner">
					<div class="carousel-item active">
						<div class="container-fluid">
							<div class="row">
								<div class="col-md-6 px-0">
									<div class="img-box">
										<a href="http://localhost:8080/SemiProject/subscribe/info.do?name=%EC%9C%A0%ED%8A%9C%EB%B8%8C%20%ED%94%84%EB%A6%AC%EB%AF%B8%EC%97%84">
										<img src="${contextPath}/resources/images/main-youtube.jpg" alt="" /></a>
									</div>
								</div>
								<div class="col-md-6">
									<div class="detail-box">
										<h4>인기차트</h4>
										<br>
										<h2>CONTENTS</h2>
										<br>
										<p>광고 없이 음악과 동영상 무제한 감상. 신규 가입 시 1개월 무료.<br>
										별도 조건 적용. 동영상과 음악을 광고 없이, <br>
										오프라인과 백그라운드에서 즐기세요. 지금 가입하세요. <br>
										단 하나의 멤버십. 지금 무료체험하세요. 광고 없는 YouTube</p>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="carousel-item">
						<div class="container-fluid">
							<div class="row">
								<div class="col-md-6 px-0">
									<div class="img-box">
										<a href="http://localhost:8080/SemiProject/subscribe/info.do?name=%EB%B6%81%ED%81%AC%EB%A3%A8"><img src="${contextPath}/resources/images/main-bookcrew.png" alt="" /></a>
									</div>
								</div>
								<div class="col-md-6">
									<div class="detail-box">
										<h4>인기차트</h4>
										<br>
										<h2>NEWS LETTER</h2>
										<br>
										<p>
										책모임에서 나눈 토론과 교실에서 받은 수업에서 <br>
										남긴 뒤죽박죽 손메모에 담긴 생각, 목표, 열정, 계획...<br>
										이제, 작가와 직접 만나 인생의 단편으로 영원히 남기세요. <br>
										북크루에서 작가와 마주할 수 있게 됩니다.</p>
										
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="carousel-item">
						<div class="container-fluid">
							<div class="row">
								<div class="col-md-6 px-0">
									<div class="img-box">
										<a href="http://localhost:8080/SemiProject/subscribe/info.do?name=%EA%B7%B8%EB%A6%B0%EC%B9%B4">
										<img src="${contextPath}/resources/images/main-greencar.png" alt="" /></a>
									</div>
								</div>
								<div class="col-md-6">
									<div class="detail-box">
										<h4>인기차트</h4>
										<br>
										<h2>LIFE STYLE</h2>
										<br>
										<p>이동을 새로 그리다! 그린카와 당신의 일상을 새로 그려보세요.<br>
										24시간  쉽고 편하게, 스마트 카셰어링 그린카! <br>
										신규 가입하면 3시간 무료!<br>
										2020 K-BPI 카셰어링 부문,5년 연속 1위 수상</p>
										
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="carousel_btn-box">
					<a class="carousel-control-prev" href="#carouselExampleIndicators"
						role="button" data-slide="prev"> <span class="sr-only">Previous</span>
					</a>  <a
						class="carousel-control-next" href="#carouselExampleIndicators"
						role="button" data-slide="next"> <span class="sr-only">Next</span>
					</a>
				</div>
			</div>
		</div>
	</section>


	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js">
  	</script>
	<script type="text/javascript" src="${contextPath}/resources/js/custom.js"></script>





</body>
</html>