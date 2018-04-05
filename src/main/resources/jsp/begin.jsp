<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
body{
text-align:center;
}
form{
vertical-align: center;
margin-top: 500px;
}
form input[type=text] {
	width: 500px;
	height: 20px;
	font-size: 20px;
}
form input[type=submit] {
	width: 100px;
	height: 40px;
	font-size: 20px;
	background-color: #AFBBC7;
}
</style>
</head>
<body>

<form action="${path }/getMap.do" method="post">
	URL:<input name="url" type="text">
<br>
<br>
	<input value="提交" type="submit"><br>
</form>
</body>
</html>