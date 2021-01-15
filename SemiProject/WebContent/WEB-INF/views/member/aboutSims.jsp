<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>About SIMS</title>
<style>
.lgimg {
	width: 500px;
	height: 200px;
}

pre {
	padding: 20px;
}

.jumbotron {
	border-style: dashed;
}

.ab-img {
	margin-bottom: 0px;
	width: 140px;
	height: 140px;
}

.ab-txt {
	margin-top: 5px;
	text-decoration: underline;
	color: black;
	margin-top: 0px;
	padding: 0px;
	font-family: SFMono-Regular, Menlo, Monaco, Consolas, "Liberation Mono",
		"Courier New", monospace;
	font-size: small;
}

.ab-txt {
	margin-top: 5px;
	text-decoration: underline;
	color: black;
	margin-top: 0px;
	padding: 0px;
	font-family: SFMono-Regular, Menlo, Monaco, Consolas, "Liberation Mono",
		"Courier New", monospace;
	font-size: small;
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
						<img src="${contextPath}/resources/images/AboutSIMS1.png" />

					</div>
					<p class="text-center">
						<strong>S ubscribe / I ntegreted / M anagement / S ystem</strong>
					</p>
					<pre>

					
“이번 달엔 취소해야지.” 마음먹었던 구독 서비스의 결제일을 잊어 헛돈이 빠져나간 적 있으신가요? 
“놀토 정주행, 두 명만 같이 하면 저렴하게 감상할 수 있을 것 같은데…” 파티가 꾸려지지 않아 아쉬웠던 적은요? 
“어디가 더 잘 해주는지 모르겠네. 다른 사람들은 뭘 쓰나?” 궁금하셨을 수도 있었을 거예요.


그런 여러분을 위해 구독 서비스를 모아 보았습니다. 
날짜에 딱 맞춰 취소하고, 좀 더 저렴하게 이용하고, 결정하는 시간을 아낄 수 있도록요.


</pre>


					<div class="row inner-group">
						<div class="col-md-2 offset-md-1">

							<a href="#"> <img
								src="${contextPath}/resources/images/ab-1.png" class="ab-img" />
							</a>

							<p>
								<a class="ab-txt" href="#">구독 큐레이션</a>
							</p>
						</div>
						<div class="col-md-2">

							<a href="#"> <img
								src="${contextPath}/resources/images/ab-2.png" class="ab-img" />
							</a>

							<p>
								<a class="ab-txt" href="#">별점 시스템</a>
							</p>
						</div>
						<div class="col-md-2">
							<a href="#"> <img
								src="${contextPath}/resources/images/ab-3.png" class="ab-img" />
							</a>
							<p>
								<a class="ab-txt" href="#">구독정보 공유</a>
							</p>
						</div>
						<div class="col-md-2">
							<a href="#"> <img
								src="${contextPath}/resources/images/ab-4.png" class="ab-img" />
							</a>
							<p>
								<a class="ab-txt" href="#">공동구매</a>
							</p>
						</div>
						<div class="col-md-2">
							<a href="#"> <img
								src="${contextPath}/resources/images/ab-5.png" class="ab-img" />
							</a>
							<p>
								<a class="ab-txt" href="#">결제금액 관리</a>
							</p>
						</div>
					</div>

					<pre>


SIMS는 테마에 따른 다양한 구독 서비스를 소개합니다. 
여러분이 직접 사이버-발품을 파실 필요 없이, 원하는 카테고리 안의 서비스를 한 눈에 찾아볼 수 있어요. 
회원들이 어떤 서비스를 가장 많이 사용하고 선호도가 높은지 알아볼 수 있고요. 
결제일이 언제며, 이번 달에 예산 중 얼마가 빠져나갈지도 미리미리 체크할 수 있습니다. 
서비스별 페이지와 게시판을 참고하여 지갑을 열기 전 먼저 장단점을 확인하세요. 
1인 요금제를 쓰기 부담이 되거나 다양한 방법으로 서비스를 나누고 싶을 때에는 공동구매 게시판을 들러보시길 추천합니다. 
SIMS를 방문하는 회원님들이 생활에 꼭 필요한 서비스를 구독하실 수 있기를 바라며, 함께 돕겠습니다.


아쉬운 점을 건의하거나 미등록된 서비스 추가를 요청하는 글은 <a href="#">이곳</a>에서 받고 있습니다. 
				</pre>
				</div>
			</div>
		</div>
	</div>


</body>
</html>