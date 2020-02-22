<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
<form action="app/book/hello" method="post">
    <label for="username">用户名</label>
    <input type="text" name="username" id="username">
    <input type="submit" value="content">
</form>

<form action="app/book/page" method="post">
    <label for="user">用户名</label>
    <input type="text" name="username" id="user">
    <input type="submit" value="page">
</form>
</body>
</html>
