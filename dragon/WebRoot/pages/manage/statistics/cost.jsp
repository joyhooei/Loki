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
		<script type="text/javascript" src="${ctx}/js/src/cost.js"></script>
	</head>
	<body>
		<div class="rgt-title">
			<p>
				<span>当前位置：</span>数据统计&gt;&gt;支付统计
			</p>
		</div>
		<s:form action="cost.xhtml" method="post" namespace="/manage"
			name="queryForm" id="queryForm">
			<div class="sj">
				<!-- 搜索 -->
				<table class="searchTable">
					<tr class="searchTr">
						<td class="searchTd">
							渠道用户：
							<select id="userid" name="userid" style="width: 200px;">
								<option value="">全部</option>
								<c:forEach items="${userList}" var="u">
									<option value="${u.id }" <c:if test="${userid eq u.id }">selected="selected"</c:if>>${u.username }(${u.id })</option>
								</c:forEach>
							</select>
						</td>
						<td class="searchTd">
							支付日期：
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
					<tr>
						<td class="searchTd">
							支付状态：
							<s:select list="#{'':'全部','2':'未支付','1':'已支付'}" name="status" 
							listKey="key" listValue="value" theme="simple" style="width: 200px;"></s:select>
						</td>
						<td class="searchTd">
<%--							渠道 (ID)：--%>
<%--							<select id="channelid" name="channelid" style="width: 200px;">--%>
<%--								<option value="">全部</option>--%>
<%--								<c:forEach items="${channelList }" var="channel">--%>
<%--									<option value="${channel.id }" <c:if test="${channelid eq channel.id }">selected="selected"</c:if>>${channel.name}(${channel.id })</option>--%>
<%--								</c:forEach>--%>
<%--							</select>--%>
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
						<font color="red">&nbsp;</font>
					</td>
				</tr>
			</table>
		</div>
		<!-- 列表 -->
		<div>
			<table class="listTable">
				<thead>
					<tr class="listHeaderTr">
						<th class="resizable" width="80">用户（ID）</th>
<%--						<th class="resizable" width="150">渠道（ID）</th>--%>
						<th class="resizable">安装日期</th>
						<th class="resizable">安装量</th>
						<th class="resizable">单价（元）</th>
						<th class="resizable">支付费用（元）</th>
						<th class="resizable">支付状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pager.pageList}" var="obj">
						<c:if test="${obj.installnum gt 0}">
							<tr class="listTr">
								<td>
									<c:forEach items="${userList }" var="user">
										<c:if test="${obj.userid eq user.id }">${user.username}(${user.id })</c:if>
									</c:forEach>
								</td>
<%--								<td>--%>
<%--									<c:forEach items="${channelList }" var="channel">--%>
<%--										<c:if test="${obj.channelid eq channel.id }">${channel.name}(${channel.id })</c:if>--%>
<%--									</c:forEach>--%>
<%--								</td>--%>
								<td>${obj.createdate}</td>
								<td>${obj.installnum}</td>
								<td>${obj.costprice}</td>
								<td>
									<fmt:formatNumber value="${obj.totalcost}" type="number" maxFractionDigits="3" pattern="#.###"/>
								</td>
								<td>
									<c:if test="${obj.status ne 1}"><font color="green">未支付</font></c:if>
									<c:if test="${obj.status eq 1}"><font color="red">已支付</font></c:if>
								</td>
								<td>
									<c:if test="${obj.status ne 1}"><a onclick="playCost(${obj.id},${obj.channelid},'${obj.createdate}')">支付</a></c:if>
								</td>
							</tr>
						</c:if>
					</c:forEach>
					<tr>
						<td colspan="5">
							合计&gt;&gt;安装量:${pager.footer.installnum}，
							支付费用:<fmt:formatNumber value="${pager.footer.totalcost}" type="number" maxFractionDigits="3" pattern="#.###"/>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>