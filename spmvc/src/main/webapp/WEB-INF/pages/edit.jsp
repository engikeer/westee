<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>修改员工</title>
</head>
<body>
<h2>员工修改</h2>
<form:form action="${pageContext.request.contextPath}/employee/${requestScope.employee.id}" method="post" modelAttribute="employee">
    <input type="hidden" name="_method" value="put">
    <input type="hidden" name="id" value="${requestScope.employee.id}">
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
    <input type="submit" value="修改">
</form:form>
</body>
</html>
