<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내정보</title>
</head>
<style>
#content-main {
	height: 830px;
	margin-top: 100px;
    margin-bottom: 400px;
}

.lgImg {
	width: 500px;
	height: 160px;
}


.btn {
	position: relative;
    left: 620px;
}
</style>
<body>
	<div class="container" id="content-main">
		<jsp:include page="../common/header.jsp"></jsp:include>
		
		
		<div class="text-center py-5">
			<img src="${contextPath}/resources/images/My Page-logo.png" class="lgImg"/>
		</div>
		
		
		<div class="row my-5">
			<jsp:include page="sideMenu.jsp"></jsp:include>

			<div class="col-sm-8">
				<h3 id="title-font">회원 탈퇴</h3>
				<div class="bg-white rounded shadow-sm container p-3">
					<form method="POST" action="updateStatus.do" onsubmit="return secessionValidate();" 
						class="form-horizontal" role="form">
						<!-- 아이디 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6 id="title-font">아이디</h6>
							</div>
							<div class="col-md-6">
								<h5 id="id">${loginMember.memEmail }</h5>
							</div>
						</div>

						<!-- 비밀번호 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6 id="title-font">비밀번호</h6>
							</div>
							<div class="col-md-6">
								<input type="password" class="form-control" id="currentPwd"
									name="currentPwd">
							</div>
						</div>

						<hr>
						<div class="panel panel-default">

							<div class="panel-body">
								<div class="form-group pull-left">
									<label class="control-label" id="title-font"> 회원 탈퇴 약관 </label>
									<div class="col-xs-12">
										<textarea class="form-control" readonly rows="10" cols="100">
제1조
이 약관은 샘플 약관입니다.

① 약관 내용 1

② 약관 내용 2

③ 약관 내용 3

④ 약관 내용 4


제2조
이 약관은 샘플 약관입니다.

① 약관 내용 1

② 약관 내용 2

③ 약관 내용 3

④ 약관 내용 4
</textarea>
									</div>
									<div class="checkbox pull-right">
										<div class="custom-checkbox">
											<div class="form-check">
												<input type="checkbox" name="agree" id="agree"
													class="form-check-input custom-control-input"> <br>
												<label class="form-check-label custom-control-label"
													for="agree" id="title-font">위 약관에 동의합니다.</label>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<hr class="mb-4">
						<button class="btn btn-warning" id="btn"
							type="submit" style= "margin : 30px;">탈퇴</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	
	
	
	
	


	<jsp:include page="../common/footer.jsp"></jsp:include>
	<%-- <script src="${contextPath}/resources/js/sims_member.js"></script> --%>
	<script>
	function secessionValidate() {
	    if(!$("#agree").prop("checked")) {
	        // #agree 체크박스가 체크되어 있지 않다면
	        swal("약관에 동의해 주세요.")
	        return false;
	    } else {
	        return confirm("정말로 탈퇴 하시겠습니까?");
	    }
	}
	
	</script>

</body>
</html>
