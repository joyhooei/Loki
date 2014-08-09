<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/tag.jsp"%>
<div class="nav-menu">
	<div class="nav-menuleft">
		<div class="time" id="timer"><script type="text/javascript" src="${ctx}/js/src/timer.js"></script></div>
		<div class="nav-menumin">
			<img src="${ctx}/images/menubg3.png" />
		</div>
	</div>
	<div class="nav-menuright"><p><a onclick="updatepassword()">[修改密码]</a>&nbsp;&nbsp;<a href="${ctx }/loginout.xhtml" onclick="if (confirm('确定要退出吗？')) return true; else return false;">[退出]</a></p></div>
</div>
<div id="passwordDialog" style="display: none;">
	<s:form action="" method="post" namespace="/manage" name="passwordForm" id="passwordForm">
		<table class="editTable">
			<tbody>
				<tr class="editTr">
					<td class="editLeftTd">
						<font color="red">*</font> 旧&nbsp;&nbsp;密&nbsp;&nbsp;码：
					</td>
					<td class="editRightTd">
						<input type="text" id="password" name="password" value=""
							style="width: 200px;" />
					</td>
				</tr>
				<tr class="editTr">
					<td class="editLeftTd">
						<font color="red">*</font> 新&nbsp;&nbsp;密&nbsp;&nbsp;码：
					</td>
					<td class="editRightTd">
						<input type="password" id="newpassword" name="newpassword" value=""
							style="width: 200px;" />
					</td>
				</tr>
				<tr class="editTr">
					<td class="editLeftTd">
						<font color="red">*</font> 确认密码：
					</td>
					<td class="editRightTd">
						<input type="password" id="confirmpassword" name="confirmpassword" value=""
							style="width: 200px;" />
					</td>
				</tr>
			</tbody>
		</table>
	</s:form>
</div>