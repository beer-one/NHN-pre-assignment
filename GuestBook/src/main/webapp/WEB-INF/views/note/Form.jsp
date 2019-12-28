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

<!-- 이메일이 올바른지 클라이언트에서 체크 -->
<script type="text/javascript">
function check() {
	var member = <%= member %>;
	
	if(!member) {
		var inputEmail = document.getElementById("emailText").value;
		var emailPattern = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
		
		if(emailPattern.test(inputEmail) == false) {
			alert("올바르지 않은 이메일 형식입니다.(Client)");
			
		} else {
			document.getElementById("form").submit();	
		}
	} else 
		document.getElementById("form").submit();
}
</script>


<h1>글 작성</h1>
<br><br><br><br>

<form action="/note" method="post" id="form">
	<h5>이메일</h5>
	<%
	if(member == null) {
	%>
	<input type="email" name="email" id="emailText">
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
	<textarea name="context" cols="60" rows="20"></textarea>
	
	<br>
	<input type="button" value="완료" onclick='check()'>
	<input type="button" value="취소" onclick='window.history.back()'>
</form>

</body>
</html>
