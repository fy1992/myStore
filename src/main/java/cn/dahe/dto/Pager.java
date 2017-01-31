package cn.dahe.dto;

import java.util.Date;
import java.util.List;

public class Pager<T> {
	//-----------搜索用的参数----------------------
	//从第几行开始
	private int start;
	//搜索参数
	//权重
	private int rank;
	//状态
	private int state;
	//状态2
	private int status;
	//标题等字符串参数
	private String stringParam1;
	private String stringParam2;
	private String stringParam3;
	private String stringParam4;
	//父id
	private String pid;
	//int类型的参数
	private Integer intParam1 = 0;
	private Integer intParam2 = 0;
	private Integer intParam3 = 0;
	private Integer intParam4 = 0;
	//开始时间
	private Date startTime;
	//结束时间
	private Date endTime;
	//------------dataTables 用的参数---------------
	//数据数组
	private List<T> aaData;
	//总数据量
	private long iTotalRecords;
	//过滤之后，实际的行数
	private long iTotalDisplayRecords;
	//排序字段
	private String orderColumn;
	//排序规则
	private String orderDir;
	public List<T> getAaData() {
		return aaData;
	}
	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getStringParam2() {
		return stringParam2;
	}
	public void setStringParam2(String stringParam2) {
		this.stringParam2 = stringParam2;
	}
	public String getStringParam3() {
		return stringParam3;
	}
	public void setStringParam3(String stringParam3) {
		this.stringParam3 = stringParam3;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getOrderColumn() {
		return orderColumn;
	}
	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}
	public String getOrderDir() {
		return orderDir;
	}
	public void setOrderDir(String orderDir) {
		this.orderDir = orderDir;
	}
	public long getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(long iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public long getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(long iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	public Integer getIntParam1() {
		return intParam1;
	}
	public void setIntParam1(Integer intParam1) {
		this.intParam1 = intParam1;
	}
	public Integer getIntParam2() {
		return intParam2;
	}
	public void setIntParam2(Integer intParam2) {
		this.intParam2 = intParam2;
	}
	public Integer getIntParam3() {
		return intParam3;
	}
	public void setIntParam3(Integer intParam3) {
		this.intParam3 = intParam3;
	}

	public String getStringParam1() {
		return stringParam1;
	}

	public void setStringParam1(String stringParam1) {
		this.stringParam1 = stringParam1;
	}

	public String getStringParam4() {
		return stringParam4;
	}

	public void setStringParam4(String stringParam4) {
		this.stringParam4 = stringParam4;
	}

	public Integer getIntParam4() {
		return intParam4;
	}

	public void setIntParam4(Integer intParam4) {
		this.intParam4 = intParam4;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
