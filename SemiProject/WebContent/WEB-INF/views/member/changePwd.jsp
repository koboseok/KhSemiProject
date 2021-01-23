<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내정보</title>
</head>
<style>
	#content-main{
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
    left: 600px;
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
				<h3 id="title-font">비밀번호 변경</h3>
				<hr>
				<div class="bg-white rounded shadow-sm container p-3">
					<form method="POST" action="updatePwd.do" onsubmit="return pwdValidate();" class="form-horizontal" role="form">
						<!-- 아이디 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6 id="title-font">이메일</h6>
							</div>
							<div class="col-md-6">
								<h5 id="id">${loginMember.memEmail }</h5>
							</div>
						</div>

						<hr>
						<!-- 현재 비밀번호 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6 id="title-font">현재 비밀번호</h6>
							</div>
							<div class="col-md-6">
								<input type="password" class="form-control" id="currentPwd" name="currentPwd">
							</div>
						</div>

						<!-- 새 비밀번호 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6 id="title-font">새 비밀번호</h6>
							</div>
							<div class="col-md-6">
								<input type="password" class="form-control" id="newPwd1" name="newPwd1">
							</div>
						</div>

						<!-- 새 비밀번호 확인-->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6 id="title-font">새 비밀번호 확인</h6>
							</div>
							<div class="col-md-6">
								<input type="password" class="form-control" id="newPwd2" name="newPwd2">
							</div>
						</div>
						
						<hr class="mb-4">
						<button class="btn btn-warning " type="submit">변경하기</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<%-- <script src="${contextPath}/resources/js/sims_member.js"></script> --%>
	<script>
	
	

	function pwdValidate(){

	    var regExp = /^[a-zA-Z\d]{6,12}$/;

	    if( !regExp.test( $("#newPwd1").val() )  ){

	        swal("비밀번호 형식이 유효하지 않습니다.");
	        $("#newPwd1").focus();
	        return false;

	    }
	    // 새로운 비밀번호와 확인이 일치하지 않을 때
	    if( $("#newPwd1").val() != $("#newPwd2").val() ){
	        swal("새로운 비밀번호가 일치하지 않습니다.");

	        $("#newPwd1").focus();
	        $("#newPwd1").val("");
	        $("#newPwd2").val("");

	        return false;
	    }
	}
	
	</script>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	
	
	

</body>
</html>
