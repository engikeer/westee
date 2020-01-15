<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>JSTL Demo</title>
</head>
<body>
<%-- 通过表单提交参数，表单提交给自己 --%>
<form action="jstl.jsp" method="GET">
    <label for="name">用户名</label>
    <input type="text" id="name" name="username" value="${param.username}">
    <input type="submit" value="提交">
</form>

<%-- 用户名是 admin 则登陆--%>
<%-- test 是判断条件，var 是接收结果的参数，标签内是判断为真时执行的内容 --%>
<c:if test="${param.username == 'admin'}" var="isAdmin">
    <c:out value="管理员身份登陆" />
</c:if>

<p>是否管理员：<c:out value="${isAdmin}" /></p>

<%-- 定义变量 score --%>
<c:set var="score" scope="session" value="${85 * 2}"/>

<%-- 选择执行--%>
<c:choose>
    <c:when test="${score >= 100}">
        成绩优秀
    </c:when>
    <c:when test="${score < 100}">
        还需努力
    </c:when>
</c:choose>

<%
    ArrayList<String> names = new ArrayList<>();
    names.add("Ada");
    names.add("Bob");
    names.add("Carl");
    // 将要遍历的变量放在能访问到作用域内
    pageContext.setAttribute("names", names);
%>

<%-- 遍历，items 是要遍历的集合，通过 findAttribute 获取，var 是为元素指定的变量名 --%>
<c:forEach items="${names}" var="name" >
    <p>名称：${name}</p>
</c:forEach>

</body>
</html>
