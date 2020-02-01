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

            $("#goto").click(function () {
                let pn = $("#pn_input").val();
                let pageCount = $("#page_count").val();
                if (parseInt(pn) < eval(1)) {
                    pn = 1;
                } else if (parseInt(pn) > parseInt(pageCount)) {
                    pn = pageCount;
                }
                window.location.href = "manager/bookManager?action=page&pn=" + pn;
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
        <c:forEach items="${requestScope.page.data}" var="book">
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
    <div id="page_nav">
        <a href="manager/bookManager?action=page">首页</a>
        <c:if test="${requestScope.page.hasPrev}">
            <a href="manager/bookManager?action=page&pn=${requestScope.page.pageNo - 1}">上一页</a>
        </c:if>

        <%-- 为保证始终显示 5 页，结束页是 min(max(pageNo + 2, 5), pageCount) --%>
        <%-- 开始页是 max(min(pageNo - 2, pageCount - 4), 1) --%>
        <c:set var="end" scope="page"
               value="${requestScope.page.pageNo + 2 > 5 ? requestScope.page.pageNo + 2 : 5}"/>
        <c:set var="start" scope="page"
               value="${requestScope.page.pageNo - 2 < requestScope.page.pageCount - 4
               ? requestScope.page.pageNo - 2 : requestScope.page.pageCount - 4}"/>
        <c:forEach begin="${start < 1 ? 1 : start}"
                   end="${end < requestScope.page.pageCount ? end : requestScope.page.pageCount}"
                   var="n">
            <c:if test="${n == requestScope.page.pageNo}">
                <span style="color: blue">【${requestScope.page.pageNo}】</span>
            </c:if>
            <c:if test="${n != requestScope.page.pageNo}">
                <a href="manager/bookManager?action=page&pn=${n}">${n}</a>
            </c:if>
        </c:forEach>

        <c:if test="${requestScope.page.hasNext}">
            <a href="manager/bookManager?action=page&pn=${requestScope.page.pageNo + 1}">下一页</a>

        </c:if>
        <a href="manager/bookManager?action=page&pn=${requestScope.page.pageCount}">末页</a>
        共 ${requestScope.page.pageCount} 页，${requestScope.page.totalCount} 条记录 到第<input name="pn" id="pn_input"/>页
        <input type="hidden" id="page_count" value="${requestScope.page.pageCount}">
        <input type="button" value="确定" id="goto">
    </div>
</div>

<!-- 这是页脚的引入 -->
<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>