<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<%-- jsp 注释 --%>
<h3>Hello jsp!</h3>
<!-- html 注释 -->
<h4>jsp</h4>
<%
    int sum = 0;
    for (int i = 1; i <= 100; i++) {
        sum += i;
    }
    out.println("<h4>第一次输出 sum：" + sum);
%>
<h4>再输出 sum：<%= sum %></h4>
<% out.println("<h4>最后一次输出 sum：" + sum + "</h4>"); %>
<% for (int i = 0; i < 5; i++) {%>
<p>一只喵喵</p>
<% }%>
</body>
</html>
