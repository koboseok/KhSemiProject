<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>SIMS</title>
<!-- Basic -->
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<!-- Mobile Metas -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<!-- Site Metas -->
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="author" content="" />

<title>SIMS</title>

<style>
.replyWrite>table {
	width: 90%;
	margin-top: 100px;
}

#replyContentArea {
	width: 90%;
}

#replyContentArea>textarea {
	resize: none;
	width: 100%;
}

#replyBtnArea {
	width: 100px;
	text-align: center;
}

.rWriter {
	display: inline-block;
	margin-right: 30px;
	vertical-align: top;
}

.rDate {
	display: inline-block;
	font-size: 0.5em;
	color: gray;
}

#replyListArea {
	list-style-type: none;
}

.rContent, .replyBtnArea {
	height: 100%;
	width: 100%;
}

.replyBtnArea {
	text-align: right;
}

.replyUpdateContent {
	resize: none;
	width: 100%;
}

.reply-row {
	border-top: 1px solid #ccc;
	padding: 15px 0;
}

.starRev {
	margin: 45px 45px 150px;
}

.starR {
	background:
		url('http://miuu227.godohosting.com/images/icon/ico_review.png')
		no-repeat right 0;
	background-size: auto 100%;
	width: 30px;
	height: 30px;
	display: inline-block;
	text-indent: -9999px;
	cursor: pointer;
	color: yellow;
}

.starR.on {
	background-position: 0 0;
}
</style>
</head>
<body>

	<div id="reply-area ">
		<!-- 댓글 작성 부분 -->
		<div class="replyWrite">
			<table align="center">
				<tr>
					<td id="replyContentArea"><textArea rows="3" id="replyContent"></textArea>
					</td>
					<td id="replyBtnArea">
						<button class="btn btn-primary" id="addReply">
							댓글<br>등록
						</button>
					</td>
				</tr>
			</table>
		</div>


		<!-- 댓글 출력 부분 -->
		<div class="replyList mt-5 pt-2">
			<ul id="replyListArea">

				<!-- 로그인 x 또는 댓글 작성자가 아닌 회원의 화면 -->
				<li class="reply-row">
					<div>
						<p class="rWriter">작성자</p>
						<p class="rDate">작성일 : 2021.01.11 10:30</p>
					</div>

					<p class="rContent">댓글 내용1</p>
				</li>


				<!-- 로그인한 회원이 댓글 작성자인 경우 -->
				<li class="reply-row">
					<div>
						<p class="rWriter">작성자</p>
						<p class="rDate">작성일 : 2021.01.11 10:30</p>
					</div>

					<p class="rContent">댓글 내용2</p>

					<div class="replyBtnArea">
						<button class="btn btn-primary btn-sm ml-1"
							onclick="showUpdateReply(2, this)">수정</button>
						<button class="btn btn-primary btn-sm ml-1"
							onclick="deleteReply(2)">삭제</button>
					</div>
				</li>

			</ul>
		</div>


	</div>
	<div class="starRev">
		<span class="starR on">1</span> <span class="starR">2</span> <span
			class="starR">3</span> <span class="starR">4</span> <span
			class="starR">5</span>
	</div>

	<script>
		$('.starRev span').click(function() {
			$(this).parent().children('span').removeClass('on');
			$(this).addClass('on').prevAll('span').addClass('on');
			return false;
		});

		
		
		
		$(function() {
			selectReplyList();

		});

		
		
		function selectReplyList() {

			$.ajax({

				url : "${contextPath}/reply/selectList.do",
				data : {
					"parentBoardNo" : parentBoardNo
				},
				type : "post",
				dataType : "JSON",
				success : function(rList) {

					$("#replyListArea").html("");

					$.each(rList,
							function(index, item) {
								var li = $("<li>").addClass("reply-row");
								var rWriter = $("<p>").addClass("rWriter")
										.text(item.memberId);
								var rDate = $("<p>").addClass("rDate").text(
										"작성일 : " + item.replyCreateDate);

								var div = $("<div>");
								div.append(rWriter).append(rDate);

								var rContent = $("<p>").addClass("rContent")
										.html(item.replyContent);

								li.append(div).append(rContent);

								// 현재 댓글의 작성자와 로그인한 멤버의 아이디가 같을 때 버튼 추가
								if (item.memberId == loginMemberId) {
									// 대댓글, 수정, 삭제 버튼 영역
									var replyBtnArea = $("<div>").addClass(
											"replyBtnArea");

									// ** 추가되는 댓글에 onclick 이벤트를 부여하여 버튼 클릭 시 수정, 삭제를 수행할 수 있는 함수를 이벤트 핸들러로 추가함. 
									var showUpdate = $("<button>").addClass(
											"btn btn-primary btn-sm ml-1")
											.text("수정").attr(
													"onclick",
													"showUpdateReply("
															+ item.replyNo
															+ ", this)");
									var deleteReply = $("<button>").addClass(
											"btn btn-primary btn-sm ml-1")
											.text("삭제").attr(
													"onclick",
													"deleteReply("
															+ item.replyNo
															+ ")");

									replyBtnArea.append(showUpdate).append(
											deleteReply);

									li.append(replyBtnArea);
								}

								$("#replyListArea").append(li);
							});

				},
				error : function() {
					console.log("댓글 목록 조회 실패");
				}

			});
		}
	</script>





</body>
</html>