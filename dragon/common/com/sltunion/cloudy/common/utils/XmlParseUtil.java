package com.sltunion.cloudy.common.utils;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.log4j.Logger;

/**
 * xml文件同POJO相互转换
 * 
 * @author sundial
 */

public final class XmlParseUtil {
	protected final static Logger logger = Logger.getLogger(XmlParseUtil.class);

	/**
	 * xml字节流串转换成POJO类
	 * 
	 * @param xml字节流
	 * @param clazz
	 * @return Object 返回类型
	 */
	public static Object unMarshal(InputStream xmlStream, Class<?> clazz) {
		Object obj = null;

		try {
			JAXBContext jc = JAXBContext.newInstance(new Class[] { clazz });
			Unmarshaller u = jc.createUnmarshaller();
			obj = u.unmarshal(xmlStream);
		} catch (JAXBException e) {
			logger.error(e);
		}
		return obj;
	}

	/**
	 * XML字符串转换成POJO类
	 * 
	 * @param String
	 *            xmlStr
	 * @param Class
	 *            clazz
	 * @return Object 返回类型
	 */
	public static Object unMarshal(String xmlStr, Class<?> clazz) {
		Object obj = null;

		try {
			JAXBContext jc = JAXBContext.newInstance(new Class[] { clazz });
			Unmarshaller u = jc.createUnmarshaller();
			obj = u.unmarshal(new StreamSource(new StringReader(xmlStr)));
		} catch (JAXBException e) {
			e.printStackTrace();
			logger.error("Can't unmarshal the XML file, error message: ", e);
		}

		return obj;
	}

	/**
	 * POJO转换成XML字符串
	 * 
	 * @param obj
	 * @param clazz
	 * @return
	 */
	public static String marshal(Object obj, Class<?> clazz) {
		String result = null;

		try {
			JAXBContext jc = JAXBContext.newInstance(new Class[] { clazz });

			Marshaller marshaller = jc.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// 编码格式

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式化生成的xml串
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);// 是否省略xml头信息（<?xml

			StringWriter writer = new StringWriter();

			marshaller.marshal(obj, writer);

			result = writer.toString();
		} catch (JAXBException e) {
			logger.error("Can't marshal the XML file, error message: ", e);
		}
		return result;
	}

	/**
	 * xsd文件验证
	 * 
	 * @param xmlStr
	 *            xml字符串
	 * @param xsdFileName
	 *            xsd文件
	 * @return true 满足xsd文件定义，反之不满足
	 */
	public static boolean xmlVsXsd(String xmlStr, String xsdFileName) {
		boolean flag = true;
		try {
			InputStream is = XmlParseUtil.class.getClassLoader().getResourceAsStream(xsdFileName);
			Source schemaFile = new StreamSource(is);
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(schemaFile);
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new StringReader(xmlStr)));
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	public static String getXmlCode(String xmlStr) {
		int start = xmlStr.indexOf("<CODE>") + 6;
		int end = xmlStr.indexOf("</CODE>");
		return xmlStr.substring(start, end);
	}

	public static String getXmlElementValue(String xmlStr, String element) {
		int start = xmlStr.indexOf("<" + element + ">") + element.length() + 2;
		int end = xmlStr.indexOf("</" + element + ">");
		if (start <= end) {
			return "";
		} else {
			return xmlStr.substring(start, end);
		}
	}
}
