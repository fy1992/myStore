package cn.dahe.dto;

/**
 * 栏目树
 * */
public class Tree {
	
	private Integer id;
	private String name;
	private Integer pid;
	private boolean open;
	
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

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public Tree(Integer id, String name, Integer pid, boolean open) {
		super();
		this.id = id;
		this.name = name;
		this.pid = pid;
		this.open = open;
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
