<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>员工列表</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-3.4.1.js"></script>
</head>
<body>
<h2>员工列表</h2>
<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>姓名</th>
        <th>Email</th>
        <th>性别</th>
        <th>部门</th>
        <th colspan="2">操作</th>
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
