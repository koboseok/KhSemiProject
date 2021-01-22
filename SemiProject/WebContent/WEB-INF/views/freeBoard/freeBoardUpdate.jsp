<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유 게시판 (수정)</title>
</head>
<style>
#buttonArea {
	padding: 40px 10px 40px 600px
}

.board tr>td>h1 {
	color: orange;
}
</style>

<jsp:include page="../common/header.jsp"></jsp:include>

<body>
	<div align="center">
		<form action="${contextPath}/freeBoard/update.do" method="post"
			enctype="multipart/form-data" role="form"
			onsubmit="return updateValidate();">
			
			<table class="board">
				<thead>
					<tr>
						<td colspan="2">
							<h1 style="margin-bottom: 50px; margin-top: 50px;">자유 게시판 수정</h1>
						</td>
					</tr>
				</thead>
				<tbody>
						<tr>
							<td style="color: gray"><label
								class="input-group-addon mr-3 insert-label">작성자</label></td>
							<td style="color: gray"><h5 class="my-0" id="writer">${loginMember.memName}</h5></td>
						</tr>
						<tr>
							<td style="color: gray"><label
								class="input-group-addon mr-3 insert-label">작성일</label></td>
							<td style="color: gray"><h5 class="my-0" id="today"></h5></td>
						</tr>
						<tr>
							<td style="color: gray">제목</td>
							<td><input type=text id="freeTitle" class="form-control"
								name="fBoardTitle" size=70 placeholder="제목을 입력해 주세요"></td>
						</tr>

						<tr>
							<td style="color: gray">내용</td>
							<td><textarea id="freeContent" class="form-control"
									name="fBoardContent" cols=80 rows=10 placeholder="내용을 작성해 주세요"></textarea></td>
						</tr>
				</tbody>
				<tfoot>
					<tr>
						<td></td>
						<td><input type="file" id="img0" name="img0" onchange="LoadImg(this,0)" ></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="file" id="img1" name="img1" onchange="LoadImg(this,1)"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="file" id="img2" name="img2" onchange="LoadImg(this,2)"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="file" id="img3" name="img3"onchange="LoadImg(this,3)"></td>
					</tr>
				
					
				</tfoot>
				<tr>
					<td style="position: relative;left: 610px;">
						<button type="submit" class="btn btn-warning" >수정</button>
						<button type="button" class="btn btn-secondary" onclick="location.href='${header.referer}'">이전으로</button>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<script>

      // 유효성 검사 
      function updateValidate() {
         if ($("#boardTitle").val().trim().length == 0) {
            alert("제목을 입력해 주세요.");
            $("#title").focus();
            return false;
         }

         if ($("#fboardContent").val().trim().length == 0) {
            alert("내용을 입력해 주세요.");
            $("#fboardContent").focus();
            return false;
         }
      }
      
       // 이미지 영역을 클릭할 때 파일 첨부 창이 뜨도록 설정하는 함수
    $(function () {
       $("#fileArea").hide();

      $(".boardImg").on("click",function(){
        var index = $(".boardImg").index(this);
        $("#img" + index).click();
      });
    });
       

    // 각각의 영역에 파일을 첨부 했을 경우 미리 보기가 가능하도록 하는 함수
    function LoadImg(value, num) {
      if (value.files && value.files[0]) {
        var reader = new FileReader();
        // 자바스크립트 FileReader
          // 웹 애플리케이션이 비동기적으로 데이터를 읽기 위하여 읽을 파일을 가리키는 File 혹은 Blob객체를 이용해 파일의 내용을 읽고 사용자의 컴퓨터에 저장하는 것을 가능하게 해주는 객체
            
        reader.readAsDataURL(value.files[0]);
        // FileReader.readAsDataURL()
         // 지정된의 내용을 읽기 시작합니다. Blob완료되면 result속성 data:에 파일 데이터를 나타내는 URL이 포함 됩니다.
         
          // FileReader.onload
            // load 이벤트의 핸들러. 이 이벤트는 읽기 동작이 성공적으로 완료 되었을 때마다 발생합니다.
        reader.onload = function (e) {
           //console.log(e.target.result);
           // e.target.result
           // -> 파일 읽기 동작을 성공한 객체에(fileTag) 올라간 결과(이미지 또는 파일)
           
          $(".boardImg").eq(num).children("img").attr("src", e.target.result);
        }

      }
    }
    
  
      // 이미지 배치
      <c:forEach var="file" items="${fList}">
         $(".boardImg").eq(${file.fileLevel}).children("img")
            .attr("src", "${contextPath}/resources/uploadImages/${file.fileName}");
      </c:forEach>
      
     /*  $("#del1").on("click",function()){
    	  $(this).prev().children("img").remove();
    	  var img = $("<img>").css("width","200px").css("height","200px");
    	  
    	  $(this).prev().append(img);
      } */
   
   </script>



</body>
</html>