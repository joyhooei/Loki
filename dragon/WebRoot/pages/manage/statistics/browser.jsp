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
		<script type="text/javascript" src="${ctx}/js/src/browser.js"></script>
	</head>
	<body>
		<div class="rgt-title">
			<p>
				<span>当前位置：</span>数据统计&gt;&gt;终端统计
			</p>
		</div>
		<s:form action="pps.xhtml" method="post" namespace="/manage"
			name="queryForm" id="queryForm">
			<div class="sj">
				<!-- 搜索 -->
				<table class="searchTable">
					<tr class="searchTr">
						<td class="searchTd">
							开始时间：
							<input type="text" name="startdate" id="startdate" style="width: 200px;" value="${startdate}" />
						</td>
						<td class="searchTd">
							结束时间：
							<s:select list="#{'':'全部','1':'启用','0':'测试','2':'禁用'}" name="status" 
							listKey="key" listValue="value" theme="simple" style="width: 200px;"></s:select>
						</td>
						<td rowspan="1" class="searchTd">
							<input type="button" class="btn" value="查询"/>
						</td>
					</tr>
				</table>
			</div>
		</s:form>
		<div class="operator">
			<table align="center">
				<tr style="vertical-align: middle;">
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
			<div id="chartDialog" style="height: 400px; margin: 0 auto"></div>
		</div>
		<!-- 列表 -->
		<div>
			<table class="listTable">
				<thead>
					<tr class="listHeaderTr">
						<th>渠道（ID）</th>
<!-- 						<th class="resizable">用户（ID）</th> -->
						<th class="resizable">加载总量</th>
						<th class="resizable">xp加载量</th>
						<th class="resizable">win732加载量</th>
						<th>其他加载量</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pager.pageList}" var="obj">
						<tr class="listTr">
							<td>
								<c:forEach items="${channelList }" var="channel">
									<c:if test="${obj.channelid eq channel.id }">${channel.name}(${channel.id })</c:if>
								</c:forEach>
							</td>
							<td>${obj.xpnum+obj.win732num+obj.othernum}</td>
							<td>${obj.xpnum}</td>
							<td>${obj.win732num }</td>
							<td>${obj.othernum }</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="5">
							合计&gt;&gt;加载总量:${pager.footer.xpnum +pager.footer.win732num+pager.footer.othernum}，xp加载量:${pager.footer.xpnum}，
							win732加载量:${pager.footer.win732num}，其他加载量:${pager.footer.othernum}
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>