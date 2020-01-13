<html>
<head>
    <title>Servlet-01</title>
    <%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
</head>
<body>
<h2>首页：Hello World!</h2>
<%--提交到当前项目下的 /login，而不是服务器根路径下的 /login--%>
<%--pageContext.request.contextPath 是代表当前项目路径的 jsp 变量，需要已导入 jsp-api 包--%>
<form action="${pageContext.request.contextPath}/login" name="login" method="post">
    <label for="user">用户名</label>
    <input type="text" name="username" id="user">
    <br>
    <label for="pw">密码</label>
    <input type="password" name="password" id="pw"><br>
    <input type="submit">
</form>
</body>
</html>
