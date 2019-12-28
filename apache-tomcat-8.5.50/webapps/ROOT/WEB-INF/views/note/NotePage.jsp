<%@page import="com.guestbook.web.vo.Note"%>
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
	Note note = (Note)request.getAttribute("note");
%>


<h1>글 작성</h1>
<br><br><br><br>

<h5>작성자</h5>
<%= note.getEmail() %>
<h5>제목</h5>
<%=note.getTitle() %>
<h5>작성날짜</h5>
<%= note.getCreatedDate() %>
<h5>수정날짜</h5>
<%=note.getCorrectedDate() %>
<h5>내용</h5>
<textarea name="context" cols="60" rows="20" readonly>
<%=note.getContext() %>
</textarea>
<br>
<input type="button" value="수정" onclick='window.location.href="/web/note/correct?no=<%= note.getNo() %>"'>
<input type="button" value="뒤로가기" onclick='window.history.back()'>


</body>
</html>
