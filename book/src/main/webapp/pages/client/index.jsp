<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>

    <!-- 头部共享信息的引入。包含jquery，base标签，以及css样式 -->
    <%@ include file="/pages/common/header.jsp" %>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">网上书城</span>
    <%@include file="/pages/common/login_success_menu.jsp"%>
</div>

<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="user/bookUser" method="get">
                <input type="hidden" name="action" value="pageByPrice" />
                价格：<input id="min" type="text" name="min" value="${ param.min }"> 元 -
                <input id="max" type="text" name="max" value="${ param.max }"> 元
                <input type="submit" value="查询" id="price_search"/>
                <c:if test="${param.action=='pageByPrice'}">
                    <a href="user/bookUser?action=page"><input type="button" value="取消"></a>
                </c:if>
            </form>
        </div>
        <div style="text-align: center">
            <span>您的购物车中有3件商品</span>
            <div>
                您刚刚将<span style="color: red">时间简史</span>加入到了购物车中
            </div>
        </div>

        <c:forEach items="${requestScope.page.data}" var="item">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="${ item.imgPath }" />
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${ item.title }</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${ item.author }</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">￥${ item.price }</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${ item.sales }</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${ item.stock }</span>
                    </div>
                    <div class="book_add">
                        <button>加入购物车</button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <%@ include file="/pages/common/page.jsp" %>

</div>

<!-- 这是页脚的引入 -->
<%@ include file="/pages/common/footer.jsp" %>

</body>
</html>