<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.Context" %>
<%@ page import="com.mfun.pojo.Person" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>JNDI Test</title>
</head>
<body>
<%
    Context initContext = new InitialContext();
    Context envContext = (Context) initContext.lookup("java:comp/env");
    Person person = (Person) envContext.lookup("mfun/PersonFactory");
//    DataSource ds = (DataSource) initContext.lookup("java:comp/env/mfun/mysqlDataSource");
    DataSource ds = (DataSource) envContext.lookup("mfun/mysqlDataSource");
%>
<h3>Hello JNDI</h3>
<h3>Get <%= ds %></h3>
<h5>你好：<%= person %></h5>
</body>
</html>
