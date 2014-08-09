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
		<script type="text/javascript" src="${ctx}/js/src/iauf.js"></script>
		<script type="text/javascript" src="${ctx}/js/lib/highcharts.js"></script>
	</head>
	<body>
		<div class="rgt-title">
			<p>
				<span>当前位置：</span>数据统计&gt;&gt;安装统计
			</p>
		</div>
		<s:form action="iau.xhtml" method="post" namespace="/crm"
			name="queryForm" id="queryForm">
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
						<td rowspan="1" class="searchTd">
							<input type="button" class="btn" value="查询"/>
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
							<select name="channelid" id="channelid" style="width: 200px;">
								<option value="">全部渠道</option>
								<c:forEach items="${channelList }" var="obj">
									<option value="${obj.id }" <c:if test="${obj.id eq channelid}">selected="selected"</c:if>> ${obj.name }(${obj.id })</option>
								</c:forEach>
							</select>
						</td>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<s:select list="#{7:'最近一周',14:'最近两周',21:'最近三周',30:'最近30天',31:'最近31天'}" name="datedeff" id="datedeff"
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
						<th class="resizable">渠道（ID）</th>
						<th>安装量</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pager.pageList}" var="obj">
						<c:if test="${obj.installnum gt 0}">
							<tr class="listTr">
								<td>
									<c:forEach items="${channelList }" var="channel">
										<c:if test="${obj.channelid eq channel.id }">${channel.name}(${channel.id })</c:if>
									</c:forEach>
								</td>
								<td>${obj.installnum}</td>
							</tr>
						</c:if>
					</c:forEach>
					<tr>
						<td colspan="2">
							合计&gt;&gt;安装量:${pager.footer.installnum}
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>