<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

/* .gear:hover  {
	display:none;
}

.gear:hover .delBtn{
	display : black;
	position: absolute;
}

.delBtn {
	display:none;
	position: absolute;
}
.setBtn{
	display:none;
	position: absolute;
} */

/* div.setting:hover .gear{
	display: none;
}
div.setting:hover .setting-btn{
	display : block ;
	
}
 */

.setting-btn{
	position: relative;
    left: 150px;
    top: -30px;
    width: 50px;
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
	width : 400%;
	 
}
.setting{
	width : 25px;
	height : 25px;
}



.gear{
	width : 25px;
	height : 25px;
}
.can{
	width : 25px;
	height : 25px;
}
.add-sub{
	width : 45px;
	height : 45px;
	margin-left : 35px;
}


 
.gearBtn {
	background : white;
	border : none;
}
.delBtn {
	background : white;
	border : none;
}
.setBtn{
	background : white;
	border : none;
}
#test1 {
	width : 30%;
}

#div1 {
	margin:auto;
	margin-top : 100px;
	margin-bottom : 100px;
	padding:100px;
	width : 700px;
	height : 300px;
	text-align : center;
	background : darkorange;
	border-radius : 20px;
}

#pre1{
	font-size : 20px;
	
	color : lightyellow;
	font-family : fantasy;
}


</style>


