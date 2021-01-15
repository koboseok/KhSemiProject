<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>signUp</title>
<script src="https://kit.fontawesome.com/955b087c12.js"
	crossorigin="anonymous"></script>
<style>
@font-face {
	font-family: 'SDSamliphopangche_Outline';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts-20-12@1.0/SDSamliphopangche_Outline.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

a {
	width: 80px;
	margin: 0;
	padding: 0 !important;
}

input[type="number"]::-webkit-outer-spin-button, input[type="number"]::-webkit-inner-spin-button
	{
	-webkit-appearance: none;
	margin: 0;
}

.modal-align-box {
	display: inline-block;
	width: 100px;
	text-align: center;
	padding: 10px;
}

.text-center {
	textalign: center;
}

.create-box {
	width: 380px;
	margin: 0px;
	padding: 10px;
	border-radius: 3%;
	background-color: #E2E2E2;
}

.text-sm {
	width: 150px;
}

.plus-minus {
	position: relative;
	top: 25px;
	left: 390px;
	cursor: pointer;
}

.select-modal {
	padding: 5px !important;
}
</style>

</head>
<body>
	<div class="container-fluid">
		<!-- header -->
		<jsp:include page="../common/header.jsp"></jsp:include>


		<div class="row">
			<div class="col-md-6 offset-md-3">
				<h2 class="entry-title text-center py-5">
					<span id="title_font">회원가입</span>
				</h2>
				<hr class="mb-4">

				<form method="POST" action="signUp.do" class="needs-validation"
					name="signUpForm" onsubmit="return validate();">

					<!-- 아이디 -->
					<div class="row mb-5 form-row">
						<div class="col-md-3">
							<label for="email">이메일 <span class="text-danger">*</span></label>
						</div>
						<div class="col-md-6">
							<input type="email" class="form-control" id="email" name="email"
								autocomplete="off" placeholder="이메일을 입력해주세요." required>
						</div>
						<div class="col-md-6 offset-md-3">
							<span id="checkId"></span>
						</div>
					</div>


					<!-- 비밀번호 -->
					<div class="row mb-3 form-row">
						<div class="col-md-3">
							<label for="pwd1">비밀번호<span class="text-danger">*</span></label>
						</div>
						<div class="col-md-6">
							<input type="password" class="form-control" id="pwd1" name="pwd1"
								maxlength="12" placeholder="영문 대/소문자, 숫자 포함 8자" required>
						</div>

						<div class="col-md-6 offset-md-3">
							<span id="checkPwd1">&nbsp;</span>
						</div>
					</div>

					<!-- 비밀번호 확인 -->
					<div class="row mb-3 form-row">
						<div class="col-md-3">
							<label for="pwd2">비밀번호 확인<span class="text-danger">*</span></label>
						</div>
						<div class="col-md-6">
							<input type="password" class="form-control" id="pwd2"
								maxlength="12" placeholder="비밀번호와 동일하게 입력해주세요." required>
						</div>

						<div class="col-md-6 offset-md-3">
							<span id="checkPwd2"></span>
						</div>
					</div>
					<br>

					<!-- 이름 -->
					<div class="row mb-3 form-row">
						<div class="col-md-3">
							<label for="name">별명</label>
						</div>
						<div class="col-md-6">
							<input type="text" class="form-control" id="name" name="name"
								placeholder="2~10자 내외 특수문자 사용 불가" required>
						</div>

						<div class="col-md-6 offset-md-3">
							<span id="checkName">&nbsp;</span>
						</div>
					</div>

					<div class="row mb-3 form-row">
						<div class="col-md-3">
							<label for="phone1">전화번호<span class="text-danger">*</span></label>
						</div>
						<!-- 전화번호1 -->
						<div class="col-md-3">
							<select class="custom-select" id="phone1" name="phone1" required>
								<option>010</option>
								<option>011</option>
								<option>016</option>
								<option>017</option>
								<option>019</option>
							</select>
						</div>

						<!-- 전화번호2 -->
						<div class="col-md-3">
							<input type="number" class="form-control phone" id="phone2"
								name="phone2" required>
						</div>

						<!-- 전화번호3 -->
						<div class="col-md-3">
							<input type="number" class="form-control phone" id="phone3"
								name="phone3" required>
						</div>

						<div class="col-md-6 offset-md-3">
							<span id="checkPhone">&nbsp;</span>
						</div>
					</div>

					<!-- 구독 서비스 등록 -->
					<div class="row">

						<div class="col-md-3">
							<label>구독 서비스 등록</label>
						</div>


						<div class="col-md-7" id="serviceFormWrapper">
							<div class="serviceFormGroup">
								<!-- 서비스명 검색 여부 판단을 위한 hidden -->
								<input type="hidden" name="searchFT" id="searchFT" value="false">
								<!-- + 아이콘 -->
								<i class="far fa-plus-square plus-minus" id="addServiceForm"></i>
								<div class="create-box" id="serviceForm">
									<div class="row m-3 form-row">
										<div class="col-md-3">
											<label for="serviceName">서비스명</label>
										</div>
										<div class="col-md-3">
											<input type="text" class="text-sm" name="serviceName"
												id="serviceName" placeholder="검색을 눌러주세요." disabled>
										</div>
										<div class="col-md-3 offset-md-3">
											<a class="nav-link" data-toggle="modal" href="#search-modal">
												<button type="button"
													class="btn btn-secondary btn-sm btn-block search-select">검색</button>
											</a>
										</div>
									</div>

									<div class="row m-3 form-row">
										<div class="col-md-3">
											<label for="serviceCharge">요금</label>
										</div>
										<div class="col-md-3">
											<input type="text" class="text-sm" name="serviceCharge"
												id="serviceCharge">
										</div>
										<div class="offset-md-6"></div>
									</div>

									<div class="row m-3 form-row">
										<div class="col-md-3">
											<label for="paymentDate">결제일</label>
										</div>
										<div class="col-md-3">
											<input type="date" class="text-sm" name="serviceDate"
												id="serviceDate">
										</div>
										<div class="offset-md-6"></div>
									</div>

									<div class="row m-3 form-row">
										<div class="col-md-3">
											<label for="paymentCycle">결제 주기</label>
										</div>
										<div class="col-md-9">
											<div class="form-check form-check-inline">
												<input type="radio" name="paymentCycle" id="1week" value="7"
													class="form-check-input custom-control-input"> <label
													class="form-check-label custom-control-label" for="1week">1주</label>
											</div>
											<div class="form-check form-check-inline">
												<input type="radio" name="paymentCycle" id="2week"
													value="14" class="form-check-input custom-control-input">
												<label class="form-check-label custom-control-label"
													for="2week">2주</label>
											</div>
											<div class="form-check form-check-inline">
												<input type="radio" name="paymentCycle" id="3week"
													value="21" class="form-check-input custom-control-input">
												<label class="form-check-label custom-control-label"
													for="3week">3주</label>
											</div>
											<div class="form-check form-check-inline">
												<input type="radio" name="paymentCycle" id="1month"
													value="1m" class="form-check-input custom-control-input">
												<label class="form-check-label custom-control-label"
													for="1month">4주</label>
											</div>
											<div class="form-check form-check-inline">
												<input type="radio" name="paymentCycle" id="6month"
													value="6m" class="form-check-input custom-control-input">
												<label class="form-check-label custom-control-label"
													for="6month">6개월</label>
											</div>
											<div class="form-check form-check-inline">
												<input type="radio" name="paymentCycle" id="1year"
													value="1y" class="form-check-input custom-control-input">
												<label class="form-check-label custom-control-label"
													for="1year">1년</label>
											</div>
										</div>
									</div>
								</div>

							</div>
						</div>
					</div>
					<hr class="mb-4">
					<p align=center>
						<button class="btn btn-dark btn-lg" type="submit">가입하기</button>
					</p>
				</form>
			</div>
		</div>
	</div>
	<br>
	<br>

	<%-- 구독 서비스명 검색 모달--%>
	<div class="modal fade" id="search-modal" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">

			<div class="modal-content">

				<div class="modal-header">
					<h4 class="modal-title" id="modalLabel">구독 서비스 찾기</h4>
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
				</div>

				<div class="modal-body">
					<form class="form-addSub" method="POST" action=" # ">
						<div class="select-modal">
							<span class="modal-align-box"><label for="categoryCode">카테고리</label></span>
							<select class="custom-select" id="categoryCode" name="subCode"
								style="width: 150px;">
								<option value="10">Contents</option>
								<option value="20">Foods</option>
								<option value="30">LifeStyle</option>
								<option value="40">NewsLetter</option>
							</select> <br>
						</div>
						<div class="select-modal">
							<span class="modal-align-box"><label for="subCode">서비스명</label></span>
							<select class="custom-select" id="subCode" name="subCode"
								style="width: 150px;">
								<option value="10">넷플릭스</option>
								<option value="20">유튜브 프리미엄</option>
								<option value="30">왓챠</option>
								<option value="40">쿠팡</option>
								<option value="50">웨이브</option>
								<option value="60">기타</option>
							</select>
						</div>
						<hr>
						<button class="btn btn-lg btn-warning btn-block" type="submit">ADD</button>
					</form>
				</div>
			</div>
		</div>
	</div>



	<script>
	
		var count = 1;
		
		// 플러스 버튼을 눌러 추가
		/* $(".plus-minus").on("click", function(){ */
		$(document).on("click", ".plus-minus", function(){
			
			var index =  $(".serviceFormGroup").index( $(this).parent() );
			console.log(index);
			
			var currentForm = $(".serviceFormGroup").eq(index).clone(true);
			var newForm = $(".serviceFormGroup").eq(index).clone(true).appendTo($("#serviceFormWrapper"));
			
			$(this).parent().replaceWith(currentForm);
			
			//복사된 개체의 input태그 내용 비우기 + id값 변경
			count = ++count;
			
			
			
			newForm.find('#serviceName').val("").attr('id', 'serviceName' + count).end();
			newForm.find('#serviceCharge').val("").attr('id', 'serviceCharge' + count).end();
			newForm.find('#paymentDate').val("").attr('id', 'paymentDate' + count).end();
			newForm.find('#paymentCycle').val("").attr('id', 'paymentCycle' + count).end();
			newForm.find('#searchFT').val(false).attr('id', 'searchFT' + count).end();

			newForm.find('#1week').attr("checked", false).attr('id', '1week' + count).end();
			newForm.find('#2week').attr("checked", false).attr('id', '2week' + count).end();
			newForm.find('#3week').attr("checked", false).attr('id', '3week' + count).end();
			newForm.find('#1month').attr("checked", false).attr('id', '1month' + count).end();
			newForm.find('#6month').attr("checked", false).attr('id', '6month' + count).end();
			newForm.find('#1year').attr("checked", false).attr('id', '1year' + count).end();
			
			
			newForm.find('input[type="radio"]').prop('name','paymentCycle' + count);
			
			//label for이 가리키는 id변경
			newForm.find('label[for=serviceName]').prop('for', 'serviceName' + count);
			newForm.find('label[for=serviceCharge]').prop('for', 'serviceCharge' + count);
			newForm.find('label[for=paymentDate]').prop('for', 'paymentDate' + count);
			newForm.find('label[for=paymentCycle]').prop('for', 'paymentCycle' + count);
			newForm.find('label[for=1week]').prop('for', '1week' + count);
			newForm.find('label[for=2week]').prop('for', '2week' + count);
			newForm.find('label[for=3week]').prop('for', '3week' + count);
			newForm.find('label[for=1month]').prop('for', '1month' + count);
			newForm.find('label[for=1year]').prop('for', '1year' + count);
			
			
			//마이너스 버튼 추가
			var i = ($(".plus-minus").length - 1);
			
			if(index == 0){
			$(".plus-minus").eq(i).after("<i class='far fa-minus-square plus-minus minus-icon'></i>");
			}
		});
		
		// 마이너스 버튼을 눌러 삭제
		$(document).on("click", ".minus-icon", function(event) { 
			var el = $(event.target).parent().remove();
    	});
		
		
	</script>

</body>
</html>