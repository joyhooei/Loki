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
		<script type="text/javascript" src="${ctx}/js/src/user.js"></script>
	</head>
	<body>
		<div class="rgt-title">
			<p>
				<span>当前位置：</span>系统管理&gt;&gt;用户管理
			</p>
		</div>
		<s:form action="user.xhtml" method="post" namespace="/manage"
			name="queryForm" id="queryForm">
			<div class="sj">
				<!-- 搜索 -->
				<table class="searchTable">
					<tr class="searchTr">
						<td class="searchTd">
							用户名称：
							<input type="text" name="username" id="username"
								style="width: 200px;" value="${username}" />
						</td>
						<td class="searchTd">
							用户类型：
							<s:select list="#{'':'全部','1':'管理员','0':'普通用户'}" name="usertype" 
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
						<input type="button" class="btn" value="新增用户" id="addButton"/>
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
						<th class="resizable">用户名（ID）</th>
						<th class="resizable">用户类型</th>
						<th class="resizable">用户角色</th>
						<th class="resizable">状态</th>
						<th class="resizable">创建时间</th>
						<th >操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pager.pageList}" var="obj">
						<tr class="listTr">
							<td><input type="checkbox" name="ids" value="${obj.id}"/></td>
							<td title="${obj.username}">
								${obj.username}(${obj.id})
							</td>
							<td>
								<c:if test="${obj.usertype eq 1}">是</c:if>
								<c:if test="${obj.usertype eq 0}">否</c:if>
							</td>
							<td title="${obj.rolename}">
								${obj.rolename }
							</td>
							<td>
								<c:if test="${obj.enable eq 1}">可用</c:if>
								<c:if test="${obj.enable eq 0}">禁用</c:if> 
							</td>
							<td title="${obj.createtime}">
								${obj.createtime }
							</td>
							<td>
								<c:if test="${obj.username eq user.username }">
									<a onclick="resetPasswordClick(${obj.id},'${obj.username}')">重置密码</a>
								</c:if>
								<c:if test="${(obj.id ne 0) and (obj.username ne user.username)}">
									<a onclick="editClick('${obj}')">编辑</a>&nbsp;&nbsp;
									<a onclick="deleteClick(${obj.id},'${obj.username}')">删除</a>
									&nbsp;&nbsp;
									<c:if test="${obj.enable eq 1}">
										<a onclick="changeEnableClick(${obj.id},${obj.enable},'${obj.username}')">禁用</a>
									</c:if>
									<c:if test="${obj.enable eq 0}">
										<a onclick="changeEnableClick(${obj.id},${obj.enable},'${obj.username}')">启用</a>
									</c:if>
									&nbsp;&nbsp;
									<a onclick="resetPasswordClick(${obj.id},'${obj.username}')">重置密码</a>
								</c:if>
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
								<font color="red">*</font> 用户名称：
							</td>
							<td class="editRightTd">
								<input type="text" id="username" name="username" value=""
									style="width: 200px;" />
							</td>
						</tr>
						<tr class="editTr">
							<td class="editLeftTd">
								<font color="red">*</font> 密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：
							</td>
							<td class="editRightTd">
								<input type="text" id="password" name="password" value=""
									style="width: 200px;" />
							</td>
						</tr>
						<tr class="editTr">
							<td class="editLeftTd">
								<font color="red">*</font> 用户角色：
							</td>
							<td class="editRightTd">
								<select id="roleid" name="roleid" style="width: 200px;">
									<option value="">请选择用户</option>
									<c:forEach items="${roleList}" var="role">
										<option value="${role.id }">${role.rolename }(${role.id })</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr class="editTr">
							<td class="editLeftTd">
								<font color="red">*</font> 状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：
							</td>
							<td class="editRightTd">
								<select id="enable" name="enable" style="width: 200px;">
									<option value="1" selected="selected">可用</option>
									<option value="0">禁用</option>
								</select>
							</td>
						</tr>
						<tr class="editTr">
							<td class="editLeftTd">
								<font color="red">*</font> 管&nbsp;&nbsp;理&nbsp;&nbsp;员：
							</td>
							<td class="editRightTd">
								<select id="usertype" name="usertype" style="width: 200px;">
									<option value="0" selected="selected">否</option>
									<option value="1">是</option>
								</select>
							</td>
						</tr>
					</tbody>
				</table>
			</s:form>
		</div>
	</body>
</html>