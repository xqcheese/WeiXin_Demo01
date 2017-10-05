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
	 * xmlתΪmap����
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
	 * ���ı���Ϣ����תΪxml
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
	 * ���˵�
	 * @return
	 */
	public static String MenuText() {
		StringBuffer sb = new StringBuffer();
		sb.append("��ӭ���Ĺ�ע������Ϊ���ŵ�ʳ�÷�����\n\n");
		sb.append("������빺���ˣ�����'�԰��';\n");
		sb.append("�����Ҫ�ָ�Ԫ��������'����'��������ѡ����Լ����׸衢�̾仰������ƪ����;\n");
		sb.append("������뿴���ҵĸ�����վ�����ݰ���html��css�Ļ�����java��֪ʶ�㣬����'ѧϰȥ'������˳�㿴���ҵļ�ʻ�;\n");
		sb.append("�ظ��������˲˵���\n\n");
		sb.append("��˵һ�� �簲 ������\n");
		return sb.toString();
	}
	
	public static String GoodNight() {
		StringBuffer sb = new StringBuffer();
		sb.append("��������");
		return sb.toString();
	}
	
	public static String GoodMorning() {
		StringBuffer sb = new StringBuffer();
		sb.append("�簡���ǵø��Լ�һ��ӵ��");
		return sb.toString();
	}
}


