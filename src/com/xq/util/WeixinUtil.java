package com.xq.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.sun.org.apache.regexp.internal.recompile;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import com.xq.menu.Button;
import com.xq.menu.ClickButton;
import com.xq.menu.Menu;
import com.xq.menu.ViewButton;
import com.xq.po.AccessToken;

import sun.net.httpserver.DefaultHttpServerProvider;
import net.sf.json.JSONObject;

public class WeixinUtil {
	private static final String APPID = "wxa700e5612d59ef41";
	private static final String APPSECRET = "d782233d10ece1c593dfbaa5f6c20c95";
    
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	
	/**
	 * get请求
	 * @param url
	 * @return
	 */
	public static JSONObject doGetStr(String url){
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url); 
		JSONObject jsonObject = null;
		try {
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if(entity != null){
				String result = EntityUtils.toString(entity,"UTF-8");
				jsonObject = jsonObject.fromObject(result);
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	/**
	 * post请求
	 * @param url
	 * @param outStr
	 * @return
	 */
	public static JSONObject doPostStr(String url,String outStr){
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		JSONObject jsonObject = null;
		try {
			httpPost.setEntity(new StringEntity(outStr,"UTF-8"));
			HttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(),"UTF-8");
			jsonObject = JSONObject.fromObject(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
		
	}
	
	/**
	 * 获取access_token
	 * @return
	 */
	public static AccessToken getAccessToken(){
		AccessToken token = new AccessToken();
		String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		JSONObject jsonObject = doGetStr(url);
		if(jsonObject != null){
			token.setToken(jsonObject.getString("access_token"));
			token.setExpiresIn(jsonObject.getString("expires_in"));
		}
		return token;
	}
	
	/**
	 * 组装菜单
	 * @return
	 */
	public static Menu initMenu(){
		Menu menu = new Menu();
		
		ViewButton button00 = new ViewButton();
		button00.setName("吃拌菜");
		button00.setType("view");
		button00.setUrl("https://weidian.com/?userid=922941756&wfr=wx_profile");
		
		ClickButton button11 = new ClickButton();
		button11.setName("一首歌");
		button11.setType("click");
		button11.setKey("11");
		
		ClickButton button12 = new ClickButton();
		button12.setName("一句话");
		button12.setType("click");
		button12.setKey("12");
		
		ClickButton button13 = new ClickButton();
		button13.setName("一篇文");
		button13.setType("click");
		button13.setKey("13");
		
		Button button10 = new Button();
		button10.setName("毒奶");
		button10.setSub_button(new Button[]{button11,button13,button13});
		
		ViewButton button20 = new ViewButton();
		button20.setName("学习去");
		button20.setType("view");
		button20.setUrl("https://xqcheese.github.io/");

		menu.setButton(new Button[]{button00,button10,button20});
		return menu;
	}
	
	public static int createMenu(String token,String menu) throws ParseException,IOException{
		int result = 0;
		String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doPostStr(url, menu);
		if(jsonObject != null){
			result = jsonObject.getInt("errcode");
		}
		return result;
	}

}
