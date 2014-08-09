<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/pages/common/tag.jsp"%>
<div class="left-menu">
	<div class="lft-title">
		<p>
			欢迎您：
			<span>${user.username}</span>
		</p>
	</div>
	<div class="lft-nr">
		<c:forEach items="${user.moduleList }" var="module">
			<c:if test="${ module.pid eq 0}">
				<div class="menu">
					<img src="${ctx}/images/sjicon.jpg" />${module.modulename }
				</div>
				<div class="submenu">
					<ul>
						<c:forEach items="${user.moduleList }" var="cmodule">
							<c:if test="${cmodule.pid eq module.id }">
								<li>
									<a class="menuchild" onclick="openIframe('${ctx}${cmodule.url }')">
										<img src="${ctx }/images/cdicon.jpg" /> ${cmodule.modulename }
									</a>
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</c:if>
		</c:forEach>
	</div>
	<div class="lft-btn">
		<img src="${ctx }/images/left-btn.jpg" />
	</div>
</div>