<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>

    <!-- 头部共享信息的引入。包含jquery，base标签，以及css样式 -->
    <%@ include file="/pages/common/header.jsp" %>

    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">我的订单</span>

    <!-- 登录成功之后所有相同的菜单  -->
    <%@ include file="/pages/common/login_success_menu.jsp" %>

</div>

<div id="main">

    <c:if test="${empty requestScope.orders}">
        <h2>当前没有订单，<a href="index.jsp">快去买吧</a></h2>
    </c:if>
    <c:if test="${!empty requestScope.orders}">
        <table>
            <tr>
                <td>订单号</td>
                <td>日期</td>
                <td>金额</td>
                <td>状态</td>
                <td>详情</td>
            </tr>
            <c:forEach items="${requestScope.orders}" var="order">
                <tr>
                    <td>${order.orderId}</td>
                    <td>${order.createDate}</td>
                    <td>${order.totalPrice}</td>
                    <td>${order.statusDesc}</td>
                    <td>
                        <a href="">查看详情</a>
                    </td>
                </tr>

            </c:forEach>
        </table>
    </c:if>

</div>

<!-- 这是页脚的引入 -->
<%@ include file="/pages/common/footer.jsp" %>

</body>
</html>