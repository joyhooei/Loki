package com.sltunion.cloudy.web.action;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.sltunion.cloudy.common.utils.ObjectUtil;
import com.sltunion.cloudy.persistent.model.TUser;

public class BaseAction extends ActionSupport implements Preparable {

	private static final long serialVersionUID = -5703439325179557963L;

	protected static final Logger logger = Logger.getLogger(BaseAction.class);

	protected HttpServletRequest request = ServletActionContext.getRequest();

	protected HttpServletResponse response = ServletActionContext.getResponse();

	protected HttpSession session = ServletActionContext.getRequest().getSession();

	protected ServletContext servletContext = ServletActionContext.getServletContext();

	protected String rootPath = ServletActionContext.getRequest().getContextPath();

	protected String ERROR_CODE_404 = "404";

	protected String FAIL = "fail";

	protected String INPUT = "input";

	protected String BLANK = "";

	protected Map<String, Object> resultMap;

	protected void addUser(TUser user) {
		session.setAttribute("user", user);
		session.setMaxInactiveInterval(86400);
	}

	protected void removeUser() {
		session.removeAttribute("user");
	}

	protected TUser getUser() {
		Object obj = session.getAttribute("user");
		if (obj != null) {
			return (TUser) obj;
		} else {
			return null;
		}
	}

	protected <T> T getRequestParams(Class<T> cls, String field) {
		String value = request.getParameter(field);
		value = ObjectUtil.toBlank(value);
		T t = null;
		try {
			t = ObjectUtil.typeCast(cls, value);
		} catch (Exception e) {
			logger.error("", e);
		}
		return t;
	}

	/**
	 * 页面提交表单转换成为Bean
	 * 
	 * @param request
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public <T> T formToBean(HttpServletRequest request, Class<T> cls) {
		T bean = null;
		try {
			bean = cls.newInstance();
			Field[] fields = cls.getDeclaredFields();
			for (Field field : fields) {
				String fieldName = field.getName();
				String methodName = "set";
				if (Character.isUpperCase(fieldName.charAt(1))) {
					methodName += fieldName;
				} else {
					methodName += fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				}
				Method method = cls.getDeclaredMethod(methodName, field.getType());
				String req_param = request.getParameter(fieldName);
				try {
					method.invoke(bean, ObjectUtil.typeCast(field.getType(), req_param));
				} catch (Exception e) {
					logger.error("", e);
				}
			}
		} catch (InstantiationException e) {
			logger.error("", e);
		} catch (IllegalAccessException e) {
			logger.error("", e);
		} catch (SecurityException e) {
			logger.error("", e);
		} catch (NoSuchMethodException e) {
			logger.error("", e);
		}
		return bean;
	}

	/**
	 * 页面提交表单转换成为Bean
	 * 
	 * @param request
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public <T> T formToBean(BaseAction action, Class<T> cls) {
		T bean = null;
		try {
			bean = cls.newInstance();
			BeanUtils.copyProperties(bean, action);
		} catch (Exception e) {
			logger.error("", e);
		}
		return bean;
	}

	public Map<String, Object> getResultMap() {
		if (resultMap == null) {
			resultMap = new HashMap<String, Object>();
		}
		return resultMap;
	}

	protected String getIPAddr() {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public void addResultMap(String key, Object value) {
		getResultMap().put(key, value);
	}

	public void prepare() throws Exception {

	}
}
