<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>员工列表</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-3.4.1.js"></script>
</head>
<body>
<h2>员工列表</h2>
<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th><fmt:message key="id"/></th>
        <th><fmt:message key="name"/></th>
        <th><fmt:message key="email"/></th>
        <th><fmt:message key="gender"/></th>
        <th><fmt:message key="department"/></th>
        <th colspan="2"><fmt:message key="action"/></th>
    </tr>
    <c:forEach items="${requestScope.employees}" var="employee">
        <tr>
            <td>${employee.id}</td>
            <td>${employee.name}</td>
            <td>${employee.email}</td>
            <td>${employee.gender == 0 ? "女" : "男"}</td>
            <td>${employee.department.name}</td>
            <td><a href="${pageContext.request.contextPath}/employee/${employee.id}">修改</a></td>
            <!-- 不经过页面要发送 put 或 delete 方法的请求，通过 js 使用 ajax 发送 delete 请求，或者用 js 提交一个 form 表单来发送请求 -->
            <td><a class="deleteBtn" href="${pageContext.request.contextPath}/employee/${employee.id}">删除</a></td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/add">添加员工</a>
<form action="${pageContext.request.contextPath}/quickadd" method="post">
    <input type="hidden" name="empinfo" value="吴用-智多星@梁山.com-1-101">
    <input type="submit" value="快速添加员工">
</form>

<form id="deleteForm" action="" method="post">
    <input type="hidden" name="_method" value="delete">
</form>
<script type="text/javascript">
    $(function() {
        $(".deleteBtn").click(function() {
            let deleteForm = $("#deleteForm");
            deleteForm.attr("action", this.href);
            deleteForm.submit();
            return false;
        });
    });
</script>
</body>
</html>
