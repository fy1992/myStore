package cn.dahe.model;

/**
 * 栏目树
 * */
public class Tree {
	
	private Integer id;
	private String name;
	private Integer pid;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Tree(Integer id, String name, Integer pid) {
		super();
		this.id = id;
		this.name = name;
		this.pid = pid;
	}
	public Tree() {
		super();
	}
	@Override
	public String toString() {
		return "ChannelTree [id=" + id + ", name=" + name + ", pid=" + pid
				+ "]";
	}
}
