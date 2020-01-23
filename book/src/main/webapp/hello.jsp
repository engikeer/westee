<%@ page import="java.sql.Connection" %>
<%@ page import="com.mfun.utils.ConnectionUtils" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<%
    String username = "Wow";
    try (Connection connection = ConnectionUtils.getConnection()) {
        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT * FROM bs_user");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            username = resultSet.getString("username");
        }
    }
%>
<h3><%= username + "，见到你很高兴！"%></h3>
</body>
</html>
