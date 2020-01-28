
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Scope</title>
</head>
<body>
<%
    String name = request.getParameter("name");
    if (name != null) {
        pageContext.setAttribute("pName", name);
        System.out.println("不为 null");
    }
%>
<h3>name: ${pName}</h3>
</body>
</html>
