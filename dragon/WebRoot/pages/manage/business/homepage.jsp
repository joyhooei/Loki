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
		<script type="text/javascript" src="${ctx}/js/src/hpl.js"></script>
	</head>
	<body>
		<div class="rgt-title">
			<p>
				<span>当前位置：</span>业务管理&gt;&gt;主页锁定
			</p>
		</div>
		<s:form action="hpl.xhtml" method="post" namespace="/manage"
			name="queryForm" id="queryForm">
			<div class="sj">
				<!-- 搜索 -->
				<table class="searchTable">
					<tr class="searchTr">
						<td class="searchTd">
							主页状态：
							<s:select list="#{'':'全部','1':'启用','0':'测试','2':'禁用'}" name="status" 
							listKey="key" listValue="value" theme="simple" style="width: 200px;"></s:select>
						</td>
						<td class="searchTd">
							防&nbsp;&nbsp;&nbsp;&nbsp;杀：
							<s:select list="#{'':'全部','1':'是','0':'否'}" name="fkill" 
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
						<input type="button" class="btn" value="新增主页" id="addButton"/>
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
						<th class="resizable">主页地址1</th>
						<th class="resizable">主页地址2</th>
						<th class="resizable">主页地址3</th>
						<th class="resizable">自动开IE</th>
						<th class="resizable">防杀</th>
						<th class="resizable">状态</th>
						<th >操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pager.pageList}" var="obj">
						<tr class="listTr">
							<td><input type="checkbox" name="ids" value="${obj.id}"/></td>
							<td title="${obj.url}">
								${obj.url}
							</td>
							<td title="${obj.url360se}">
								${obj.url360se}
							</td>
							<td title="${obj.url360chrome}">
								${obj.url360chrome}
							</td>
							<td>
								<c:if test="${obj.autoie eq 1}">是</c:if>
								<c:if test="${obj.autoie eq 0}">否</c:if>
							</td>
							<td>
								<c:if test="${obj.fkill eq 1}">是</c:if>
								<c:if test="${obj.fkill eq 0}">否</c:if>
							</td>
							<td>
								<c:if test="${obj.status eq 1}">启用</c:if>
								<c:if test="${obj.status eq 0}">测试</c:if>
								<c:if test="${obj.status eq 2}">禁用</c:if>
							</td>
							<td>
								<a onclick="editClick('${obj}')">编辑</a>&nbsp;&nbsp;
								<a onclick="deleteClick(${obj.id},'${obj.url}')">删除</a>
								&nbsp;&nbsp;
								<a onclick="assignClick(${obj.id},'${obj.homepagechannelList}')">关联渠道</a>
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
								<font color="red">*</font> 主页地址1：
							</td>
							<td class="editRightTd">
								<input type="text" id="url" name="url" value=""
									style="width: 200px;" />
							</td>
						</tr>
						<tr class="editTr">
							<td class="editLeftTd">
								<font color="red">*</font> 主页地址2：
							</td>
							<td class="editRightTd">
								<input type="text" id="url360se" name="url360se" value=""
									style="width: 200px;" />
							</td>
						</tr>
						<tr class="editTr">
							<td class="editLeftTd">
								<font color="red">*</font> 主页地址3：
							</td>
							<td class="editRightTd">
								<input type="text" id="url360chrome" name="url360chrome" value=""
									style="width: 200px;" />
							</td>
						</tr>
						<tr class="editTr">
							<td class="editLeftTd">
								<font color="red">*</font> 延&nbsp;&nbsp;&nbsp;&nbsp;时：
							</td>
							<td class="editRightTd">
								<input type="text" id="delay" name="delay" value="0"
									style="width: 200px;" />小时
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
								<font color="red">*</font> 自动开IE：
							</td>
							<td class="editRightTd">
								<select id="autoie" name="autoie" style="width: 200px;">
									<option value="1" selected="selected">是</option>
									<option value="0">否</option>
								</select>
							</td>
						</tr>
						<tr class="editTr">
							<td class="editLeftTd">
								<font color="red">*</font> 防&nbsp;&nbsp;&nbsp;&nbsp;杀：
							</td>
							<td class="editRightTd">
								<select id="fkill" name="fkill" style="width: 200px;">
									<option value="1" selected="selected">是</option>
									<option value="0">否</option>
								</select>
							</td>
						</tr>
					</tbody>
				</table>
			</s:form>
		</div>
		<div id="assignDialog" style="display: none;">
			<input type="hidden" id="homepageid" name="homepageid" value="" />
			<input type="hidden" id="channelList" value="${channelList }" />
			<s:form action="" method="post" namespace="" name="assignForm" id="assignForm">
				<table class="editTable">
					<tbody>
						<tr class="editTr">
							<td class="editLeftTd">
								<div class="select-menu">
<%--									<c:forEach items="${channelList }" var="channel">--%>
<%--										<div class="select-submenu">--%>
<%--											<input type="checkbox" name="channelid" value="${channel.id}"/>(${channel.id})${channel.name }--%>
<%--										</div>--%>
<%--									</c:forEach>--%>
										<div class="select-submenu">
											<input type="checkbox" name="channelid" value=""/>
										</div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</s:form>
		</div>
	</body>
</html>