<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>统计平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/pages/common/css.jsp"%>
<%@ include file="/pages/common/js.jsp"%>
<script type="text/javascript" src="${ctx}/js/src/index.js"></script>
</head>
<body>
	<div>
		<jsp:include page="/pages/inc/top.jsp" />
	</div>
	<div class="main">
		<!-- 网页Left -->
		<div class="left">
			<jsp:include page="/pages/inc/left.jsp" />
		</div>
		<!-- 网页内容 -->
		<div class="right">
			<iframe src="${ctx }/pages/welcom.jsp" name="contentFrame" id="contentFrame" class="contentFrame" scrolling="no"></iframe>
		</div>
	</div>
</body>
</html>