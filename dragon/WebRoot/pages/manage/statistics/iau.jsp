<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>管理</title>
		<%@ include file="/pages/common/css.jsp"%>
		<%@ include file="/pages/common/js.jsp"%>
		<script type="text/javascript" src="${ctx}/js/src/pager.js"></script>
		<script type="text/javascript" src="${ctx}/js/src/iau.js"></script>
		<script type="text/javascript" src="${ctx}/js/lib/highcharts.js"></script>
	</head>
	<body>
		<div class="rgt-title">
			<p>
				<span>当前位置：</span>数据统计&gt;&gt;安装统计
			</p>
		</div>
		<s:form action="iau.xhtml" method="post" namespace="/manage" name="queryForm" id="queryForm">
			<div class="sj">
				<!-- 搜索 -->
				<table class="searchTable">
					<tr class="searchTr">
						<td class="searchTd">
							渠道(I D)：
							<select name="channelid" id="channelid" style="width: 200px;">
								<option value="">全部渠道</option>
								<c:forEach items="${channelList }" var="obj">
									<option value="${obj.id }" <c:if test="${obj.id eq channelid}">selected="selected"</c:if>> ${obj.name }(${obj.id })</option>
								</c:forEach>
							</select>
						</td>
						<td class="searchTd">
							安装日期：
							<input type="text" id="startdate" name="startdate" value="${startdate }"
									class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" readonly="readonly" />
							~
							<input type="text" id="enddate" name="enddate" value="${enddate }"
									class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startdate\')}',maxDate:'%y-%M-%d'})" readonly="readonly"/>
						</td>
						<td rowspan="2" class="searchTd">
							<input type="button" class="btn" value="查询"/>
						</td>
					</tr>
					<tr style="vertical-align: middle;">
					<td>
						用户(I D)：
						<select name="userid" id="userid" style="width: 200px;">
							<option value="">全部用户</option>
							<c:forEach items="${userList }" var="obj">
								<option value="${obj.id }" <c:if test="${obj.id eq userid}">selected="selected"</c:if>> ${obj.username }(${obj.id })</option>
							</c:forEach>
						</select>
					</td>
					<td>
					</td>
				</tr>
				</table>
			</div>
		</s:form>
		<div class="operator">
			<!-- 全局操作 -->
			<table>
				<tr align="right" style="vertical-align: middle;">
					<td>
						<input type="button" class="btn" value="显示曲线" id="showButton"/>
						<input type="button" class="btn" value="隐藏曲线" id="hideButton" style="display: none;"/>
					</td>
				</tr>
			</table>
			<div id="chartDiv" style="display: none;">
				<table align="center">
					<tr style="vertical-align: middle;">
						<td>
							<select name="userid" id="userid" style="width: 200px;">
								<option value="">全部用户</option>
								<c:forEach items="${userList }" var="obj">
									<option value="${obj.id }" <c:if test="${obj.id eq userid}">selected="selected"</c:if>> ${obj.username }(${obj.id })</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<select name="channelid" id="channelid" style="width: 200px;">
								<option value="">全部渠道</option>
								<c:forEach items="${channelList }" var="obj">
									<option value="${obj.id }" <c:if test="${obj.id eq channelid}">selected="selected"</c:if>> ${obj.name }(${obj.id })</option>
								</c:forEach>
							</select>
						</td>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<s:select list="#{1:'最近24小时',7:'最近一周',14:'最近两周',21:'最近三周',30:'最近30天',31:'最近31天'}" name="datedeff" id="datedeff"
							listKey="key" listValue="value" theme="simple" style="width: 200px;" headerKey=""></s:select>
						</td>
					</tr>
				</table>
				<div id="chartDialog" style="height: 400px; margin: 0 auto" align="center"></div>
			</div>
		</div>
		<!-- 列表 -->
		<div>
			<table class="listTable">
				<thead>
					<tr class="listHeaderTr">
						<th class="resizable" width="80">用户（ID）</th>
						<th class="resizable" width="150">渠道（ID）</th>
						<th class="resizable">安装量</th>
						<th class="resizable">活跃量</th>
						<th class="resizable">卸载量</th>
						<th class="resizable">下载量</th>
						<th class="resizable" colspan="2">xp</th>
						<th class="resizable" colspan="2">win732</th>
						<th class="resizable" colspan="2">win764</th>
						<th class="resizable" colspan="2">其他</th>
						<th colspan="2">无效安装</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pager.pageList}" var="obj">
						<tr class="listTr">
							<td>
								<c:forEach items="${userList }" var="user">
									<c:if test="${obj.userid eq user.id }">${user.username}(${user.id })</c:if>
								</c:forEach>
							</td>
							<td>
								<c:forEach items="${channelList }" var="channel">
									<c:if test="${obj.channelid eq channel.id }">${channel.name}(${channel.id })</c:if>
								</c:forEach>
							</td>
							<td>${obj.installnum}</td>
							<td>${obj.activenum}</td>
							<td>${obj.uninstallnum}</td>
							<td>${obj.driverdown}</td>
							<td>${obj.xpnum}</td>
							<c:if test="${obj.installnum eq 0}">
								<td>0%</td>
							</c:if>
							<c:if test="${obj.installnum ne 0}">
								<td><fmt:formatNumber value="${obj.xpnum/obj.installnum*100}" type="number" maxFractionDigits="3" pattern="#.###"/>%</td>
							</c:if>
							<td>${obj.win732num }</td>
							<c:if test="${obj.installnum eq 0}">
								<td>0%</td>
							</c:if>
							<c:if test="${obj.installnum ne 0}">
								<td><fmt:formatNumber value="${obj.win732num/obj.installnum*100}" type="number" maxFractionDigits="3" pattern="#.###"/>%</td>
							</c:if>
							<td>${obj.win764num}</td>
							<c:if test="${obj.installnum eq 0}">
								<td>0%</td>
							</c:if>
							<c:if test="${obj.installnum ne 0}">
								<td>
									<fmt:formatNumber value="${obj.win764num/obj.installnum*100}" type="number" maxFractionDigits="3" pattern="#.###"/>%
								</td>
							</c:if>
							<td>${obj.win832num+obj.win864num+obj.othernum}</td>
							<c:if test="${obj.installnum eq 0}">
								<td>0%</td>
							</c:if>
							<c:if test="${obj.installnum ne 0}">
								<td>
									<fmt:formatNumber value="${(obj.win832num+obj.win864num+obj.othernum)/obj.installnum*100}" type="number" maxFractionDigits="3" pattern="#.###"/>%
								</td>
							</c:if>
							<td>${obj.vmnum }</td>
							<c:if test="${obj.installnum eq 0}">
								<c:if test="${obj.vmnum eq 0}">
									<td>0%</td>
								</c:if>
								<c:if test="${obj.vmnum ne 0}">
									<td>100%</td>
								</c:if>
							</c:if>
							<c:if test="${obj.installnum ne 0}">
								<td>
									<fmt:formatNumber value="${obj.vmnum/obj.installnum*100}" type="number" maxFractionDigits="3" pattern="#.###"/>%
								</td>
							</c:if>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="9">
							合计&gt;&gt;安装量:${pager.footer.installnum}，活跃量:${pager.footer.activenum}，
							卸载量:${pager.footer.uninstallnum}，下载量:${pager.footer.driverdown}，
							xp安装量:${pager.footer.xpnum}，win732安装量:${pager.footer.win732num}，
							其他安装量:${pager.footer.win764num+pager.footer.win832num+pager.footer.win864num+pager.footer.othernum}，
							无效安装:${pager.footer.vmnum}
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>