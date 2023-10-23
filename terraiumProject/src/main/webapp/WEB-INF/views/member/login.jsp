<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


 <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
 <link href="resources/scss/login.scss" rel="stylesheet">
 <link href="resources/css/login.css" rel="stylesheet">

</head>
<body>


 <div class="wrap">
        <div class="login">
            <h2>Log-in</h2>
            <div class="login_sns">
            <li><a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=3b117227ae449f5a74e1d81dda8cfc66&redirect_uri=http://localhost:8088/tree/login.me"><img height="50px" src="resources/img/login/kakaotalk.png"></a></li>
            <li><a href=""><i class="fab fa-facebook-f"></i></a></li>
            <li><a href=""><i class="fab fa-twitter"></i></a></li>
            </div>
            <div class="login_id">
                <h4>ID</h4>
                <input type="text" name="userId" id="userId" placeholder="아이디를 입력해주세요">
            </div>
            <div class="login_pw">
                <h4>Password</h4>
                <input type="password" name="userPwd" id="userPwd" placeholder="비밀번호를 입력해주세요">
            </div>
            <div class="login_etc">
                <div class="checkbox">
                <a href="###">비밀번호를 잃어버렸나요?</a>
                </div>
                <div class="forgot_pw">
                <a href="###">회원 가입</a>
            </div>
            </div>
            <div class="submit">
                <input type="submit" value="로그인">
            </div>
        </div>
    </div>




</body>
</html>