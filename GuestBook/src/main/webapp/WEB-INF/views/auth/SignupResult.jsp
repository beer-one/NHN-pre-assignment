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
			alert("로그인 성공");
			window.location.href="/web/"
		</script>
<% 
		System.out.println("Success");
	}
	else {
		String message;
		if(result.equals("password"))
			message = "패스워드가 일치하지 않습니다.";
		else
			message = "이미 존재하는 이메일입니다.";
%>
	<script type="text/javascript">
		var message = '<%= message %>';
		alert("로그인 실패: " + message);
		window.history.back();
	</script>
<% 
		System.out.println("Fail");
	}
		
%>
</body>
</html>