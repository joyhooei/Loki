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
		<script type="text/javascript" src="${ctx}/js/src/module.js"></script>
	</head>
	<body>
		<div class="rgt-title">
			<p>
				<span>当前位置：</span>系统管理&gt;&gt;模块管理
			</p>
		</div>
		<s:form action="module.xhtml" method="post" namespace="/manage"
			name="queryForm" id="queryForm">
			<div class="sj">
				<!-- 搜索 -->
				<table class="searchTable">
				</table>
			</div>
		</s:form>
		<div class="operator">
			<!-- 全局操作 -->
			<table>
				<tr align="right" style="vertical-align: middle;">
					<td>
						<input type="button" class="btn" value="新增模块" id="addButton"/>
					</td>
				</tr>
			</table>
		</div>
		<table class="module-left">
			<tr>
				<td>
					<c:forEach items="${pager.pageList }" var="module">
						<c:if test="${ module.pid eq 0}">
							<div class="module-menu">
								<img src="${ctx}/images/sjicon.jpg" />${module.modulename }
								&nbsp;&nbsp;
								<a onclick="editClick('${module}')" class="editable">编辑</a>&nbsp;&nbsp;
								<a onclick="deleteClick(${module.id},'${module.modulename}')" class="editable">删除</a>
							</div>
							<c:forEach items="${pager.pageList }" var="cmodule">
								<c:if test="${cmodule.pid eq module.id }">
									<div class="module-submenu">
										<img src="${ctx }/images/cdicon.jpg" /> ${cmodule.modulename }
										&nbsp;&nbsp;
										<a onclick="editClick('${cmodule}')" class="editable">编辑</a>&nbsp;&nbsp;
										<a onclick="deleteClick(${cmodule.id},'${cmodule.modulename}')" class="editable">删除</a>
									</div>
								</c:if>
							</c:forEach>
						</c:if>
					</c:forEach>
				</td>
			</tr>
		</table>
		
		<div id="editDialog" style="display: none;">
			<s:form action="" method="post" namespace="/manage" name="editForm" id="editForm">
				<input type="hidden" id="id" name="id" value="" />
				<table class="editTable">
					<tbody>
						<tr class="editTr">
							<td class="editLeftTd">
								<font color="red">*</font> 模块名称：
							</td>
							<td class="editRightTd">
								<input type="text" id="modulename" name="modulename" value=""
									style="width: 200px;" />
							</td>
						</tr>
						<tr class="editTr">
							<td class="editLeftTd">
								<font color="red">*</font> 上级模块：
							</td>
							<td class="editRightTd">
								<select id="pid" name="pid" style="width: 200px;">
									<option value="0" selected="selected">顶级模块</option>
									<c:forEach items="${moduleList}" var="obj">
										<option value="${obj.id }">${obj.modulename }(${obj.id })</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr class="editTr">
							<td class="editLeftTd">
								<font color="red">*</font> 模块代码：
							</td>
							<td class="editRightTd">
								<input type="text" id="modulecode" name="modulecode" value=""
									style="width: 200px;" />
							</td>
						</tr>
						<tr class="editTr">
							<td class="editLeftTd">
								<font color="red">*</font> 模块级别：
							</td>
							<td class="editRightTd">
								<select id="moduleleave" name="moduleleave" style="width: 200px;">
									<option value="1" selected="selected">菜单</option>
									<option value="2">页面</option>
									<option value="3">数据</option>
								</select>
							</td>
						</tr>
						<tr class="editTr">
							<td class="editLeftTd">
								<font color="red">*</font> 模块类型：
							</td>
							<td class="editRightTd">
								<select id="manage" name="manage" style="width: 200px;">
									<option value="1" selected="selected">后台管理</option>
									<option value="0">前台用户</option>
								</select>
							</td>
						</tr>
						<tr class="editTr">
							<td class="editLeftTd">
								 模块地址：
							</td>
							<td class="editRightTd">
								<input type="text" id="url" name="url" value=""
									style="width: 200px;" />
							</td>
						</tr>
						<tr class="editTr">
							<td class="editLeftTd">
								<font color="red">*</font> 模块顺序：
							</td>
							<td class="editRightTd">
								<input type="text" id="sortindex" name="sortindex" value=""
									style="width: 200px;" />
							</td>
						</tr>
					</tbody>
				</table>
			</s:form>
		</div>
	</body>
</html>