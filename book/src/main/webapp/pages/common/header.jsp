<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% pageContext.setAttribute("req", request); %>
<base href="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}/" />
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>