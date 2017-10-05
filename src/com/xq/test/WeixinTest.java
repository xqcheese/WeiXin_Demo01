package com.xq.test;

import java.io.IOException;

import net.sf.json.JSONObject;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import com.xq.po.AccessToken;
import com.xq.util.WeixinUtil;

public class WeixinTest {
	public static void main(String args[]){
		try {
			AccessToken token = WeixinUtil.getAccessToken();
		    System.out.println("Ʊ�ݣ�"+token.getToken());
		    System.out.println("��Чʱ�䣺"+token.getExpiresIn());
		
		    String menu = JSONObject.fromObject(WeixinUtil.initMenu()).toString();
		    int result = WeixinUtil.createMenu(token.getToken(), menu);
		    if(result == 0){
			    System.out.println("�����ɹ�");
			}else{
			    System.out.println("������"+result);
		    }
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
