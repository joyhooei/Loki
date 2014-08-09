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
		<script type="text/javascript" src="${ctx}/js/src/role.js"></script>
	</head>
	<body>
		<div class="rgt-title">
			<p>
				<span>当前位置：</span>系统管理&gt;&gt;用户管理
			</p>
		</div>
		<s:form action="role.xhtml" method="post" namespace="/manage"
			name="queryForm" id="queryForm">
			<div class="sj">
				<!-- 搜索 -->
				<table class="searchTable">
					<tr class="searchTr">
						<td class="searchTd">
							角色名称：
							<input type="text" name="rolename" id="rolename"
								style="width: 200px;" value="${rolename}" />
						</td>
						<td class="searchTd">
							角色类型：
							<s:select list="#{'':'全部','1':'后台角色','0':'前台角色'}" name="manage" 
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
						<input type="button" class="btn" value="新增角色" id="addButton"/>
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
						<th class="resizable">角色名（ID）</th>
						<th class="resizable">角色类型</th>
						<th class="resizable">描述</th>
						<th >操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pager.pageList}" var="obj">
						<tr class="listTr">
							<td><input type="checkbox" name="ids" value="${obj.id}"/></td>
							<td title="${obj.rolename}">
								${obj.rolename}(${obj.id})
							</td>
							<td>
								<c:if test="${obj.manage eq 1}">后台角色</c:if>
								<c:if test="${obj.manage eq 0}">前台角色</c:if>
							</td>
							<td title="${obj.remark}">
								${obj.remark }
							</td>
							<td>
								<a onclick="editClick('${obj}')">编辑</a>&nbsp;&nbsp;
								<a onclick="deleteClick(${obj.id},'${obj.rolename}')">删除</a>
								&nbsp;&nbsp;
								<a onclick="assignClick(${obj.id},'${obj.rolemoduleList}')">分配权限</a>
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
								<font color="red">*</font> 角色名称：
							</td>
							<td class="editRightTd">
								<input type="text" id="rolename" name="rolename" value=""
									style="width: 200px;" />
							</td>
						</tr>
						<tr class="editTr">
							<td class="editLeftTd">
								<font color="red">*</font> 角色类型：
							</td>
							<td class="editRightTd">
								<select id="manage" name="manage" style="width: 200px;">
									<option value="1" selected="selected">后台角色</option>
									<option value="0">前台角色</option>
								</select>
							</td>
						</tr>
						<tr class="editTr">
							<td class="editLeftTd">
								描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述：
							</td>
							<td class="editRightTd">
								<textarea id="remark" name="remark" style="width: 400px;height: 200px;"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</s:form>
		</div>
		<div id="assignDialog" style="display: none;">
			<input type="hidden" id="roleid" name="roleid" value="" />
			<s:form action="" method="post" namespace="" name="assignForm" id="assignForm">
				<table class="editTable">
					<tbody>
						<tr class="editTr">
							<td class="editLeftTd">
								<c:forEach items="${moduleList }" var="module">
									<c:if test="${ module.pid eq 0}">
										<div class="select-menu">
											<input type="checkbox" name="moduleid" value="${module.id}"/>${module.modulename }<br/>
											<c:forEach items="${moduleList }" var="cmodule">
												<c:if test="${cmodule.pid eq module.id }">
													<div class="select-submenu">
														<input type="checkbox" name="moduleid" value="${cmodule.id}"/>${cmodule.modulename }
													</div>
												</c:if>
											</c:forEach>
										</div>
									</c:if>
								</c:forEach>
							</td>
						</tr>
					</tbody>
				</table>
			</s:form>
		</div>
	</body>
</html>