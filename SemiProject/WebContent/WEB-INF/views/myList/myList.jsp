<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">

<style>
div.contain  {
/* 	width: 1200px; 
	 margin: 50px; */
	padding: 100px;
	margin-top : 100px;
	margin-bottom : 100px;
	
}


div.col-md-3 {
	
}

.container.contain .row .col-md-3 {
	position: relative;
}

.container.contain .row .col-md-3:hover .imgMouseover {
	display: none;
}

.container.contain .row .col-md-3:hover .showdocument.si {
	display: block;
	position: absolute;
}

.container.contain .row .col-md-3 .showdocument.si {
	position: absolute;
	display: none;
}



.showdocument.si {
	width: 200px;
	heigth: 200px;
	margin: auto;
}

.won {
	width: 15px;
	heigth: 15px;
}

#test1 > addBtn {
	margin: auto;
}
.priceDiv{
	width:400px;
	heigth: 400px;
}

</style>


</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>



	<div class="container contain bgPinkLighten1">
		<div class="row">
			<div class="col-md-3">
				<div class="row">
					<div class="col-md-12 " id="test1" width="25%">
						<!-- img 요소의 class 값에 img-responsive를 추가하면, 이미지의 가로 크기가 부모 요소의 가로 크기를 넘지 못합니다.  -->
						<img class="imgMouseover" alt="" width="100%"
							class="img-responsive center-block"
							src="${ contextPath }/resources/images/netflix.jpg" />
						<div class="showdocument si">

							NETFLIX
							<button class="float-right">x</button>
							<br> <br> 구독기간<br> 시작 : 2020-12-16<br> 종료 :
							2021-01-15<br> <br> 금액 : 12,000원
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="row">
					<div class="col-md-12 " id="test1" width="25%">
						<img class="imgMouseover" alt="" width="100%"
							class="img-responsive center-block"
							src="${ contextPath }/resources/images/wavve.png" />
						<div class="showdocument si">

							WAVVE
							<button class="float-right">x</button>
							<br> <br> 구독기간<br> 시작 : 2020-12-26<br> 종료 :
							2021-01-25<br> <br> 금액 : 11,000원
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="row">
					<div class="col-md-12" id="test1" width="25%">
						<%-- <img class="imgMouseover" alt="" width="100%"
							class="img-responsive center-block"
							src="${ contextPath }/resources/images/watcha.png" /> --%>
						<button class="btn addBtn" data-toggle="modal"
							href="#modal-container-2">
							<br>
							<br><pre>    구독 서비스 추가 +</pre>
						</button>
						<div class="showdocument si">

							<%-- 	WATCHA <button class="float-right">x</button><br>
							<br> 구독기간<br> 시작 : 2021-01-11<br> 종료 : 2021-03-13<br>
							<br> 금액 : 13,000<img class="won" src="${ contextPath }/resources/images/won.PNG"/> --%>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="row">
					<div class="col-md-12" id="test1" width="25%">
						<%-- <img class="imgMouseover" alt="" width="100%"
							class="img-responsive center-block"
							src="${ contextPath }/resources/images/coupang.jpg" /> --%>
						<button class="btn addBtn " data-toggle="modal"
							href="#modal-container-2" >
							<br>
							<br><pre>   구독 서비스 추가 +</pre>
						</button>
						<div class="showdocument si">

							<%-- COUPANG <button class="float-right">x</button><br> 
							<br> 구독기간<br> 시작 : 2020-11-10<br> 종료 : 2021-02-12<br>
							<br> 금액 : 7,000<img class="won" src="${ contextPath }/resources/images/won.PNG"/> --%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="container contain">
		<div class="row">
			<div class="col-md-12" >
				<div class="row">
					<div class="col-md-6" height="25%">
					<pre>	저번달 지출 내역 : 9,000원 </pre>
					
					</div>
					
					<div class="col-md-6" height="25%">
					<pre>	이번달 지출 내역 : 21,000원 </pre>
					
					</div>
				</div>
			</div>
		</div>
	</div>



	<%-- 구독 추가 모달 --%>
	<div class="modal fade" id="modal-container-2" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">

			<div class="modal-content">

				<div class="modal-header">
					<h4 class="modal-title" id="modalLabel">ADD SUBSCRIBE</h4>
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
				</div>

				<div class="modal-body">
					<form class="form-addSub" method="POST" action=" # ">
						<label>구독 서비스</label> <select class="custom-select" id="subCode"
							name="subCode" style="width: 150px;">
							<option value="10">넷플릭스</option>
							<option value="20">유튜브 프리미엄</option>
							<option value="30">왓챠</option>
							<option value="40">쿠팡</option>
							<option value="50">웨이브</option>
							<option value="60">기타</option>
						</select>

						<%-- el 식은 null포인터 예외를 나타내지않고 빈문자열을 출력한다. --%>
						<br>
						<br> 구독 시작일 <br> <input type="date" class="form-control"
							id="subStartDt" name="subStartDt" placeholder="구독 시작일"> <br>
						구독 종료일 <br> <input type="date" class="form-control"
							id="subEndDt" name="subEndDt" placeholder="구독 종료일 "> <br>
						금액 <br> <input type="number" class="form-control"
							id="subPrice" name="subPrice" placeholder="금액 "> <br>



						<button class="btn btn-lg btn-warning btn-block" type="submit">ADD</button>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>



	<!--<img src="이미지 경로" alt="이미지 설명"  class="imgMouseover" />
		<div class="showImage"><img src="마우스오버시 나올 이미지 경로" alt="이미지 설명" /></div>  -->

	<script>
		$(window).load(function() {
			/*   $(function() {
			      $("div.si").hide();
			      $(".imgMouseover").hover(function() {
			          $("div.si").fadeIn();
			      }, function() {
			          $("div.si").fadeOut();
			      });
			   }); */
		});
	</script>

	<jsp:include page="../common/footer.jsp"></jsp:include>

</body>

</html>