<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
	
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>
</head>
<body>
<h1>로그인</h1>
<br><br><br><br>

<form action="login" method="post">
	
	<h5>이메일</h5>
	<input type="text" name="email">
	<br>
	<h5>암호</h5>
	<input type="password" name="password">
	<input type="submit" value="로그인">
	<input type="button" value="취소" onclick='window.history.back()'>

</form>

</body>
</html>
