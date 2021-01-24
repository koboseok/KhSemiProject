<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
/*댓글*/
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
	font-size: 0.8em;
}

.rPoint {
	font-size: 0.9em;
	color: gray;
	float: right;
	padding-right: 30px;
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
	float: right;
	padding-right: 215px;
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
}

.starR.on {
	background-position: 0 0;
}

#addReply {
	background-color: orange;
	border: none;
}

#addReply:hover {
	background: #FF6600;
}

.srContent {
	font-size: 1.1em;
}

.btn-primary {
	background-color: orange;
	border: none;
}

.btn-primary:hover {
	background: #FF6600;
}
</style>
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
		<div class="starRev">
			<span class="starR on">1</span> <span class="starR">2</span> <span
				class="starR">3</span> <span class="starR">4</span> <span
				class="starR">5</span>
		</div>
	</div>


	<!-- 댓글 출력 부분 -->
	<div class="replyList mt-5 pt-2">
		<ul id="replyListArea">

			<!-- 로그인 x 또는 댓글 작성자가 아닌 회원의 화면 -->
			<li class="reply-row">
				<div>
					<p class="rWriter"></p>
					<p class="rDate"></p>
				</div>

				<p class="rContent"></p>
			</li>


			<!-- 로그인한 회원이 댓글 작성자인 경우 -->
			<li class="reply-row">
				<div>
					<p class="rWriter"></p>
					<p class="rDate"></p>
				</div>

				<p class="rContent"></p>

				<div class="replyBtnArea">
					<button class="btn btn-primary btn-sm ml-1"
						onclick="deleteReply(2)"></button>
				</div>
			</li>

		</ul>
	</div>


</div>
<script>
	$('.starRev span').click(function() {
		$(this).parent().children('span').removeClass('on');
		$(this).addClass('on').prevAll('span').addClass('on');
		replyPoint = $(this).eq(0).text();
		console.log(replyPoint);
		return false;
	});

	var loginMemberId = "${loginMember.memName}";
	var parentSubscribe = "${subscribe.subName}";

	// 페이지 로딩 완료 시 댓글 목록 호출
	$(function() {
		selectReplyList();

	});

	// 해당 게시글 댓글 목록 조회 함수(ajax)
	function selectReplyList() {

		$.ajax({

			url : "${contextPath}/subscribereply/selectList.do",
			data : {
				"parentSubscribe" : parentSubscribe
			},
			type : "post",
			dataType : "JSON",
			success : function(srList) {
				//console.log(rList);

				$("#replyListArea").html("");

				$.each(srList,
						function(index, item) {
							var li = $("<li>").addClass("reply-row");
							var rWriter = $("<p>").addClass("rWriter").text(
									item.MemberName);
							var rPoint = $("<p>").addClass("rPoint").text(
									"별점 : " + item.Point);

							var div = $("<div>");
							div.append(rWriter).append(rPoint);

							var srContent = $("<p>").addClass("srContent")
									.html(item.ReplyContent);

							li.append(div).append(srContent);

							// 현재 댓글의 작성자와 로그인한 멤버의 아이디가 같을 때 버튼 추가
							if (item.MemberName == loginMemberId) {
								// 대댓글, 수정, 삭제 버튼 영역
								var replyBtnArea = $("<div>").addClass(
										"replyBtnArea");

								// ** 추가되는 댓글에 onclick 이벤트를 부여하여 버튼 클릭 시 수정, 삭제를 수행할 수 있는 함수를 이벤트 핸들러로 추가함. 
								var deleteReply = $("<button>").addClass(
										"btn btn-primary btn-sm ml-1").text(
										"삭제").attr("onclick",
										"deleteReply(" + item.ReplyNo + ")");

								replyBtnArea.append(deleteReply);

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

	//-----------------------------------------------------------------------------------------

	// 댓글 등록 (ajax)
	$("#addReply").on("click", function() {

		// 댓글 내용을 얻어와서 변수에 저장
		var replyContent = $("#replyContent").val().trim();

		// 로그인이 되어있지 않은 경우 == loginMemberId 전역변수에 저장된 값이 ""일 경우
		if (loginMemberId == "") {
			alert("로그인 후  이용해 주세요");

	}else { // 로그인이 되어있다면..?
			// 댓글 내용이 작성되어있는지 확인
			if (replyContent.length == 0) {
				alert("댓글을 입력해주세요");
			} else { // 로그인 되어있고 댓글도 작성되어있는 경우
				// 회원 번호를 얻어와서 변수에 저장
				var replyWriter = "${loginMember.memNo}";

				console.log(replyWriter);

				console.log(replyContent);

				console.log(parentSubscribe);

				$.ajax({
					url : "${contextPath}/subscribereply/insertReply.do",
					data : {
						"replyWriter" : replyWriter,
						"replyContent" : replyContent,
						"parentSubscribe" : parentSubscribe,
						"replyPoint" : replyPoint
					},

					type : "post",
					success : function(result) {
						if (result > 0) { // 댓글 삽입 성공 시
							// 댓글 작성 내용 삭제
							$("#replyContent").val("");

							// 성공 메세지 출력
							swal({
								"icon" : "success",
								"title" : "댓글 등록에 성공하였습니다"
							});

							// 댓글 목록을 새로 갱신(다시 조회)
							selectReplyList();

						}

					},
					error : function() {
						console.log("댓글 등록 실패");
					}
				});
			}

		}

	});

	//-----------------------------------------------------------------------------------------

	//댓글 삭제 함수
	function deleteReply(ReplyNo) {
		if (confirm("정말로 삭제하시겠습니까?")) {
			var url = "${contextPath}/subscribereply/deleteReply.do";

			$.ajax({
				url : url,
				data : {
					"ReplyNo" : ReplyNo
				},
				success : function(result) {
					if (result > 0) {
						selectReplyList(parentSubscribe);

						swal({
							"icon" : "success",
							"title" : "댓글 삭제 성공"
						});
					}

				},
				error : function() {
					console.log("ajax 통신 실패");
				}
			});
		}

	}
</script>