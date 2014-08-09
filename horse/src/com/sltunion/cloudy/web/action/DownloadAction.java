/**
 * @Copyright:Copyright (c) 2008 - 2100
 * @Company:zdt
 */
package com.sltunion.cloudy.web.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.struts2.ServletActionContext;

/**
 * @Title:
 * @Author:liugang
 * @Since:2013-1-5
 * @Version:1.1.0
 */
public class DownloadAction extends BaseAction {
	/**
     * 
     */
	private static final long serialVersionUID = 6736233067232203260L;

	// 要下载的文件路径，对应于服务器上的文件源
	private String inputfile;

	// 服务器路径类型，1：绝对路径，2：相对路径
	private Integer pathtype;

	// 下载保存文件时的名称
	private String filename;

	/**
	 * 下载用的Action应该返回一个InputStream实例， 该方法对应在result里的inputName属性值为targetFile
	 * 
	 */

	public InputStream getDownloadFile() throws Exception {
		InputStream in = null;
		try {
			System.out.println("filename:" + filename);
			System.out.println("inputfile:" + inputfile);
			if (1 == pathtype) {
				// 处理绝对路径的情况
				in = new FileInputStream(inputfile);
			} else {
				// 处理项目中的相对路径
				in = ServletActionContext.getServletContext().getResourceAsStream(inputfile);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return in;
	}

	public String getInputfile() {
		return inputfile;
	}

	public void setInputfile(String inputfile) {
		this.inputfile = inputfile;
	}

	public Integer getPathtype() {
		return pathtype;
	}

	public void setPathtype(Integer pathtype) {
		this.pathtype = pathtype;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		try {
			// 解决IE与火狐中文乱码问题
			if (request.getHeader("user-agent").indexOf("MSIE") != -1) {
				// IE乱码
				this.filename = java.net.URLEncoder.encode(filename, "utf-8");
			} else {
				// 火狐乱码
				this.filename = new String(filename.getBytes("utf-8"), "iso-8859-1");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public String getMessage() {
		return null;
	}

	public void setMessage(String message) {
	}
}
