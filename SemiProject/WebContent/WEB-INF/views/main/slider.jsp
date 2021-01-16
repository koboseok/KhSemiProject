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
										<img src="${contextPath}/resources/images/main-youtube.jpg" alt="" />
									</div>
								</div>
								<div class="col-md-6">
									<div class="detail-box">
										<h4>인기차트</h4>
										<br>
										<h2>CONTENTS</h2>
										<br>
										<p>Enjoy the videos and music you love, upload original content, 
										and share it all with friends, family, and the world on YouTube.</p>
										<a href=""> 바로 가기 </a>
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
										<img src="${contextPath}/resources/images/main-bookcrew.png" alt="" />
									</div>
								</div>
								<div class="col-md-6">
									<div class="detail-box">
										<h4>인기차트</h4>
										<br>
										<h2>NEWS LETTER</h2>
										<br>
										<p>Enjoy the videos and music you love, upload original content, 
										and share it all with friends, family, and the world on YouTube.</p>
										<a href=""> 바로 가기 </a>
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
										<img src="${contextPath}/resources/images/main-greencar.png" alt="" />
									</div>
								</div>
								<div class="col-md-6">
									<div class="detail-box">
										<h4>인기차트</h4>
										<br>
										<h2>LIFE STYLE</h2>
										<br>
										<p>Enjoy the videos and music you love, upload original content, 
										and share it all with friends, family, and the world on YouTube.</p>
										<a href=""> 바로 가기 </a>
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