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
	String result = (String)request.getAttribute("result");
	
	if(result.equals("success")) {
%>
		<script type="text/javascript">
			alert("회원가입 성공");
			window.location.href="/"
		</script>
<% 
		System.out.println("Success");
	}
	else {
		String message;
		if(result.equals("emailForm"))
			message = "올바르지 않은 이메일 형식입니다.";
		else
			message = "이미 존재하는 이메일입니다.";
%>
	<script type="text/javascript">
		var message = '<%= message %>';
		alert("회원가입 실패: " + message);
		window.history.back();
	</script>
<% 
		System.out.println("Fail");
	}
		
%>
</body>
</html>