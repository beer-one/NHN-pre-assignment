<%@page import="com.guestbook.web.vo.Member"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
	
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home</title>	
</head>
<body>
<div style="background-color:#FF4400;height:80px;font-size:40px;color:#FFFFFF;">
<center style="padding:20px;">방명록

<%
	Member member = (Member)session.getAttribute("member");
	if(member == null) {
%>
		<a style="font-size:20px;float:right;color:#FFFFFF;padding:10px;" href="auth/login">로그인</a>
		<a style="font-size:20px;float:right;color:#FFFFFF;padding:10px;" href="auth/signup">회원가입</a>
<%
	} else {
%>
		<a style="font-size:20px;float:right;color:#FFFFFF;padding:10px;" href="auth/logout">로그아웃</a>
		<a style="font-size:20px;float:right;color:#FFFFFF;padding:10px;" href="member/mypage"><%=member.getName()%>></a>
<% 		
	}
%>
</center>
</div>

</body>
</html>
