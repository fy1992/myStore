package cn.dahe.dto;
/**
 * json 返回值封装
 * */
public class AjaxObj{
	/**
	 * 0 fail 1 success
	 */
	private int result;
	private String msg = "";
	private Object Object;
	private int totalPage;
	//validform 固定格式的返回值
	private String status; // 状态 y 成功  n 失败
	private String info;  //输出提示信息
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public Object getObject() {
		return Object;
	}
	public void setObject(Object object) {
		Object = object;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "AjaxObj{" +
				"result=" + result +
				", msg='" + msg + '\'' +
				", Object=" + Object +
				", totalPage=" + totalPage +
				", status='" + status + '\'' +
				", info='" + info + '\'' +
				'}';
	}
}
