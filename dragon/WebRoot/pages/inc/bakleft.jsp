<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/pages/common/taglibs.jsp"%>
<script type="text/javascript">
    function expand(el)
    {
        childObj = document.getElementById("child" + el);

        if (childObj.style.display == 'none')
        {
            childObj.style.display = 'block';
        }
        else
        {
            childObj.style.display = 'none';
        }
        return;
    }
</script>
<div>
	<table height="100%" cellspacing="0" cellpadding="0" width="170"
		background="${ctx}/images/menu_bg.jpg" border="0">
		<tr>
			<td valign="top" align="center">
				<table cellspacing="0" cellpadding="0" width="100%" border="0">
					<tr>
						<td height="10"></td>
					</tr>
				</table>
				<sec:authorize url="/manage/userList.xhtml">
					<table cellspacing="0" cellpadding="0" width="150" border="0">
						<tr height="22">
							<td style="padding-left: 30px"
								background="${ctx}/images/menu_bt.jpg" align="left"><a
								class="menuparent" href="${ctx}/manage/userList.xhtml">用户管理</a></td>
						</tr>
						<tr height="4">
							<td></td>
						</tr>
					</table>
				</sec:authorize> 
				<sec:authorize url="/manage/prepaidlist.xhtml">
					<table cellspacing="0" cellpadding="0" width="150" border="0">
						<tr height="22">
							<td style="padding-left: 30px"
								background="${ctx}/images/menu_bt.jpg" align="left"><a
								class="menuparent" href="${ctx}/manage/prepaidlist.xhtml">充值管理</a>
							</td>
						</tr>
						<tr height="4">
							<td></td>
						</tr>
					</table>
				</sec:authorize>
				<sec:authorize ifAnyGranted="tdlb,tdqh,super">
					<table cellspacing="0" cellpadding="0" width="150" border="0">
						<tr height="22">
							<td style="padding-left: 30px"
								background="${ctx}/images/menu_bt.jpg" align="left"><a
								class="menuparent" href="javascript:void(0);">通道管理</a></td>
						</tr>
						<tr height="4">
							<td></td>
						</tr>
					</table> 
				</sec:authorize>
				<table id="child1" cellspacing="0" cellpadding="0" width="150"
					border="0">
					<sec:authorize url="/manage/channelList.xhtml">
						<tr height="20">
							<td align="center" width="30"><img height="9"
								src="${ctx}/images/menu_icon.gif" width="9"></td>
							<td align="left"><a class="menuchild"
								href="${ctx}/manage/channelList.xhtml">通道列表</a></td>
						</tr>
					</sec:authorize>
					<sec:authorize url="/manage/changeChannel.xhtml">
						<tr height="20">
							<td align="center" width="30"><img height="9"
								src="${ctx}/images/menu_icon.gif" width="9"></td>
							<td align="left"><a class="menuchild"
								href="${ctx}/manage/changeChannel.xhtml">通道切换</a></td>
						</tr>
					</sec:authorize>
					<tr height="4">
						<td colspan="2"></td>
					</tr>
				</table>
				<sec:authorize url="/manage/smstest.xhtml">
					<table cellspacing="0" cellpadding="0" width="150" border="0">
						<tr height="22">
							<td style="padding-left: 30px"
								background="${ctx}/images/menu_bt.jpg" align="left"><a
								class="menuparent" href="${ctx}/manage/smstest.xhtml">短信测试</a></td>
						</tr>
						<tr height="4">
							<td></td>
						</tr>
					</table>
				</sec:authorize>
				 <sec:authorize url="/manage/smsauditlist.xhtml">
					<table cellspacing="0" cellpadding="0" width="150" border="0">
						<tr height="22">
							<td style="padding-left: 30px"
								background="${ctx}/images/menu_bt.jpg" align="left"><a
								class="menuparent" href="${ctx}/manage/smsauditlist.xhtml">下行审核</a>
							</td>
						</tr>
						<tr height="4">
							<td></td>
						</tr>
					</table>
				</sec:authorize>
				 <sec:authorize url="/manage/smssendlist.xhtml">
					<table cellspacing="0" cellpadding="0" width="150" border="0">
						<tr height="22">
							<td style="padding-left: 30px"
								background="${ctx}/images/menu_bt.jpg" align="left"><a
								class="menuparent" href="${ctx}/manage/smssendlist.xhtml">待发记录</a>
							</td>
						</tr>
						<tr height="4">
							<td></td>
						</tr>
					</table>
				</sec:authorize>
				<sec:authorize url="/manage/smsmtlist.xhtml">
					<table cellspacing="0" cellpadding="0" width="150" border="0">
						<tr height="22">
							<td style="padding-left: 30px"
								background="${ctx}/images/menu_bt.jpg" align="left"><a
								class="menuparent" href="${ctx}/manage/smsmtlist.xhtml">下行记录</a></td>
						</tr>
						<tr height="4">
							<td></td>
						</tr>
					</table>
				</sec:authorize>
				<sec:authorize url="/manage/smsMo!index.xhtml">
					<table cellspacing="0" cellpadding="0" width="150" border="0">
						<tr height="22">
							<td style="padding-left: 30px"
								background="${ctx}/images/menu_bt.jpg" align="left"><a
								class="menuparent" href="${ctx}/manage/smsMo!index.xhtml">上行记录</a>
							</td>
						</tr>
						<tr height="4">
							<td></td>
						</tr>
					</table>
				</sec:authorize>
				<sec:authorize url="/manage/smskoulist.xhtml">
					<table cellspacing="0" cellpadding="0" width="150" border="0">
						<tr height="22">
							<td style="padding-left: 30px"
								background="${ctx}/images/menu_bt.jpg" align="left"><a
								class="menuparent" href="${ctx}/manage/smskoulist.xhtml">短信补发</a></td>
						</tr>
						<tr height="4">
							<td></td>
						</tr>
					</table>
				</sec:authorize>
                <sec:authorize url="/manage/bwList!index.xhtml">
					<table cellspacing="0" cellpadding="0" width="150" border="0">
						<tr height="22">
							<td style="padding-left: 30px"
								background="${ctx}/images/menu_bt.jpg" align="left"><a
								class="menuparent" href="${ctx}/manage/bwList!index.xhtml">黑白名单</a>
							</td>
						</tr>
						<tr height="4">
							<td></td>
						</tr>
					</table>
				</sec:authorize>
				<sec:authorize url="/manage/keywordlist.xhtml">
					<table cellspacing="0" cellpadding="0" width="150" border="0">
						<tr height="22">
							<td style="padding-left: 30px"
								background="${ctx}/images/menu_bt.jpg" align="left"><a
								class="menuparent" href="${ctx}/manage/keywordlist.xhtml">关键字</a></td>
						</tr>
						<tr height="4">
							<td></td>
						</tr>
					</table>
				</sec:authorize>
				<sec:authorize ifAnyGranted="wdkf,rfspm,yfspm,super">
					<table cellspacing="0" cellpadding="0" width="150" border="0">
						<tr height="20">
							<td style="padding-left: 30px"
								background="${ctx}/images/menu_bt.jpg" align="left">
								<a class="menuparent"
								href="javascript:void(0)">客户统计</a></td>
						</tr>
						<tr height="4">
							<td></td>
						</tr>
					</table>
				</sec:authorize>
				<table id="child2" cellspacing="0" cellpadding="0" width="150"
					border="0">
					<sec:authorize url="/manage/customerStatis!customer.xhtml">
						<tr height="20">
							<td align="center" width="30"><img height="9"
								src="${ctx}/images/menu_icon.gif" width="9"></td>
							<td align="left"><a class="menuchild"
								href="${ctx}/manage/customerStatis!customer.xhtml">我的客户</a></td>
						</tr>
					</sec:authorize>
					<sec:authorize url="/manage/customerStatis!customerDaily.xhtml">
						<tr height="20">
							<td align="center" width="30"><img height="9"
								src="${ctx}/images/menu_icon.gif" width="9"></td>
							<td align="left"><a class="menuchild"
								href="${ctx}/manage/customerStatis!customerDaily.xhtml">日发送排名</a></td>
						</tr>
					</sec:authorize>
					<sec:authorize url="/manage/customerStatis!customerMonth.xhtml">
						<tr height="20">
							<td align="center" width="30"><img height="9"
								src="${ctx}/images/menu_icon.gif" width="9"></td>
							<td align="left"><a class="menuchild"
								href="${ctx}/manage/customerStatis!customerMonth.xhtml">月发送排名</a></td>
						</tr>
					</sec:authorize>
					<tr height="4">
						<td colspan="2"></td>
					</tr>
				</table>
				<sec:authorize url="/manage/incomeStatis!income.xhtml">
					<table cellspacing="0" cellpadding="0" width="150" border="0">
						<tr height="20">
							<td style="padding-left: 30px"
								background="${ctx}/images/menu_bt.jpg" align="left">
								<a class="menuparent"
								href="${ctx}/manage/incomeStatis!income.xhtml">收入统计</a></td>
						</tr>
						<tr height="4">
							<td></td>
						</tr>
					</table>
				</sec:authorize>
				<sec:authorize ifAnyGranted="rtj,ytj,super">
					<table cellspacing="0" cellpadding="0" width="150" border="0">
						<tr height="20">
							<td style="padding-left: 30px"
								background="${ctx}/images/menu_bt.jpg" align="left">
								<a class="menuparent"
								href="javascript:void(0)">通道统计</a></td>
						</tr>
						<tr height="4">
							<td></td>
						</tr>
					</table>
				</sec:authorize> 
				<table id="child3" cellspacing="0" cellpadding="0" width="150"
					border="0">
					<sec:authorize url="/manage/channelStatis!channelDaily.xhtml">
						<tr height="20">
							<td align="center" width="30"><img height="9"
								src="${ctx}/images/menu_icon.gif" width="9"></td>
							<td align="left"><a class="menuchild"
								href="${ctx}/manage/channelStatis!channelDaily.xhtml">日统计</a></td>
						</tr>
					</sec:authorize>
					<sec:authorize url="/manage/channelStatis!channelMonth.xhtml">
						<tr height="20">
							<td align="center" width="30"><img height="9"
								src="${ctx}/images/menu_icon.gif" width="9"></td>
							<td align="left"><a class="menuchild"
								href="${ctx}/manage/channelStatis!channelMonth.xhtml">月统计</a></td>
						</tr>
					</sec:authorize>
					<%--
					<sec:authorize url="/manage/channelStatis!channelProvince.xhtml">
						<tr height="20">
							<td align="center" width="30"><img height="9"
								src="${ctx}/images/menu_icon.gif" width="9"></td>
							<td align="left"><a class="menuchild"
								href="${ctx}/manage/channelStatis!channelProvince.xhtml">省份统计</a></td>
						</tr>
					</sec:authorize>
					--%>
					<tr height="4">
						<td colspan="2"></td>
					</tr>
				</table> 
				<sec:authorize url="/manage/systemNotice!index.xhtml">
					<table cellspacing="0" cellpadding="0" width="150" border="0">
						<tr height="22">
							<td style="padding-left: 30px"
								background="${ctx}/images/menu_bt.jpg" align="left"><a
								class="menuparent" href="${ctx}/manage/systemNotice!index.xhtml">系统公告</a>
							</td>
						</tr>
						<tr height="4">
							<td></td>
						</tr>
					</table>
				</sec:authorize>
				<sec:authorize ifAnyGranted="glylb,jslb,qxlb,super"> 
					<table cellspacing="0" cellpadding="0" width="150" border="0">
						<tr height="22">
							<td style="padding-left: 30px"
								background="${ctx}/images/menu_bt.jpg" align="left"><a
								class="menuparent" href="javascript:void(0)">权限管理</a></td>
						</tr>
						<tr height="4">
							<td></td>
						</tr>
					</table>
				</sec:authorize>
				<table id="child3" cellspacing="0" cellpadding="0" width="150"
					border="0">
					<sec:authorize url="/manage/managerList.xhtml">
						<tr height="20">
							<td align="center" width="30"><img height="9"
								src="${ctx}/images/menu_icon.gif" width="9"></td>
							<td align="left"><a class="menuchild"
								href="${ctx}/manage/managerList.xhtml">用户管理</a></td>
						</tr>
					</sec:authorize>
					<sec:authorize url="/manage/roleList.xhtml">
						<tr height="20">
							<td align="center" width="30"><img height="9"
								src="${ctx}/images/menu_icon.gif" width="9"></td>
							<td align="left"><a class="menuchild"
								href="${ctx}/manage/roleList.xhtml">角色管理</a></td>
						</tr>
					</sec:authorize>
					<sec:authorize url="/manage/authList.xhtml">
						<tr height="20">
							<td align="center" width="30"><img height="9"
								src="${ctx}/images/menu_icon.gif" width="9"></td>
							<td align="left"><a class="menuchild"
								href="${ctx}/manage/authList.xhtml">模块管理</a></td>
						</tr>
					</sec:authorize>
					<tr height="4">
						<td colspan="2"></td>
					</tr>
				</table>
			</td>
			<td width="1" bgcolor="#d1e6f7"></td>
		</tr>
	</table>
</div>