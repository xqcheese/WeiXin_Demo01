package com.xq.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.xq.po.TextMessage;

public class MessageUtil {
	
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_SHORTVIDEO = "shortvideo";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";
	
	
	/**
	 * xml转为map集合
	 * @param request
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static Map<String, String> XmlToMap(HttpServletRequest request) throws DocumentException, IOException{
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		
		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);
		
		Element root = doc.getRootElement();
		
		List<Element> list = root.elements();
		
		for(Element e:list){
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;
	}
	
	/**
	 * 将文本消息对象转为xml
	 * @param textMessage
	 * @return
	 */
	public static String TextMessageToXml(TextMessage textMessage) {
		XStream xstream = new XStream();
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
		
	}
	
	public static String InitText(String toUserName,String fromUserName,String content){
		TextMessage text = new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(MessageUtil.MESSAGE_TEXT);
		text.setCreateTime(new Date().getTime());
		text.setContent(content);
		return TextMessageToXml(text);
	}
	
	
	/**
	 * 主菜单
	 * @return
	 */
	public static String MenuText() {
		StringBuffer sb = new StringBuffer();
		sb.append("欢迎您的关注，以下为本号的食用方法：\n\n");
		sb.append("如果您想购买拌菜，请点击'吃拌菜';\n");
		sb.append("如果想要恢复元气，请点击'毒奶'，您可以选择给自己奶首歌、奶句话或者奶篇文章;\n");
		sb.append("如果您想看看我的个人网站，内容包括html和css的基础、java的知识点，请点击'学习去'，还能顺便看看我的简笔画;\n");
		sb.append("回复？调出此菜单；\n\n");
		sb.append("来说一句 早安 或者晚安\n");
		return sb.toString();
	}
	
	public static String GoodNight() {
		StringBuffer sb = new StringBuffer();
		sb.append("晚安，好梦");
		return sb.toString();
	}
	
	public static String GoodMorning() {
		StringBuffer sb = new StringBuffer();
		sb.append("早啊，记得给自己一个拥抱");
		return sb.toString();
	}
}


