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
		<script type="text/javascript" src="${ctx}/js/src/channel.js"></script>
	</head>
	<body>
		<div class="rgt-title">
			<p>
				<span>当前位置：</span>基础数据&gt;&gt;渠道管理
			</p>
		</div>
		<s:form action="channel.xhtml" method="post" namespace="/manage"
			name="queryForm" id="queryForm">
			<div class="sj">
				<!-- 搜索 -->
				<table class="searchTable">
					<tr class="searchTr">
						<td class="searchTd">
							渠道名称：
							<input type="text" name="username" id="username"
								style="width: 200px;" value="${username}" />
						</td>
						<td class="searchTd">
							用&nbsp;&nbsp;&nbsp;&nbsp;户：
							<s:select list="userList" name="userid" 
							listKey="id" listValue="username" theme="simple" style="width: 200px;" headerKey="" headerValue="全部"></s:select>
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
						<input type="button" class="btn" value="新增渠道" id="addButton"/>
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
						<th class="resizable">渠道名称（ID）</th>
						<th class="resizable">用户（ID）</th>
						<th class="resizable">起始值</th>
						<th class="resizable">扣量比</th>
						<th class="resizable">单价（元）</th>
						<th class="resizable">统计状态</th>
						<th class="resizable">创建时间</th>
						<th >操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pager.pageList}" var="obj">
						<tr class="listTr">
							<td><input type="checkbox" name="ids" value="${obj.id}"/></td>
							<td title="${obj.name}">
								${obj.name}(${obj.id})
							</td>
							<td title="${obj.username}">
								${obj.username}(${obj.userid})
							</td>
							<td>
								${obj.startnum}
							</td>
							<td>
								${obj.deduct}%
							</td>
							<td>
								${obj.price}
							</td>
							<td>
								<c:if test="${obj.status eq 0}">测试</c:if>
								<c:if test="${obj.status eq 1}">启用</c:if> 
								<c:if test="${obj.status eq 2}">禁用</c:if>
							</td>
							<td title="${obj.createtime}">
								${obj.createtime }
							</td>
							<td>
								<a onclick="editClick('${obj}')">编辑</a>&nbsp;&nbsp;
								<a onclick="deleteClick(${obj.id},'${obj.name}')">删除</a>
								&nbsp;&nbsp;
								<c:if test="${obj.status eq 1}">
									<a onclick="changeEnableClick(${obj.id},${obj.status},'${obj.name}')">禁用</a>
								</c:if>
								<c:if test="${obj.status eq 2}">
									<a onclick="changeEnableClick(${obj.id},${obj.status},'${obj.name}')">启用</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!-- 分页 -->
		<jsp:include page="/pages/common/pageing.jsp" />
		<div id="editDialog">
			<s:form action="" method="post" namespace="/manage" name="editForm" id="editForm">
				<input type="hidden" id="id" name="id" value="" />
				<table class="editTable">
					<tbody>
						<tr class="editTr">
							<td class="editLeftTd">
								<font color="red">*</font> 名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：
							</td>
							<td class="editRightTd">
								<input type="text" id="name" name="name" value=""
									style="width: 200px;" />
							</td>
						</tr>
						<tr class="editTr">
							<td class="editLeftTd">
								<font color="red">*</font> 用户(I&nbsp;D)：
							</td>
							<td class="editRightTd">
								<select id="userid" name="userid" style="width: 200px;">
									<option value="">请选择用户</option>
									<c:forEach items="${userList}" var="user">
										<option value="${user.id }">${user.username }(${user.id })</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr class="editTr">
							<td class="editLeftTd">
								<font color="red">*</font> 扣量比例：
							</td>
							<td class="editRightTd">
								<input type="text" id="deduct" name="deduct" value="45"
									style="width: 200px;" />%
							</td>
						</tr>
						<tr class="editTr">
							<td class="editLeftTd">
								<font color="red">*</font> 起&nbsp;&nbsp;始&nbsp;&nbsp;值：
							</td>
							<td class="editRightTd">
								<input type="text" id="startnum" name="startnum" value="350"
									style="width: 200px;" />
							</td>
						</tr>
						<tr class="editTr">
							<td class="editLeftTd">
								<font color="red">*</font> 单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：
							</td>
							<td class="editRightTd">
								<input type="text" id="price" name="price" value="0.3"
									style="width: 200px;" />元
							</td>
						</tr>
						<tr class="editTr">
							<td class="editLeftTd">
								<font color="red">*</font> 统计状态：
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
								备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：
							</td>
							<td class="editRightTd">
								<textarea id="remark" name="remark" style="width: 400px;height: 200px;"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</s:form>
		</div>
	</body>
</html>