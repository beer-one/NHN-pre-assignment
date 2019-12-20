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

<form action="signup" method="post">
	<h5>이름</h5>
	<input type="text" name="name">
	<br><br>
	<h5>이메일</h5>
	<input type="text" name="email">
	<br><br>
	<h5>암호</h5>
	<input type="password" name="password">
	<br>
	<h5>암호 확인</h5>
	<input type="password" name="password-check">
	<br><br>
	<input type="submit" value="완료">
	<inpyt type="button" value="취소" onclick='location.href="/"'>

</form>

</body>
</html>
