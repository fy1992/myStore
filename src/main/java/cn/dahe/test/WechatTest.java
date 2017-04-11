package cn.dahe.test;

import cn.dahe.util.HttpRequestProxy;
import weixin.popular.api.TokenAPI;
import weixin.popular.bean.token.Token;

public class WechatTest {

	/**
	 * 自定义创建菜单
	 */
	public static void creteMenu() {
		Token token = TokenAPI.token("wxc7e0539a9fe20a3d", "d4624c36b6795d1d99dcf0547af5443d");
		String callbackUrl = "http://694059031.tunnel.2bdata.com/store/wechatdemo/goodsList";
		String entity = "{\"button\":[{\"type\":\"view\",\"name\":\"壹号掌柜\",\"url\":\"" + callbackUrl + "\"}]}";
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + token.getAccess_token();
		System.out.println(HttpRequestProxy.doPost(url, entity));
	}

	public static void main(String[] args) {
		// getAccess_token();
		creteMenu();
	}
}
