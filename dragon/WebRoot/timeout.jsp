<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/tag.jsp"%>
<html>
	<head>
		<title>登录已超时</title>
		<style type="text/css">
			div {
				margin: 50 auto;
				padding: 15px 50px;
				text-align: center;
			}
			
			a {
				font-weight: bold;
				font-family: "宋体";
				font-size: 18px;
			}
		</style>
	</head>
	<body>
		<div id="sessionOut" style="text-align: center;">
			<img src="${ctx}/images/chao.gif" /><br/>
			<a href="${ctx }/index.jsp">登&nbsp;&nbsp;&nbsp;&nbsp;录</a>
		</div>
	</body>

	<script type="text/javascript">
		if (self != top) {
			window.top.location = window.location;
		}
	</script>
</html>