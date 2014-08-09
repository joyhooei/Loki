<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>统计管理</title>
		<%@ include file="/pages/common/css.jsp"%>
		<%@ include file="/pages/common/js.jsp"%>
		<script type="text/javascript" src="${ctx}/js/src/login.js"></script>
		<style type="text/css">
			b.rtop,b.rbottom {
				display: block;
				background: #FFFFFF
			}
			
			b.rtop b,b.rbottom b {
				display: block;
				height: 1px;
				overflow: hidden;
				background: #9BD1FA
			}
			
			b.r1 {
				margin: 0 5px
			}
			
			b.r2 {
				margin: 0 3px
			}
			
			b.r3 {
				margin: 0 2px
			}
			
			b.rtop b.r4,b.rbottom b.r4 {
				margin: 0 1px;
				height: 2px
			}
		</style>
	</head>

	<body style="margin: 0px; padding: 0px;">
		<!--[if lte IE 6]>
		<style type="text/css">
		#ie6nomore,#ie6-tips,#ie6-dl,#ie6-tips p{float:left}
		#ie6nomore{width:100%;height:56px;background-color:#2A2A2A;color:#CCC;font-size:12px;font-family:Verdana, Geneva, sans-serif}
		#ie6nomore a{text-decoration:none;color:#A8C779}
		#ie6-content{width:700px;margin:0 auto}
		#ie6-tips{width:500px}
		#ie6-tips p{line-height:150%;text-align:left;height:28px}
		#ie6{margin:8px 0 -8px}
		#up{font-weight:700;width:500px}
		#ie6-dl{float:left;width:220px;margin-top:18px; margin-left:400px;margin-top:-35px;}
		#ie6-dl span{margin-left:10px;float:left;}
		#ff{background:url(http://cspromod.googlecode.com/files/ff.gif)}
		#ie8{background:url(http://cspromod.googlecode.com/files/ie8.gif)}
		#ch{background:url(http://cspromod.googlecode.com/files/ch.gif)}
		#ff,#ie8,#sa,#ch,#op{background-repeat:no-repeat}
		#ff a,#ie8 a,#sa a,#ch a,#op a,{margin-left:20px}
		</style>
		<div id="ie6nomore">
		<div id="ie6-content">    
		<div id="ie6-tips">
		<p id="ie6">请注意：您正在使用 IE 6 浏览器,可能无法正常使用后台管理系统的功能</p><br/>
		<p id="up">建议：升级或更换另一款浏览器，将获得更好的浏览体验</p>
		</div>      
		<div id="ie6-dl">
		<span id="ie8"><a rel="nofollow" href='http://go.microsoft.com/fwlink/?linkid=261544' title='下载 Internet Explorer 8' target='_blank'>Internet Explorer 8</a></span>
		<span id="ff"><a rel="nofollow" href='http://download.firefox.com.cn/releases/webins3.0/official/zh-CN/Firefox-latest.exe' title='下载 Firefox' target='_blank'>Firefox</a></span>
		<span id="ch"><a rel="nofollow" href='https://dl.google.com/tag/s/appguid%3D%7B8A69D345-D564-463C-AFF1-A69D9E530F96%7D%26iid%3D%7BB3C756AA-8F29-E582-8185-698A6B2FD9A0%7D%26lang%3Dzh-CN%26browser%3D2%26usagestats%3D0%26appname%3DGoogle%2520Chrome%26needsadmin%3Dfalse%26installdataindex%3Ddefaultbrowser/update2/installers/ChromeSetup.exe' title='下载 Google Chrome' target='_blank'>Chrome</a></span>
		</div>
		<![endif]-->
		<table width="100%" cellpadding="0" cellspacing="0"
			style="font-size: 12px; padding-top: 0px; margin-top: 0px;"
			align="center">
			<tr bgcolor="#0055a2">
				<td height="50"
					style="padding-top: 0px; padding-bottom: 0px; padding-left: 50px;">
<%--					<img src="${ctx}/images/logoNew6.gif" />--%>
				</td>
			</tr>

		</table>
		<form id="loginForm" name="loginForm"
			action="${ctx}/login.xhtml" method="post">
			<table align="center" cellspacing="0" cellpadding="0"
				style="font-size: 12px; margin-top: 15px; width: 100%; min-width: 960px; _width: 960px;">
				<tr>
					<td align="center">
						<div
							style="width: 340px; border-color: #BECFEB; border-style: solid; border-width: 2px; margin-left: 25px; background-color: #EDF5FF"
							align="left">
							<table style="font-size: 12px;" width="100%">
								<tr>
									<td colspan="2" height="50px;"
										style="padding-left: 20px; padding-top: 10px; font-size: 20px; color: #0050b3; font-weight: bold; font-family: '微软雅黑,黑体'"
										nowrap="nowrap">
										用户登录
									</td>
								</tr>
								<tr>
									<td height="50px;"
										style="padding-left: 20px; font-size: 20px; font-weight: bold;">
										登陆账号：
									</td>
									<td height="50px;" style="font-size: 20px; font-weight: bold;">
										<input id="username" maxlength="50" name="username" type="text"
											value="${username }"
											style="width: 200px; height: 37px; font-size: 20px; font-weight: bold; padding-top: 5px; border-color: bcc5d5; border-style: outset; border-right-style: solid; border-bottom-style: solid;" />
									</td>
								</tr>
								<tr>
									<td height="50px;"
										style="padding-left: 20px; font-size: 20px; font-weight: bold;">
										登陆密码：
									</td>
									<td height="50px;"
										style="font-size: 20px; font-weight: bold;">
										<input id="password" maxlength="50" name="password"
											type="password"
											style="width: 200px; height: 37px; font-size: 20px; font-weight: bold; padding-top: 5px; border-color: bcc5d5; border-style: outset; border-right-style: solid; border-bottom-style: solid;" />
									</td>
								</tr>
								<tr>
									<td height="65px;" align="center"
										style="padding-top: 0px; padding-left: 20px;" colspan="2">
										<font color="red">${resultMap.message}</font>
										<input type="submit" value=""
											style="background-image: url(${ctx}/images/a-btn2.jpg); width:92px; height:24px; border:0" />
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>