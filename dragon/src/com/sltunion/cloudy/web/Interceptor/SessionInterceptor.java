package com.sltunion.cloudy.web.Interceptor;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.sltunion.cloudy.common.utils.ObjectUtil;
import com.sltunion.cloudy.persistent.model.TModule;
import com.sltunion.cloudy.persistent.model.TUser;
public class SessionInterceptor implements Interceptor {

	private static final long serialVersionUID = -7773237323227039638L;
		
	public void destroy() {

	}

	public void init() {

	}
	
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Object obj = session.getAttribute("user");
		String requrl = ServletActionContext.getRequest().getServletPath();
		boolean flag = false;
		if(obj != null){
			if(!requrl.contains("ajax") && !"/index.xhtml".equals(requrl)){
				TUser user = (TUser)obj;
				List<TModule> mondoList = user.getModuleList();
				for (TModule tModule : mondoList) {
					if(ObjectUtil.isNotEmpty(tModule.getUrl()) && tModule.getUrl().equals(requrl)){
						flag = true;
					}
				}
				if(!flag){
					return "accessDenied";
				}
			}
			return invocation.invoke();
		}
		return "timeout";
	}
}
