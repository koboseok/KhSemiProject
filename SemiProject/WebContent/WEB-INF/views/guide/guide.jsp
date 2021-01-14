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

		pre.contents {
            margin: auto;
			border: 1px solid none;
			width: 600px;
			height: 250px;
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
        	
        
        }
        
	</style>

</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>



	
    <h1 class = "guide-header">SPECIAL SIMS GUIDE</h1>
    
    
    <div class="guide-wrapper">
    
    <div class="guide-name">WHY USE THE SIMS</div>
    <pre class="contents">저희 심즈를 사용해야 하는 이유는 바로 편의성 때문이죠 !</pre>

    <div class="guide-name">HOW TO USE SIMS</div>
    <pre class="contents">2</pre>

    <div class="guide-name">BOARD GUIDE</div>
    <pre class="contents">3</pre>

    <div class="guide-name">WHAT CONTENTS DO YO HAVE ON SIMS</div>
    <pre class="contents">4</pre>
    
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