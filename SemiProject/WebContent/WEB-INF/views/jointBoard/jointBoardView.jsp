<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 공동 구매 게시판</title>
<style>



</style>

</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container  my-5">

		<div>

			<h1 style="color: orange">공동 구매 게시판</h1>
			<br>
			<br>
			<div id="board-area">
				<h5>카테고리</h5>
				<h5 style="color:gray ">No.</h5>
				<br>
				<h2>제목</h2>
				<br>
					<p>
				<span id="writer" style="color:gray"> 작성자 </span>
				
				&nbsp;|&nbsp;
				
				<span id = "createDt" style="color:gray"> 작성일 </span>
								
				&nbsp;|&nbsp;
				
				<span class="readCount" style="color:gray">조회수 </span>
				</p>
				<hr>
				<!--이미지 출력 되는 곳 -->

				<!-- 내용 -->
				<div id="viewContent">내용</div>


				<hr>


				<div>

					<button type="button" id= "listBtn" class="btn btn-warning btn btn-primary float-right ">목록으로</button>
					<button type="button" id = "modifyBnt"class="btn btn-secondary btn btn-primary float-right " >수정</button>
					<button type="button" id = "deleteBnt" class="btn btn-danger btn btn-primary float-right ">삭제</button>
				
					
				
					

				</div>

				<jsp:include page="../reply/jointReply.jsp"></jsp:include>

			</div>



		</div>
	</div>
	</table>
	</div>
</body>
</html>