</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>



	<!-- img 요소의 class 값에 img-responsive를 추가하면, 이미지의 가로 크기가 부모 요소의 가로 크기를 넘지 못합니다.  -->

	<c:if test="${empty loginMember }">
		<div id="div1"><pre id="pre1">로그인 후 이용이 가능합니다. 
	<a class="nav-black nav-link"
		href="${contextPath}/member/signUpForm.do">회원가입 페이지로 이동</a>회원가입  하쉴 ??</pre></div>
	</c:if>

	<!-- if문 시작 로그인이 되어있을 경우 -->
	<c:if test="${!empty loginMember }"> -
		<div class="container contain" style="background-color: white;">
			<div class="row" id="slider-div" style="height: 300px; ">
			
				<!-- 포문 시작-->
				<!-- 리스트 사이즈가 1 이상이경우 -->
				<c:if test="${!empty list }"> 
				<c:forEach var="item" items="${list}">
					<div class="col-md-3 ">
						<div class="row">
							<div class="col-md-12" id="test1">
								<img class="imgMouseover" alt="${item.servNm }"
									class="img-responsive center-block"
									src="${ contextPath }/resources/images/${item.servImg}" />
								<div class="showdocument si slider-div">

									${item.servNm }(${item.servCode })
									
									<button class=" float-right delBtn del" name="delMylist" data-toggle="modal" href="#modal-container-3"
												onclick="delMyList(${item.servCode});" >
										<img class="can" src="${contextPath }/resources/images/trash-can.png" />
									</button>
									<button class=" float-right gearBtn" data-toggle="modal" href="#modal-container-4"
												>
										<img class="gear" src="${contextPath }/resources/images/gear.png" />
									</button>
									
									<br> <br> 구독기간<br> 시작 : ${item.startDt}<br>
									종료 : ${item.endDt}<br> <br> 금액 : ${item.price } 원
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
				<!-- // 포문 끝 -->
				<!-- //리스트 사이즈가 1 이상이경우 -->
				</c:if> 
				<div class="col-md-3 ">
					<div class="row">
						<div class="col-md-12" id="test1">
							<button class="btn addBtn " style="background: forestgreen;"
								data-toggle="modal" href="#modal-container-2">
								<br> <img class="add-sub"
									src="${contextPath }/resources/images/add-sub.png" />
								<pre style="color: white;">구독 서비스 추가</pre>
							</button>
						</div>
					</div>
				</div>
			
			</div>
		</div>
	 </c:if> 
	<!--// if 종료 -->
	
	
	<c:if test = "${!empty list }">
		<div class="container contain">
			<div class="row">
				<div class="col-md-12" >
					<div class="row">
						<div class="col-md-6" height="25%">
						<pre>	저번달 지출 내역 : ${temp }원 </pre>
						
						</div>
						
						<div class="col-md-6" height="25%">
						<pre>	이번달 지출 내역 : ${sum}원 </pre>
						
						</div>
					</div>
				</div>
			</div>
		</div>
	
	</c:if>



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
					<form class="form-addSub" method="POST" action="${contextPath}/myList/addList.do">
						<label>구독 서비스</label> <select class="custom-select" id="servCode"
							name="servCode" style="width: 200px;">
							<option value="1">라프텔</option>
							<option value="2">웨이브</option>
							<option value="3">유튜브 프리미엄</option>
							<option value="4">티빙</option>
							<option value="5">넷플릭스</option>
							<option value="6">나물투데이</option>
							<option value="7">더브레드블루</option>
							<option value="8">맘마레시피</option>
							<option value="9">빈브라더스</option>
							<option value="10">술담화</option>
							<option value="11">꾸까</option>
							<option value="12">네이버플러스</option>
							<option value="13">로켓와우</option>
							<option value="14">먼슬리 코스메틱스</option>
							<option value="15">미하이삭스</option>
							<option value="16">뉴닉</option>
							<option value="17">미라클레터</option>
							<option value="18">북크루</option>
							<option value="19">어피티</option>
							<option value="20">에그브렉</option>
						</select>

						<%-- el 식은 null포인터 예외를 나타내지않고 빈문자열을 출력한다. --%>
						<br>
						<br>
						금액 <br> <input type="number" class="form-control"
							id="servPrice" name="servPrice" placeholder="금액 "> <br>
						 구독 시작일 <br> <input type="date" class="form-control"
							id="startDt" name="startDt" placeholder="구독 시작일"> <br>
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
	
	<div class="modal fade" id="modal-container-3" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">

					<div class="modal-content">

						<div class="modal-header">
							<h5 class="modal-title" id="myModalLabel" style="text-align: center;">정말 삭제 하시겠습니까 ?</h5>
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">×</span>
							</button>
						</div>

						<div class="modal-body">
							<form class="form-signin" method="POST"
								action="${ contextPath }/myList/delMyList.do">
				
								<input type="text" id="servCode1" name="servCode">
								<button class="btn  btn-warning btn-block" type="submit">삭제</button>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-danger"
								data-dismiss="modal">취소</button>
						</div>
					</div>
				</div>
			</div>

	<%-- 구독 목록 재설정 --%>
	<%-- <div class="modal fade" id="modal-container-4" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">

			<div class="modal-content">

				<div class="modal-header">
					<h4 class="modal-title" id="modalLabel">UPDATE SUBSCRIBE</h4>
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
				</div>

				<div class="modal-body">
					<form class="form-addSub" method="POST"
						action="${contextPath}/myList/updateList.do">
						<label>구독 서비스</label> <select class="custom-select" id="updateServCode"
							name="updateServCode" style="width: 200px;">
							<option value="1">라프텔</option>
							<option value="2">웨이브</option>
							<option value="3">유튜브 프리미엄</option>
							<option value="4">티빙</option>
							<option value="5">넷플릭스</option>
							<option value="6">나물투데이</option>
							<option value="7">더브레드블루</option>
							<option value="8">맘마레시피</option>
							<option value="9">빈브라더스</option>
							<option value="10">술담화</option>
							<option value="11">꾸까</option>
							<option value="12">네이버플러스</option>
							<option value="13">로켓와우</option>
							<option value="14">먼슬리 코스메틱스</option>
							<option value="15">미하이삭스</option>
							<option value="16">뉴닉</option>
							<option value="17">미라클레터</option>
							<option value="18">북크루</option>
							<option value="19">어피티</option>
							<option value="20">에그브렉</option>
						</select>

						el 식은 null포인터 예외를 나타내지않고 빈문자열을 출력한다.
						<br> <br> 금액 <br> <input type="number"
							class="form-control" id="updateServPrice" name="updateServPrice"
							placeholder="금액 "> <br> 구독 시작일 <br> <input
							type="date" class="form-control" id="updateStartDt" name="updateStartDt"
							placeholder="구독 시작일"> <br>
						


							<input type="hidden" id="servCode2" name="servCode2">
						<button class="btn btn-lg btn-warning btn-block" type="submit">UPDATE</button>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div> --%>



	<!--<img src="이미지 경로" alt="이미지 설명"  class="imgMouseover" />
		<div class="showImage"><img src="마우스오버시 나올 이미지 경로" alt="이미지 설명" /></div>  -->

	
	 <script>
	 $(function(){
		 	var inf = ${empty list and list.size() < 4 ? false : true};
		 	
		 	
		 
			$('#slider-div').slick({
				slide: 'div',		//슬라이드 되어야 할 태그 ex) div, li 
				infinite : inf, 	//무한 반복 옵션	 
				//"${empty list ? false : true}"
				slidesToShow :"<c:if test='${empty list}'> 1 </c:if>  <c:if test = '${list.size() == 1}'> 2 </c:if>  <c:if test = '${list.size() == 2}'> 3 </c:if>  <c:if test = '${list.size() == 3}'> 4 </c:if>  <c:if test = '${list.size() >= 4}'> 4 </c:if>" , 
					//"${ empty list ? 1 : !empty list and list.size() < 4 ? list.size() : 4  }",		// 한 화면에 보여질 컨텐츠 개수
				//list.size가 0일 경우 1 , list.size보다 4 미만일 경우 list.size() , list.size가 4 이상일 경우  4 , list.size가 0일 경우 1 

				slidesToScroll : 4,		//스크롤 한번에 움직일 컨텐츠 개수
				speed : 100,	 // 다음 버튼 누르고 다음 화면 뜨는데까지 걸리는 시간(ms)
				arrows : false, 		// 옆으로 이동하는 화살표 표시 여부
				dots : true, 		// 스크롤바 아래 점으로 페이지네이션 여부
				autoplay : false,			// 자동 스크롤 사용 여부
				autoplaySpeed : 10000, 		// 자동 스크롤 시 다음으로 넘어가는데 걸리는 시간 (ms)
				pauseOnHover : true,		// 슬라이드 이동	시 마우스 호버하면 슬라이더 멈추게 설정
				vertical : false,		// 세로 방향 슬라이드 옵션
				//prevArrow : "<button type='button' class='slick-prev '>Previous</button>",		// 이전 화살표 모양 설정
				//nextArrow : "<button type='button' class='slick-next '>Next</button>",		// 다음 화살표 모양 설정
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
							slidesToShow:2
						} 
					}
				] 

			});
		})
		
		// function init() {console.log("${ empty list ? 1 : !empty list and list.size() < 4 ? list.size() : 4  }")} init();
		
		function delMyList(servCode1) {
			$("#servCode1").val(servCode1);
		}
		/* function updateMyList(servCode2) {
			console.log(servCode2);
			$("#servCode2").val(servCode2);
		} */
    </script>
	<jsp:include page="../common/footer.jsp"></jsp:include>


</body>

</html>