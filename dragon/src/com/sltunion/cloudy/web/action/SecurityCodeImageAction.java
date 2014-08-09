package com.sltunion.cloudy.web.action;

import java.io.ByteArrayInputStream;

import com.sltunion.cloudy.common.SecurityCode;
import com.sltunion.cloudy.common.SecurityImage;

public class SecurityCodeImageAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1240328454088931094L;

	// 图片流
	private ByteArrayInputStream imageStream;

	public ByteArrayInputStream getImageStream() {
		return imageStream;
	}

	public void setImageStream(ByteArrayInputStream imageStream) {
		this.imageStream = imageStream;
	}

	public String createVerImage(){
		// 获取默认难度和长度的验证码
		String securityCode = SecurityCode.getSecurityCode();
		imageStream = SecurityImage.getImageAsInputStream(securityCode);
		// 放入session中
		session.setAttribute("SESSION_SECURITY_CODE", securityCode);
		return SUCCESS;
	}

	public String getMessage() {
		return null;
	}

	public void setMessage(String message) {
	}
}
