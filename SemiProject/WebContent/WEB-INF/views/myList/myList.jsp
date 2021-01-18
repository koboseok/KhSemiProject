<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">






<style>
div.contain  {
	padding: 100px;
	margin-top : 100px;
	margin-bottom : 100px;
	
}

.container.contain .row .col-md-3 {
	position: relative;
}

.container.contain .row .col-md-3:hover .imgMouseover {
	display: none;
}

.container.contain .row .col-md-3:hover .showdocument {
	display: block;
	position: absolute;
}

.container.contain .row .col-md-3 .showdocument {
	position: absolute;
	display: none;
}



.showdocument {
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
.imgMouseover{
	width : 500%;
	 
}
.gear{
	width : 25px;
	height : 25px;
}
.add-sub{
	width : 45px;
	height : 45px;
	margin-left : 35px;
}

/* div button{
	background : forestgreen;
}
 */
 
.gearBtn{
	background : white;
	border : none;
}

</style>


</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>



	<div class="container contain" style=" background-color: white;">
	
		<div class="row" id="slider-div" style ="height : 300px;" >
			<div class="col-md-3 ">
				<div class="row">
					<div class="col-md-12" id="test1" width="30%">
						<!-- img 요소의 class 값에 img-responsive를 추가하면, 이미지의 가로 크기가 부모 요소의 가로 크기를 넘지 못합니다.  -->
						<img class="imgMouseover" alt=""
							class="img-responsive center-block"
							src="${ contextPath }/resources/images/netflix.jpg" />
						<div class="showdocument si slider-div">

							NETFLIX
							<button class="float-right gearBtn"><img class="gear" src="${contextPath }/resources/images/gear.png"/></button>
							<br> <br> 구독기간<br> 시작 : 2020-12-16<br> 종료 :
							2021-01-15<br> <br> 금액 : 12,000원
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3 ">
				<div class="row">
					<div class="col-md-12" id="test1" width="30%">
						<img class="imgMouseover" alt="" 
							class="img-responsive center-block"
							src="${ contextPath }/resources/images/wavve.png" />
						<div class="showdocument">

							WAVVE
							<button class="float-right gearBtn"><img class="gear" src="${contextPath }/resources/images/gear.png"/></button>
							<br> <br> 구독기간<br> 시작 : 2020-12-26<br> 종료 :
							2021-01-25<br> <br> 금액 : 11,000원
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-md-3 ">
				<div class="row">
					<div class="col-md-12" id="test1" width="30%">
					 	 <img class="imgMouseover" alt="" 
							class="img-responsive center-block"
							src="${ contextPath }/resources/images/watcha.png" /> 
						<div class="showdocument">

								WATCHA <button class="float-right gearBtn"><img class="gear" src="${contextPath }/resources/images/gear.png" /></button><br>
							<br> 구독기간<br> 시작 : 2021-01-11<br> 종료 : 2021-03-13<br>
							<br> 금액 : 13,000원
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-md-3 ">
				<div class="row">
					<div class="col-md-12" id="test1" width="30%">
						<button class="btn addBtn " style="background: forestgreen;" data-toggle="modal"
							href="#modal-container-2">
								<br><img class="add-sub" src="${contextPath }/resources/images/add-sub.png"/>
								<pre style="color: white;">구독 서비스 추가</pre>
						</button>
						<div class="showdocument">

						</div>
					</div>
				</div>
			</div>
			
			<div class="col-md-3 ">
				<div class="row">
					<div class="col-md-12 " id="test1" width="30%">
						<button class="btn addBtn " style="background: forestgreen;" data-toggle="modal"
							href="#modal-container-2" >
							<br><img class="add-sub" src="${contextPath }/resources/images/add-sub.png"/>
							<pre  style="color: white;" >구독 서비스 추가</pre>
						</button>
						<div class="showdocument">
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-md-3 ">
				<div class="row">
					<div class="col-md-12 " id="test1" width="25%">
						<button class="btn addBtn " style="background: forestgreen;" data-toggle="modal"
							href="#modal-container-2" >
							<br><img class="add-sub" src="${contextPath }/resources/images/add-sub.png"/>
								<pre  style="color: white;" >구독 서비스 추가</pre>
						</button>
						<div class="showdocument">

						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3 ">
				<div class="row">
					<div class="col-md-12 " id="test1" width="25%">
						<button class="btn addBtn " style="background: forestgreen;" data-toggle="modal"
							href="#modal-container-2" >
							<br><img class="add-sub" src="${contextPath }/resources/images/add-sub.png"/>
								<pre  style="color: white;" >구독 서비스 추가</pre>
						</button>
						<div class="showdocument">

						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3 ">
				<div class="row">
					<div class="col-md-12 " id="test1" width="25%">
						<button class="btn addBtn "  style="background: forestgreen;" data-toggle="modal"
							href="#modal-container-2" >
							<br><img class="add-sub" src="${contextPath }/resources/images/add-sub.png"/>
								<pre  style="color: white;" >구독 서비스 추가</pre>
						</button>
						<div class="showdocument">

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
							name="subCode" style="width: 200px;">
							<option value="10">넷플릭스</option>
							<option value="20">유튜브 프리미엄</option>
							<option value="30">왓챠</option>
							<option value="40">쿠팡</option>
							<option value="50">웨이브</option>
							<option value="60">기타</option>
						</select>

						<%-- el 식은 null포인터 예외를 나타내지않고 빈문자열을 출력한다. --%>
						<br>
						<br>
						금액 <br> <input type="number" class="form-control"
							id="subPrice" name="subPrice" placeholder="금액 "> <br>
						 구독 시작일 <br> <input type="date" class="form-control"
							id="subStartDt" name="subStartDt" placeholder="구독 시작일"> <br>
						<!-- 구독 종료일 <br> <input type="date" class="form-control"
							id="subEndDt" name="subEndDt" placeholder="구독 종료일 "> <br> -->



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
	 $(function(){
			$('#slider-div').slick({
				slide: 'div',		//슬라이드 되어야 할 태그 ex) div, li 
				infinite : true, 	//무한 반복 옵션	 
				slidesToShow : 4,		// 한 화면에 보여질 컨텐츠 개수
				slidesToScroll : 4,		//스크롤 한번에 움직일 컨텐츠 개수
				speed : 100,	 // 다음 버튼 누르고 다음 화면 뜨는데까지 걸리는 시간(ms)
				arrows : false, 		// 옆으로 이동하는 화살표 표시 여부
				dots : true, 		// 스크롤바 아래 점으로 페이지네이션 여부
				autoplay : false,			// 자동 스크롤 사용 여부
				autoplaySpeed : 10000, 		// 자동 스크롤 시 다음으로 넘어가는데 걸리는 시간 (ms)
				pauseOnHover : true,		// 슬라이드 이동	시 마우스 호버하면 슬라이더 멈추게 설정
				vertical : false,		// 세로 방향 슬라이드 옵션
				prevArrow : "<button type='button' class='slick-prev '>Previous</button>",		// 이전 화살표 모양 설정
				nextArrow : "<button type='button' class='slick-next '>Next</button>",		// 다음 화살표 모양 설정
				dotsClass : "slick-dots", 	//아래 나오는 페이지네이션(점) css class 지정
				draggable : true, 	//드래그 가능 여부 
				
				responsive: [ // 반응형 웹 구현 옵션
					{  
						breakpoint: 960, //화면 사이즈 960px
						settings: {
							//위에 옵션이 디폴트 , 여기에 추가하면 그걸로 변경
							slidesToShow:3 
						} 
					},
					{ 
						breakpoint: 768, //화면 사이즈 768px
						settings: {	
							//위에 옵션이 디폴트 , 여기에 추가하면 그걸로 변경
							slidesToShow:2 
						} 
					}
				]

			});
		})
    </script>
	<jsp:include page="../common/footer.jsp"></jsp:include>


</body>

</html>