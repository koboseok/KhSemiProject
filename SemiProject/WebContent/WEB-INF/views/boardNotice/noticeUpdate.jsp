<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<style>
@font-face {
	font-family: 'SDSamliphopangche_Outline';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts-20-12@1.0/SDSamliphopangche_Outline.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}
#boardStyle {
	font-family: 'SDSamliphopangche_Outline';
}
	#notice-area{ margin-bottom:200px;}
	#notice-content{ padding-bottom:150px;}
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>

	<div class="container my-5">

		<h1>
			<span id="boardStyle"> 공지 사항 - 수정 </span>
		</h1>
	      <hr>
	      <div class="bg-white rounded shadow-sm container py-3">
	        <form method="POST" action="update.do?no=${param.no }" role="form" onsubmit="return noticeValidate();">
	        
	        
	          <div class="form-inline mb-2">
	            <div class="input-group">
	              <label class="input-group-addon mr-3">제목</label>
	              <input type="text" class="form-control" id="noticeTitle" name="noticeTitle" size="70" value="${notice.noticeTitle }">
	            </div>
	          </div>
	
	          <div class="form-inline mb-2">
	            <div class="input-group">
	              <label class="input-group-addon mr-3">작성자</label>
	              <h5 class="my-0" id="writer">${notice.memNm }</h5>
	            </div>
	          </div>
	
	
	          <div class="form-inline mb-2">
	            <div class="input-group">
	              <label class="input-group-addon mr-3">작성일</label>
	              <h5 class="my-0" id="today">${notice.noticeCreateDate}</h5>
	            </div>
	          </div>
	
	          <hr>
	
	          <div class="form-group">
	            <div><label for="content">내용</label> </div>
	            <textarea class="form-control" id="noticeContent" name="noticeContent" rows="10" 
	            style="resize: none;">${notice.noticeContent }</textarea>
	          </div>
	
	
	        <hr class="mb-4">
	
       	<div class="text-center">
					<button type="submit" class="btn btn-warning">수정</button>
				<button type="button" class="btn btn-secondary"
					onclick="location.href='${header.referer}'">이전으로</button>
				</div>
	        
      </form>
     </div>

	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	
	
	<script>
	// 유효성 검사.
	function noticeValidate(){
		if( $("#noticeTitle").val().trim().length == 0){
			alert("제목을 입력해 주세요.");
			$("#title").focus();
			return false;
		}
		
		if( $("#noticeContent").val().trim().length == 0){
			alert("내용을 입력해 주세요.");
			$("#content").focus();
			return false;
		}
	}
	</script>
</body>
</html>
