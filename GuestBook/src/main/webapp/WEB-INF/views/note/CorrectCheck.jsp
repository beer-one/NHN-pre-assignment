<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Check</title>
</head>
<body>
<%
	boolean result = (Boolean)request.getAttribute("success");
	
	if(result) {
%>
		<script type="text/javascript">
			alert("수정 완료");
			window.location.href="/"
		</script>
<% 
	}
	else {
%>
	<script type="text/javascript">
		alert("비밀번호가 일치하지 않습니다.");
		window.history.back();
	</script>
<% 
	}
		
%>
</body>
</html>