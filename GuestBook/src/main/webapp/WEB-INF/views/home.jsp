<%@page import="com.guestbook.web.vo.Note"%>
<%@page import="com.guestbook.web.vo.Member"%>
<%@page import="java.util.List"%>

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
<jsp:include page="layout/Header.jsp"/>
<br><br><br>
<h1>
방명록 글
<br>
<%
List<Note> notes = (List<Note>)request.getAttribute("notes");
%>

<table>
	<thead>
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>날짜</th>
	</tr>
	</thead>
	<tbody>
	<%
	int i = 1;
	for (Note n : notes) {
	%>
	<tr>
		<td><%= i %></td>
		<td><%= n.getTitle() %></td>
		<td><%= n.getEmail() %></td>
		<td><%= n.getCreatedDate() %></td>
	</tr>
	<%
		i++;
	}
	%>
	</tbody>
</table>
<br>
<input type="button" value="글 쓰기"onclick="window.location.href='/web/note/form'"/>
</h1>
</body>
</html>