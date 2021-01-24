<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
<c:set var="contextPath" scope="application" value="${ pageContext.servletContext.contextPath }"/>
<!-- Basic -->
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<!-- Mobile Metas -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<!-- Site Metas -->
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="author" content="" />

<title>SIMS</title>



<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!--  slick -->
<script type="text/javascript" src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick-theme.min.css">

<!-- css -->
<script type="text/javascript"src="${ contextPath }/resources/js/bootstrap.js"></script>
<script type="text/javascript"src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>
<script type="text/javascript" src="${ contextPath }/resources/js/custom.js"></script>
<!-- fonts style -->
<link href="https://fonts.googleapis.com/css?family=Poppins:400,700|Raleway:400,700&display=swap"rel="stylesheet" />

<!-- bootstrap core css -->
<link rel="stylesheet" type="text/css" href="${ contextPath }/resources/css/bootstrap.css" />

<!-- Custom styles for this template -->
<link href="${ contextPath }/resources/css/style.css" rel="stylesheet" />
<link href="${ contextPath }/resources/css/stylecsy.css" rel="stylesheet" />

<!-- responsive style -->
<link href="${ contextPath }/resources/css/responsive.css"rel="stylesheet" />

<!-- sweetalert -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<style>
.modal-dialog {
	width: 100%;
	z-index : 5010;
}
li {
	list-style : none;
}
#inner-ul {
	display : none;
	
}

ul li:hover #inner-ul{
	display: inline-block;
}
#inner-li{
	margin : 10px;
	padding : 5px;
}

#inner-a {
	color: darkorange;
}
#inner-a:hover {
	color: black;
	
}

.nav-black {
	color: black !important;
}

.nav-member {
    position: relative;
	left: 600px;
    bottom: 160px;
}

#inner-ul li {
display: inline-block;}

</style>
</head>

<body>

	<%--
	 	로그인 실패 등의 서버로부터 전달받은 메세지를 경고창으로 출력하기
	 	
	 	1) 서버로부터 전달받은 메세지가 있는지 검사
	  --%>
	 <c:if test="${ !empty sessionScope.swalTitle }">
	 	<script>
	 		swal({ icon:"${swalIcon}", title:"${swalTitle}", text:"${swalText}"});
	 	</script>
	 	<%-- 2) 한번 출력한 메세지를 Session에서 삭제 --%>
	 	<c:remove var="swalIcon"/>
	 	<c:remove var="swalTitle"/>
	 	<c:remove var="swalText"/>
	 </c:if>


	<div class="hero_area">
		<header class="header_section">
			<div class="container-fluid">
				<nav class="navbar navbar-expand-lg custom_nav-container pt-3">
					<a class="navbar-brand" href="${contextPath}"> <img
						src="${ contextPath }/resources/images/sims.png" alt="" />
					</a>
					
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					
					<ul class="navbar-nav nav-member" style="margin: 10px;">
						<c:choose>
							<%-- 로그인이 되어있지 않을때 == session에 loginMember라는 값이 없을 때 --%>
							<c:when test="${empty sessionScope.loginMember }">
								<!-- 헤더에 있는 login 버튼 클릭 시 #modal-container-1 이라는 아이디를 가진 요소를 보여지게함.-->
								<li><a class="nav-black nav-link"
									data-toggle="modal" href="#modal-container-1">Login</a></li>
								<a class="nav-link">|</a>
								<li><a class="nav-black nav-link"
									href="${contextPath}/member/signUpForm.do">회원가입</a></li>
							</c:when>
							
							<c:when test="${loginMember.getMemGrade().contentEquals('A')}">
								<li class="active">
									<a class="nav-link nav-black" 
									href="${contextPath}/admin/memberList.do">회원관리</a>
								</li>
								<a class="nav-link">|</a>
								<li class="active" ><a class="nav-link nav-black"
								href="${contextPath}/member/logout.do">Logout</a>
								</li>
							</c:when>
							<%-- 로그인이 되어 있을때 --%>
							<c:otherwise>
								<li class="active">
									<%-- 로그인 회원의 이름을 가져와 출력 --%> 
									<a class="nav-link nav-black"
									href="${contextPath}/member/myPage.do">${loginMember.memName} 님</a>
								</li>
								<a class="nav-link">|</a>
								<li class="active" ><a class="nav-link nav-black" 
								href="${contextPath}/member/logout.do">Logout</a>
								</li>
							</c:otherwise>
						</c:choose>
					</ul>
				


					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<div
							class="d-flex ml-auto flex-column flex-lg-row align-items-center">
							<ul class="navbar-nav">
								<li class="nav-item "><a class="nav-link" href="${contextPath}/member/aboutSims.do ">
										about</a></li>
								<li class="nav-item"><a class="nav-link" href="${contextPath}/subscribe/main.do">
										subscribe </a></li>

							

								<li class="nav-item"><a class="nav-link" href="${contextPath}/boardNotice/main.do ">
										board </a>
										<ul class="navbar-nav" id="inner-ul">
										<li id="inner-li"><a id="inner-a"  href="${contextPath}/freeBoard/main.do">자유 게시판</a></li>
										<li id="inner-li"><a id="inner-a" href="${contextPath}/jointBoard/main.do">공동 구매 게시판</a></li>
										<li id="inner-li"><a id="inner-a" href="${contextPath}/privateBoard/main.do">비공개 건의 게시판</a></li>
									</ul></li>

								<li class="nav-item"><a class="nav-link" href="${contextPath}/myList/myList.do ">
										my sims </a></li>
								<li class="nav-item"><a class="nav-link" href="${contextPath}/guide/guide.do ">
										guide </a></li>
							</ul>
						</div>
					</div>
				</nav>
				
			</div>
		</header>
		</div>
		
		
		<%-- Modal창에 해당하는 html 코드는 페이지 마지막에 작성하는게 좋다 --%>
			<div class="modal fade" id="modal-container-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">

					<div class="modal-content">

						<div class="modal-header">
							<h5 class="modal-title" id="myModalLabel">LOGIN</h5>
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">×</span>
							</button>
						</div>

						<div class="modal-body">
							<form class="form-signin" method="POST"
								action="${ contextPath }/member/login.do">


								<input type="text" class="form-control" id="memEmail"
									name="memEmail" placeholder="ID"
									value="${ cookie.saveId.value }">
								<%-- el 식은 null포인터 예외를 나타내지않고 빈문자열을 출력한다. --%>
								<br> <input type="password" class="form-control"
									id="memPwd" name="memPwd" placeholder="PASSWORD"> <br>

								<div class="checkbox mb-3">
									<label> <%-- cookie에 저장된 아이디가 있을 경우 checked 속성을 추가한다. --%>
										<input type="checkbox" name="save" id="save"
										<c:if test="${ !empty cookie.saveId.value }">
										checked 
									</c:if>>
										SAVE ID
									</label>
								</div>

								<button class="btn btn-lg btn-warning btn-block" type="submit">LOGIN</button>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-danger"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		
		

</body>

</html>