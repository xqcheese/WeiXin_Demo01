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
		    System.out.println("票据："+token.getToken());
		    System.out.println("有效时间："+token.getExpiresIn());
		
		    String menu = JSONObject.fromObject(WeixinUtil.initMenu()).toString();
		    int result = WeixinUtil.createMenu(token.getToken(), menu);
		    if(result == 0){
			    System.out.println("创建成功");
			}else{
			    System.out.println("错误码"+result);
		    }
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
