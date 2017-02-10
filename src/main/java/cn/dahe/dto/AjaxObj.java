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

	@Override
	public String toString() {
		return "AjaxObj{" +
				"result=" + result +
				", msg='" + msg + '\'' +
				", Object=" + Object +
				", totalPage=" + totalPage +
				'}';
	}
}
