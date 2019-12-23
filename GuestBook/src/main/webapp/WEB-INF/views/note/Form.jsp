<%@page import="com.guestbook.web.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
	
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>글쓰기</title>
</head>
<body>
<%
	Member member = (Member)session.getAttribute("member");
%>


<h1>글 작성</h1>
<br><br><br><br>

<form action="/web/note" method="post">
	<h5>이메일</h5>
	<%
	if(member == null) {
	%>
	<input type="email" name="email">
	<%
	} else { %>
	<%=member.getEmail() %>
	<%
	}%>
	<br>
	<h5>비밀번호</h5>
	<input type="password" name="password">
	<br>
	<h5>제목</h5>
	<input type="text" name="title">
	<br>
	<h5>내용</h5>
	<input type="text" name="context">
	<br>
	<input type="submit" value="완료">
	<input type="button" value="취소" onclick='window.history.back()'>
</form>

</body>
</html>
