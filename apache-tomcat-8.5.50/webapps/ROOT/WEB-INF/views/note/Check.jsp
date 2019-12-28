<%@page import="com.guestbook.web.vo.Note"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Note result = (Note)request.getAttribute("note");
	
	if(result != null) {
%>
		<script type="text/javascript">
			alert("게시물이 저장되었습니다.");
			window.location.href="/web/"
		</script>
<% 
	}
	else {
		String reason = (String)request.getAttribute("reason");
		if(reason.equals("emailForm")) {
%>
			<script type="text/javascript">
				alert("올바르지 않은 이메일 형식입니다.(Server))");
				window.history.back();
			</script>
<% 		} else {
%>	
			<script type="text/javascript">
				alert("등록되지 않은 이메일이거나 비밀번호가 일치하지 않습니다.");
				window.history.back();
			</script>
<%
		}
	}		
%>
</body>
</html>