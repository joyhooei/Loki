package com.sltunion.cloudy.web.action;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import com.sltunion.cloudy.common.utils.PropertiesUtil;

/**
 * 
 * @author sundial
 * 
 */
public class UploadAction extends BaseAction {
	/**
     * 
     */
	private static final long serialVersionUID = 943185032842685372L;

	private File[] upload; // 实际上传文件

	private String[] uploadContentType; // 文件的内容类型

	private String[] uploadFileName; // 上传文件名

	private String[] fileName; // 自定义文件名

	private String[] targetObj; // 目的记录

	public String upload() {
		try {

			String projectPath = "";
			String targetid = "";
			PropertiesUtil bundle = new PropertiesUtil();
			String smsphonefile = "";
			String uploadName = "";

			String uploadPath = bundle.getString("temp_folder");// 获取临时路径
			String targetDirectory = bundle.getString("sms_phone_folder");
			File targetDir = new File(targetDirectory);
			if (!targetDir.exists()) {
				targetDir.mkdirs();
			}

			for (int i = 0; i < upload.length; i++) {
				if (targetObj != null) {
					targetid = targetObj[i];
				}
				uploadName = uploadFileName[i];// 上传的文件名
				// String type = uploadContentType[i];// 文件类型
				String realName = "_" + System.currentTimeMillis() + getExt(uploadName);// 保存的文件名称
				smsphonefile = targetDirectory + realName;
				File target = new File(targetDirectory, realName);
				FileUtils.copyFile(upload[i], target);// 上传至服务器的目录，一般都这样操作，
				projectPath = uploadPath + "/" + realName;
			}
		} catch (Exception e) {
			e.printStackTrace();
			addResultMap("message", "短信发送失败！请联系管理员。");
		}
		return SUCCESS;
	}

	public void addActionError(String anErrorMessage) {
		// 这里要先判断一下，是我们要替换的错误，才处理
		if (anErrorMessage.startsWith("the request was rejected because its size")) {
			// 这些只是将原信息中的文件大小提取出来。
			Matcher m = Pattern.compile("\\d+").matcher(anErrorMessage);
			String s1 = "";
			if (m.find())
				s1 = m.group();
			String s2 = "";
			if (m.find())
				s2 = m.group();
			// 偷梁换柱，将信息替换掉
			super.addActionError("你上传的文件（" + s1 + "）超过允许的大小（" + s2 + "）");
		} else {// 不是则不管它
			super.addActionError(anErrorMessage);
		}
	}

	public void addFieldError(String fieldName, String errorMessage) {
		if (errorMessage != null && errorMessage.startsWith("File too large:")) {
			errorMessage = "您上传的文件超过允许的大小";
			addResultMap("message", errorMessage);
		}
		if (errorMessage != null && errorMessage.startsWith("Content-Type not allowed:")) {
			errorMessage = "您上传的文件类型不合法";
			addResultMap("message", errorMessage);
		}
		super.addFieldError(fieldName, errorMessage);
	}

	private static String getExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public String[] getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * @return the fileName
	 */
	public String[] getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String[] fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the targetObj
	 */
	public String[] getTargetObj() {
		return targetObj;
	}

	/**
	 * @param targetObj
	 *            the targetObj to set
	 */
	public void setTargetObj(String[] targetObj) {
		this.targetObj = targetObj;
	}
}
