<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="page_nav">
	<a href="${requestScope.url}&pn=1">首页</a>
	<c:if test="${requestScope.page.hasPrev}">
		<a href="${requestScope.url}&pn=${requestScope.page.pageNo - 1}">上一页</a>
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
			<a href="${requestScope.url}&pn=${n}">${n}</a>
		</c:if>
	</c:forEach>

	<c:if test="${requestScope.page.hasNext}">
		<a href="${requestScope.url}&pn=${requestScope.page.pageNo + 1}">下一页</a>

	</c:if>
	<a href="${requestScope.url}&pn=${requestScope.page.pageCount}">末页</a>
	共 ${requestScope.page.pageCount} 页，${requestScope.page.totalCount} 条记录 到第<input name="pn" id="pn_input"/>页
	<input type="hidden" id="page_count" value="${requestScope.page.pageCount}">
	<input type="button" value="确定" id="goto">
</div>
		
<script type="text/javascript">
	$(function() {
		$("#goto").click(function () {
			let pn = $("#pn_input").val();
			let pageCount = $("#page_count").val();
			if (parseInt(pn) < eval(1)) {
				pn = 1;
			} else if (parseInt(pn) > parseInt(pageCount)) {
				pn = pageCount;
			}
			window.location.href = "${requestScope.url}&pn=" + pn;
		});
	});
</script>
		