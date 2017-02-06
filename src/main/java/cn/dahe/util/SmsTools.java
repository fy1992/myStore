package cn.dahe.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

public class SmsTools {
	private static Log log = LogFactory.getLog(SmsTools.class);
	/**
	 * 审批短信通知
	 * @param phone
	 * @param msg
	 * */
	public static boolean sendSms_audit(String phone, String msg, boolean flag, int type) {
		log.info("---- sendSms_audit begin phone："+phone+"----");
		String content = "";
		// 发送内容
		if(type == 0){
			content = "您的手机号：" + phone + "， 因："+msg+"， 企业/单位申报审批不通过，请您核实申报信息后再次尝试";
			if(flag){
				content = "您的手机号：" + phone + "， 企业/单位申报审批已通过，可以进行相关备案";
			}
		}else{
			content = "您的手机号：" + phone + "， 因："+msg+"，媒体审批不通过，请登录后台进行媒体备案修改";
			if(flag){
				content = "您的手机号：" + phone + "， 媒体审批已通过";
			}
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("mobile", phone);
		params.put("msg", content);
		String result = HttpRequestProxy.doPost("http://sms.dahe.cn/dahe/sms/wxb", params);
		JSONObject json = new JSONObject(result);
		log.info(json);
		log.info("---- sendSms_audit end ----");
		return json.get("result").toString().equals("1");
	}
	
	/**
	 * 手机验证码发送
	 * @param phone
	 * @param code
	 * */
	public static boolean sendSms(String phone, String code) {
		log.info("---- sendSms begin phone："+phone+"----");
		// 发送内容
		String content = "您的验证码为：" + code
				+ "，请在60秒内正确填写，如不是本人操作请忽略!";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("mobile", phone);
		params.put("msg", content);
		String result = HttpRequestProxy.doPost("http://sms.dahe.cn/dahe/sms/wxb", params);
		JSONObject json = new JSONObject(result);
		log.info(json);
		log.info("---- sendSms end phone："+phone+"----");
		return json.get("result").toString().equals("1");
	}
}
