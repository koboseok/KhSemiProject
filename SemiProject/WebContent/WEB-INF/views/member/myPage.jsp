<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내정보</title>
<style>
	input[type="number"]::-webkit-outer-spin-button, 
	input[type="number"]::-webkit-inner-spin-button
		{
		-webkit-appearance: none;
		margin: 0;
	}
	
.lgImg {
	width: 500px;
	height: 160px;
}

.btn {
	position: relative;
    left: 650px;
}
</style>
</head>
<body>
	<div class="container">
		<jsp:include page="../common/header.jsp"></jsp:include>
		
		<%-- 전화번호, 주소를 구분자를 이용하여 분리된 배열 형태로 저장 --%>
		
		<c:set var="phone" value="${fn:split(loginMember.memPhone,'-') }"/>
		
		<div class="text-center py-5">
			<img src="${contextPath}/resources/images/My Page-logo.png" class="lgImg"/>
		</div>

		<div class="row my-5">
			<jsp:include page="sideMenu.jsp"></jsp:include>		
				
				
				
			<div class="col-sm-8">
				<h3 id="title-font">내 정보</h3>
				<hr>
				<div class="bg-white rounded shadow-sm container p-3">
					<form method="POST" action="updateMember.do" onsubmit="return memberUpdateValidate();" class="form-horizontal" role="form">
						<!-- 아이디 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6 id="title-font">이메일</h6>
							</div>
							<div class="col-md-6">
								<%-- <input type="email" class="form-control" id="email" name="email" 
											value="${ loginMember.memEmail } "> --%>
											<h4>${ loginMember.memEmail }</h4>
								
							</div>
						</div>
	
						<!-- 이름 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6 id="title-font">별명</h6>
							</div>
							<div class="col-md-6">
								<input type="text" class="form-control name" id="name" name="name"value="${ loginMember.memName }">
							</div>
						</div>
	
						<!-- 전화번호 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<label for="phone1" id="title-font">전화번호</label>
							</div>
							<!-- 전화번호1 -->
							<div class="col-md-3">
								<select class="custom-select" id="phone1" name="phone1">
									<option>010</option>
									<option>011</option>
									<option>016</option>
									<option>017</option>
									<option>019</option>
								</select>
							</div>
							
	
							<!-- 전화번호2 -->
							<div class="col-md-3">
								<input type="number" class="form-control phone" id="phone2" name="phone2" 
											value="${phone[1]}">
							</div>
	
							<!-- 전화번호3 -->
							<div class="col-md-3">
								<input type="number" class="form-control phone" id="phone3" name="phone3" 
											value="${phone[2]}">
							</div>
						</div>
	
						<br>
	
						<!-- 관심분야 -->
						<hr class="mb-4">

						<button class=" btn btn-warning  " type="submit">수정</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<br><br>
	
	<jsp:include page="../common/footer.jsp"></jsp:include>
		
		
		
	<!-- postcodify를 로딩 -->
	<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
	
	<!-- 회원 관련 Javascript 코드를 모아둘 wsp_member.js 파일을 작성 -->
	 <script src="${contextPath}/resources/js/sims_member.js"></script> 
	
 		
	<script>
		/* 스크립트 코드에 EL/JSTL 이 포함된 경우 별도 js파일에 작성할 수 없다
		- js파일은 요청 시 클라이언트 측으로 전달되어져 브라우저가 해석하지만
		  JSP(EL/JSTL)는 서버측에서 JAVA로 변환되고 해석되어야함.
		   -> js파일에 작성하는 경우 EL/JSTL 코드가 해석되지 않은 상태로 클라이언트로 전달되어 
		      의도한 형태로 해석되지 않는 문제가 발생함.
		*/
		
		// 전화번호 첫 번째 자리를 회원 전화번호 첫 번째 자리와 일치하는 값으로 선택하기
		
		// (function(){})(); -> 즉시 처리 함수
		// 함수가 정의 되자마자 수행되는 함수, 변수명 충돌 현상 방지 + 속도적 측면에서 우위가 있다.
		
		(function(){
			// #phone1 의 자식 중 option 태그들을 반복적으로 순차 접근
			$("#phone1 > option").each(function(index, item){
				// index : 현재 접근중인 index를 나타낸다. 
				// item : 현재 접근중인 요소를 나타낸다.
				
				// 현재 접근한 요소의 내용이 phone[0]과 같을 경우
				if($(item).text() == "${phone[0]}"){
					// 현재 접근한 요소에 selected 속성을 추가
					$(item).prop("selected",true);
					
				}
				
			});
		})();
		
		
		
	
		
		
	</script>

	
	
</body>
</html>
