<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<html>
<head>
    <title>欢迎</title>
</head>
<body>
    <h3>成功页面</h3>
<% System.out.println("渲染页面"); %>
    <h3>你好：${requestScope.name}</h3>
</body>
</html>
