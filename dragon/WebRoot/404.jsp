<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>404</title>
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
		<script type="text/javascript">
			function back(){
				window.localtion.href=history.back(-1);
			}
		</script>
	</head>
	<body>
		<div>
			<img src="${ctx }/images/404.gif" /><br/>
			<a href="javascript:void(0);" onclick="back()">返&nbsp;&nbsp;&nbsp;&nbsp;回</a>
		</div>
	</body>
</html>