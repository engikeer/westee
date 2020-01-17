<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.mfun.util.ControllerEnum" %>
<%@include file="../jsp/common/head.jsp"%>
    <div class="right">
        <img class="wColck" src="${pageContext.request.contextPath }/images/clock.jpg" alt="clock"/>
        <div class="wFont">
            <h2>${sessionScope.user_session.userName }</h2>
            <p>欢迎来到超市订单管理系统!</p>
        </div>
    </div>
</section>
<%@include file="../jsp/common/foot.jsp" %>
