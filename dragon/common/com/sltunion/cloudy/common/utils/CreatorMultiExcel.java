package com.sltunion.cloudy.common.utils;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *excel文件导出
 */

public class CreatorMultiExcel {
	Vector<String[]> uservect = null;
	String path = "";
	String fname = "";
	private final static int MAX_SHEET_ROW = 65536;

	/**
	 * 构造函数
	 * @param file true 创建文件,false 不创建文件
	 */
	public CreatorMultiExcel(boolean file) {
		uservect = new Vector<String[]>();
		if (file) {
			try {
				Date now = new Date();
				SimpleDateFormat sfarmat = new SimpleDateFormat(
						"yyyyMMddHHmmss");
				fname = sfarmat.format(now) + ".xls";
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 添加Excel一行记录
	 * @param temp
	 */
	public void addVector(String[] temp) {
		uservect.addElement(temp);
	}

	/**
	 * 设置生成文件目录
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 创建Excel文件
	 */
	public void createfile() {
		try {
			String filename = path + fname;
			int rownum = 0;
			FileOutputStream out = new FileOutputStream(filename);
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFRow r = null;
			HSSFCell c = null;
			int sheet_count = 0;
			int rowcount = uservect.size();
			if (rowcount % MAX_SHEET_ROW == 0)
				sheet_count = rowcount / MAX_SHEET_ROW;
			else
				sheet_count = (int) rowcount / MAX_SHEET_ROW + 1;
			for (int i = 0; i < sheet_count; i++) {
				HSSFSheet s = wb.createSheet();
				wb.setSheetName(i, "sheet " + (i + 1));
				for (rownum = (short) 0; rownum < MAX_SHEET_ROW; rownum++) {
					if ((i * MAX_SHEET_ROW + rownum) >= rowcount)
						break;
					String[] stemp = (String[]) uservect.elementAt(i
							* MAX_SHEET_ROW + rownum);
					r = s.createRow(rownum);
					for (int j = 0; j < stemp.length; j++) {
						c = r.createCell(j);
						c.setCellValue(new HSSFRichTextString(stemp[j]));
					}
				}
			}
			wb.write(out);
			out.close();
			out = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 输出Excel文件字节流
	 * @return
	 */
	public byte[] getData() {
		java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
		java.io.DataOutputStream dos = new java.io.DataOutputStream(bos);
		try {
			int rownum = 0;
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFRow r = null;
			HSSFCell c = null;
			int sheet_count = 0;
			int rowcount = uservect.size();
			if (rowcount % MAX_SHEET_ROW == 0)
				sheet_count = rowcount / MAX_SHEET_ROW;
			else
				sheet_count = (int) rowcount / MAX_SHEET_ROW + 1;
			for (int i = 0; i < sheet_count; i++) {
				HSSFSheet s = wb.createSheet();
				wb.setSheetName(i, "sheet " + (i + 1));
				for (rownum = (short) 0; rownum < MAX_SHEET_ROW; rownum++) {
					if ((i * MAX_SHEET_ROW + rownum) >= rowcount)
						break;
					String[] stemp = (String[]) uservect.elementAt(i
							* MAX_SHEET_ROW + rownum);
					r = s.createRow(rownum);
					for (int j = 0; j < stemp.length; j++) {
						c = r.createCell(j);
						c.setCellValue(new HSSFRichTextString(stemp[j]));
					}
				}
			}
			wb.write(dos);
			dos.flush();
			return bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (dos != null) {
					dos.close();
					dos = null;
				}
				if (bos != null) {
					bos.close();
					bos = null;
				}
			} catch (Exception e) {
			}
		}
		return null;
	}

	/**
	 * 得到Excel文件名
	 * @return
	 */
	public String getFileName() {
		return fname;
	}
}