//입력 값들이 유효성 검사가 진행되었는지 확인하기 위한 객체 생성
var validateCheck = {
    "email" : false,
    "pwd1" : false,
    "pwd2" : false,
    "name" : false,
    "phone2" : false,
}

//이메일 유효성 검사 + 중복검사 
$("#email").on("input", function() {
    var regExp = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;
    var value = $("#email").val();


	if(!(value.length>0)){
		$("#checkEmail").html("&nbsp;");
    } else if(!regExp.test(value)) {
        $("#checkEmail").text("유효하지 않은 이메일 형식입니다.").css("color", "red");
        validateCheck.email = false;
    } else {
            //ajax를 이용한 실시간 아이디 중복 검사
            $.ajax({
                url : "emailDupCheck.do", //상대경로 작성. 홈페이지 url의 모양을 떠올려보면 이해가 간다.
                data : {"email" : value}, 
                type : "post",
                success : function(result) {
                    if(result == 0) {
                        $("#checkEmail").text("사용 가능한 이메일입니다.").css("color", "green")
                        validateCheck.id = true;
                    } else {
                        $("#checkEmail").text("이미 사용 중인 이메일입니다.").css("color", "red")
                        validateCheck.id = false;
                    }
                },
                error: function() {
                    console.log("이메일 중복 검사 실패");
                }
            });
    }
});


 //비밀번호 유효성 검사
 //영어 대, 소문자 + 숫자, 총 6~12글자
 //+비밀번호, 비밀번호 확인의 일치 여부
 //+비밀번호를 입력하지 않거나 유효하지 않은 상태로 비밀번호 확인을 작성하는 경우

 $("#pwd1, #pwd2").on("input", function() {
    //비밀번호 유효성 검사
    var regExp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,16}$/;

    var v1 = $("#pwd1").val();
    var v2 = $("#pwd2").val();

    if(!regExp.test(v1)) {
        $("#checkPwd1").text("영문 대, 소문자, 숫자를 포함한 8~12글자로 작성해주세요.").css("color","red");
        validateCheck.pwd1 = false;
    } else {
        $("#checkPwd1").text("사용 가능한 비밀번호입니다.").css("color","green");
        validateCheck.pwd1 = true;
    }

    //비밀번호가 유효하지 않은 상태에서 비밀번호 확인 작성 시
    if(!validateCheck.pwd1 && v2.length>0) {
        swal("유효한 비밀번호를 먼저 작성해주세요.");
        $("#pwd2").val(""); //비밀번호 확인에 입력한 값 삭제
        $("#pwd1").focus();
    } else {
       //비밀번호, 비밀번호 확인의 일치 여부
       if(v1.length==0 || v2.length==0) {
           $("#checkPwd2").html("&nbsp;");
       } 
       
       else if(v1==v2) {
        $("#checkPwd2").text("비밀번호가 일치합니다.").css("color", "green");
        validateCheck.pwd2 = true;
       }

       else {
           $("#checkPwd2").text("비밀번호가 일치하지 않습니다.").css("color","red");
           validateCheck.pwd2 = false;
       }
    }
 });




 //전화번호 유효성 검사
 $(".phone").on("input", function() {
    //전화번호 input태그에 4글자 이상 초과 입력하지 못하게 하는 이벤트
    if($(this).val().length>4) {
        $(this).val($(this).val().slice(0,4));
    }

    var regExp1= /^\d{3,4}$/;
    var regExp2= /^\d{4}$/;

    var v1 = $("#phone2").val();
    var v2 = $("#phone3").val();

    if(!regExp1.test(v1) || !regExp2.test(v2)) {
        $("#checkPhone").text("전화번호가 유효하지 않습니다.").css("color", "red");
        validateCheck.phone2 = false;
    } else {
        $("#checkPhone").text("사용할 수 있는 전화번호입니다.").css("color", "green");
        validateCheck.phone2 = true;
    }
 });


 //별명 유효성 검사
 $("#name").on("input",function() {
    var regExp = /^[\wㄱ-ㅎㅏ-ㅣ가-힣]{2,10}$/;
    var value = $("#name").val();

   if(!(value.length>0)){
		$("#checkName").html("&nbsp;");
	} else if(!regExp.test(value)) {
        $("#checkName").text("사용할 수 없는 별명입니다.").css("color", "red");
        validateCheck.name = false;
    } else {
        $.ajax({
           url : "nameDupCheck.do", //상대경로 작성. 홈페이지 url의 모양을 떠올려보면 이해가 간다.
           data : {"name" : value}, 
           type : "post",
           success : function(result) {
               if(result == 0) {
                 $("#checkName").text("사용 가능한 별명입니다.").css("color", "green")
                 validateCheck.id = true;
               } else {
                 $("#checkName").text("이미 사용 중인 별명입니다.").css("color", "red")
                  validateCheck.id = false;
               }
           },
           error: function() {
           console.log("별명 중복 검사 실패");
          }
       });
    }
});



 function validate() {
    //팝업 중복검사 전용: 아이디 중복검사 여부 확인
    /*if($("#idDup").val() != "true") {
        swal("아이디 중복 검사를 진행해 주세요.");
        $("#idDupCheck").focus();
        return false;
    } */

    //유효성 검사 여부 확인
     for(var key in validateCheck) {
         if(!validateCheck[key]) {
             var msg;
             switch(key) {
                case "email" : msg="이메일이"; break;
                case "pwd1" : 
                case "pwd2" : msg="비밀번호가"; break;
                case "name" : msg="별명이"; break;
                case "phone2" : msg="전화번호가"; break;
                case "email" : msg="이메일이"; break;
             }
             swal(msg + " 유효하지 않습니다.");
             $("#"+key).focus();
             return false;
             //기본이벤트 제거
         }
     }
 }
