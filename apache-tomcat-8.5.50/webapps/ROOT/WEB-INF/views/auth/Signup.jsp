<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
	
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Signup</title>
</head>
<body>
<h1>회원 가입</h1>
<br><br><br><br>


<script type="text/javascript">
function check() {
	var inputEmail = document.getElementById("emailText").value;
	var emailPattern = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	
	if(emailPattern.test(inputEmail) == false) {
		alert("올바르지 않은 이메일 형식입니다.");
		
	} else {
		var password = document.getElementById("passwordText").value;
		var passwordCheck = document.getElementById("passwordCheckText").value;
		if(password == passwordCheck) 
			document.getElementById("form").submit();	
		else
			alert("패스워드가 일치하지 않습니다.");
			
	}
}
</script>

<form action="signup" method="post" id="form">
	<h5>이름</h5>
	<input type="text" name="name">
	<br><br>
	<h5>이메일</h5>
	<input type="text" name="email" id="emailText">
	<br><br>
	<h5>암호</h5>
	<input type="password" name="password" id="passwordText">
	<br>
	<h5>암호 확인</h5>
	<input type="password" name="password-check" id="passwordCheckText">
	<br><br>
	<input type="button" value="완료" onclick='check();'>
	<input type="button" value="취소" onclick='window.history.back()'>

</form>



</body>
</html>
