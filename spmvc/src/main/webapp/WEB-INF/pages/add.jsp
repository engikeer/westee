<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>员工添加</title>
</head>
<body>
<h2>员工添加</h2>
<!-- 可以省略 action 属性，会将表单数据以 POST 方式提交到表单所在地址 -->
<form:form action="${pageContext.request.contextPath}/employee" method="post" modelAttribute="employee">
    <label for="name">姓名：</label>
    <!-- path 是提交时的参数，也是回显对象的属性名。path 还会默认作为该标签的 id -->
    <form:input path="name" />
    <br>
    <label for="email">Email：</label>
    <form:input path="email" />
    <br>
    性别：<label for="male">男</label>
    <form:radiobutton path="gender" value="1" id="male" />
    <label for="female">女</label>
    <form:radiobutton path="gender" value="0" id="female" />
    <br>
    <label for="department">部门：</label>
    <!-- items 指定要遍历的集合，itemLabel 和 itemValue 指定作为标签和值的"属性名"-->
    <form:select path="department.id" id="department" items="${requestScope.departments}" itemLabel="name" itemValue="id"/>
    <br>
    <input type="submit" value="添加">
</form:form>

<%--<form action="">--%>
<%--    <label for="name">姓名：</label>--%>
<%--    <input type="text" name="name" id="name">--%>
<%--    <br>--%>
<%--    <label for="email">Email：</label>--%>
<%--    <input type="text" name="email" id="email">--%>
<%--    <br>--%>
<%--    性别：<label for="male">男</label>--%>
<%--    <input type="radio" name="gender" value="1" id="male">--%>
<%--    <label for="female">女</label>--%>
<%--    <input type="radio" name="gender" value="0" id="female">--%>
<%--    <br>--%>
<%--    <label for="department">部门：</label>--%>
<%--    <select id="department" name="department.id">--%>
<%--        <c:forEach items="${requestScope.departments}" var="department">--%>
<%--            <option value="${department.id}">${department.name}</option>--%>
<%--        </c:forEach>--%>
<%--    </select>--%>
<%--    <br>--%>
<%--    <input type="submit" value="添加">--%>
<%--</form>--%>
</body>
</html>
