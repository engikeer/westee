<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>

    <!-- 头部共享信息的引入。包含jquery，base标签，以及css样式 -->
    <%@ include file="/pages/common/header.jsp" %>

    <script type="text/javascript">
        $(function(){
            $(".deleteBtn").click(function () {
                // this 是当前的<a>
                // 找到对应的 title
                let td = $(this).parent().parent().children(":first");
                if(!confirm("删除《" + td.text() +"》？")) {
                    // 取消删除
                    return false;
                }
            });
        });
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">图书管理系统</span>

    <!-- 这是manager管理模块的共同菜单  -->
    <%@ include file="/pages/common/manager_menu.jsp" %>


</div>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>
        <tr>
            <td colspan="5"></td>
            <td colspan="2"><a href="pages/manager/book_edit.jsp">添加图书</a></td>
        </tr>
        <c:forEach items="${requestScope.list}" var="book">
            <tr>
                <td>${book.title}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                <td><a href="manager/bookManager?action=getBook&id=${book.id}">修改</a></td>
                <td><a class="deleteBtn" href="manager/bookManager?action=delete&id=${book.id}">删除</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

<!-- 这是页脚的引入 -->
<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>