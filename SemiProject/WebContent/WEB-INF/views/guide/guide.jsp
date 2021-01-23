<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<style>
		.guide-name {
            margin: auto;
			width: 650px;
			height: 60px;
			background: darkorange;
			color: ivory;
			border-radius: 10px;
			text-align: center;
			line-height: 50px;
			border: 2px solid navajowhite;
			cursor: pointer;
		}

		p.contents {
            margin: auto;
			border: 1px solid none;
			width: 600px;
			height: 300px;
			display: none;
		}
        .guide-header {
        	margin: 100px;
            text-align: center;
        }
        
        .guide-wrapper{
        	 margin: 100px;
        	 padding : 100px;
        	 height: 500px;
        	 margin-bottom: 200px;
        
        }
        
        p{
        	text-align: center;
        	line-height: 35px;
        }
        
	</style>

</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>



	
    <h1 class = "guide-header">SPECIAL SIMS GUIDE</h1>
    
    
    <div class="guide-wrapper">
    
    <div class="guide-name">WHY USE THE SIMS</div>
    <p class="contents" style="height: 150px;">구독 서비스를 한 눈에 보고 구독중인 서비스를 효율적으로 관리해보세요 !<br><br>

						저희 SIMS를 사용해야 하는 이유는 편의성 때문이죠 !</p>

    <div class="guide-name">HOW TO USE SIMS</div>
    <p class="contents" style="height: 170px;"><br>회원 가입 후 SUBSCRIBE 에서 현재 구독중인 서비스를 찾아 후기와 별점 등을<br> 확인 하실 수 있고  
			구독 할만한 서비스를 찾아보고<br> MY LIST로 이동하시어 구독 서비스를 담아 관리해 보세요 !</p>

    <div class="guide-name" >BOARD GUIDE</div>
    <p class="contents" style="text-align: left;">자유게시판 , 공동 구매 게시판 , 비공개 건의 게시판이 준비되어 있는데요 .<br>
		
		자유롭게 유저들과 소통하는 자리를 마련했고 , <br>
		
		 넷플릭스와 같이 한번에 N명까지의 서비스 이용이 가능한 경우 <br>
		같이 구매를 진행하여 돈을 절약하는 효과를 기대하고 있습니다 .<br>
		
		관리자와 편하게 소통할 수 있는 자리를 마련했으며 , <br>
		사용자가 언제든지 불편하거나 , 개선점 등을  의견을 낼수 있고,<br>
		회원 중 심하게 물의를 일으키는 회원을 신고하여 불량회원으로 처리하겠습니다.</p>

    <div class="guide-name">WHAT CONTENTS DO YO HAVE ON SIMS</div>
    <p class="contents"><br><br>SUBSCRIBE 페이지를 보면 알 수 있겠죠 ? 
						한번 SIMS 구경 해 보실래요 ?<br><a class="nav-link" href="${contextPath}/subscribe/main.do">
										SUBSCRIBE로 이동 </a>
						</p>
    
    </div>

   
	<jsp:include page="../common/footer.jsp"></jsp:include>
    
    <script>
        $("div").on('click',function(){

            if( $(this).next().css("display") == "none" ){ // 닫혀있으면
            // $(this) : 클릭된 div 태그
            // .next() : 클릭된 div 다음에 존재하는 요소 (p)
            // .css("display") : p태그의 style 중 display 속성 값을 얻어온다.

                $(this).siblings("p.contents").slideUp();
                // .siblings("p.contents") 
                // -> 클릭된 div 태그의 형제 요소 중 p태그 이면서 class가 contents인 요소를 선택
                // --> div 태그가 클릭 될 시 하나만 열고 나머지는 다 닫아라

                $(this).next().slideDown();

            }else   $(this).next().slideUp(); // 열려있으면
            
        });
    </script>
    
    


</body>

</html>