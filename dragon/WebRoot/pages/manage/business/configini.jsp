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
		<script type="text/javascript" src="${ctx}/js/src/configini.js"></script>
	</head>
	<body>
		<div class="rgt-title">
			<p>
				<span>当前位置：</span>业务管理&gt;&gt;通用配置
			</p>
		</div>
		<s:form action="cfi.xhtml" method="post" namespace="/manage"
			name="queryForm" id="queryForm">
			<div class="sj">
				<!-- 搜索 -->
				<table class="searchTable">
					<tr class="searchTr">
						<td class="searchTd">
							配置名称：
							<input type="text" name="name" id="name"
								style="width: 200px;" value="${name}" />
						</td>
						<td class="searchTd">
							配置状态：
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
			<!-- 全局操作 -->
			<table>
				<tr align="right" style="vertical-align: middle;">
					<td>
						<input type="button" class="btn" value="新增配置" id="addButton"/>
						<input type="button" class="btn" value="刷新此业务接口数据" id="clean"/>
						<input type="button" class="btn" value="刷新所有业务接口数据" id="cleanAll"/>
					</td>
				</tr>
			</table>
		</div>
		<!-- 列表 -->
		<div>
			<table class="listTable">
				<thead>
					<tr class="listHeaderTr">
						<th width="50"><input type="checkbox" name="checkboxs"/></th>
						<th class="resizable">配置名称</th>
						<th class="resizable">配置内容</th>
						<th class="resizable">配置类型</th>
						<th class="resizable">状态</th>
						<th >操作</th>
					</tr>
				</thead>
				<tbody>
					<%request.setAttribute("vEnter", "\r\n");request.setAttribute("vBlank", "");%>
					<c:forEach items="${pager.pageList}" var="obj">
						<tr class="listTr">
							<td><input type="checkbox" name="ids" value="${obj.id}"/></td>
							<td title="${obj.name}">
								${obj.name}
							</td>
							<td title="${fn:replace(obj.content, vEnter,vBlank)}">
								${fn:replace(obj.content, vEnter,vBlank)}
							</td>
							<td>
								<c:if test="${obj.logictype eq 1}">软件推荐</c:if>
								<c:if test="${obj.logictype eq 2}">迷你页</c:if>
								<c:if test="${obj.logictype eq 3}">开关配置</c:if>
							</td>
							<td>
								<c:if test="${obj.status eq 1}">启用</c:if>
								<c:if test="${obj.status eq 0}">测试</c:if>
								<c:if test="${obj.status eq 2}">禁用</c:if>
							</td>
							<td>
								<a onclick="editClick('${fn:replace(obj, vEnter, vBlank)}')">编辑</a>&nbsp;&nbsp;
								<a onclick="deleteClick(${obj.id},'${obj.name}')">删除</a>
								&nbsp;&nbsp;
								<a onclick="assignClick(${obj.id},'${obj.configchannelList}')">分配渠道</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!-- 分页 -->
		<jsp:include page="/pages/common/pageing.jsp" />
		<div id="editDialog" style="display: none;">
			<s:form action="" method="post" namespace="/manage" name="editForm" id="editForm">
				<input type="hidden" id="id" name="id" value="" />
				<table class="editTable">
					<tbody>
						<tr class="editTr">
							<td class="editLeftTd">
								<font color="red">*</font> 配置名称：
							</td>
							<td class="editRightTd">
								<input type="text" id="name" name="name" value=""
									style="width: 200px;" />
							</td>
						</tr>
						<tr class="editTr">
							<td class="editLeftTd">
								<font color="red">*</font> 配置类型：
							</td>
							<td class="editRightTd">
								<select id="logictype" name="logictype" style="width: 200px;">
									<option value="1" selected="selected">软件推荐</option>
									<option value="2">迷你页</option>
									<option value="2">开关配置</option>
								</select>
							</td>
						</tr>
						<tr class="editTr">
							<td class="editLeftTd">
								<font color="red">*</font> 状&nbsp;&nbsp;&nbsp;&nbsp;态：
							</td>
							<td class="editRightTd">
								<select id="status" name="status" style="width: 200px;">
									<option value="1" selected="selected">启用</option>
									<option value="0">测试</option>
									<option value="2">禁用</option>
								</select>
							</td>
						</tr>
						<tr class="editTr">
							<td class="editLeftTd">
								配置内容：
							</td>
							<td class="editRightTd">
								<textarea id="content" name="content" style="width: 400px;height: 200px;"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</s:form>
		</div>
		<div id="assignDialog"  style="display: none;">
			<input type="hidden" id="configid" name="configid" value="" />
			<s:form action="" method="post" namespace="" name="assignForm" id="assignForm">
				<table class="editTable">
					<tbody>
						<tr class="editTr">
							<td class="editLeftTd">
								<div class="select-menu">
									<c:forEach items="${channelList }" var="channel">
										<div class="select-submenu">
											<input type="checkbox" name="channelid" value="${channel.id}"/>(${channel.id})${channel.name }
										</div>
									</c:forEach>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</s:form>
		</div>
	</body>
</html>