package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 会员
 * Created by fy on 2016/12/29.
 */
@Table(name = "t_vip")
@Entity
public class Vip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //会员编号
    @Column(name = "vip_no")
    private String vipNo;
    //会员姓名
    @Column(name = "vip_name")
    private String vipName;
    //会员折扣
    private String rebate;
    //会员密码
    private String password;
    //会员生日
    private Date birthday;
    //开卡日期
    @Column(name = "create_card_date")
    private Date createCardDate;
    //到期日期
    @Column(name = "due_date")
    private Date dueDate;
    //是否允许赊账 0 不允许 1 允许
    @Column(name = "is_credit", columnDefinition = "INT DEFAULT 0")
    private int credit;
    //qq
    private String qq;
    //email
    private String email;
    //地址
    private String addr;
    //备注
    private String description;
    //是否启用 0 禁用 1 启用
    private int status;
    //会员余额
    private double balance;
    //会员积分
    private int point;
    //会员等级Id
    @Column(name = "vip_level_id")
    private int vipLevelID;
    //会员等级名称
    @Column(name = "vip_level_name")
    private String vipLevelName;
    //所属分店
    @Column(name = "store_id")
    private int storeId;
    @Column(name = "store_name")
    private String storeName;
    //注册时间
    @Column(name = "register_time")
    private Date registerTime;
    //电话
    private String phone;
    //微信openId
    private String openId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVipNo() {
        return vipNo;
    }

    public void setVipNo(String vipNo) {
        this.vipNo = vipNo;
    }

    public String getVipName() {
        return vipName;
    }

    public void setVipName(String vipName) {
        this.vipName = vipName;
    }

    public String getRebate() {
        return rebate;
    }

    public void setRebate(String rebate) {
        this.rebate = rebate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreateCardDate() {
        return createCardDate;
    }

    public void setCreateCardDate(Date createCardDate) {
        this.createCardDate = createCardDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getVipLevelID() {
        return vipLevelID;
    }

    public void setVipLevelID(int vipLevelID) {
        this.vipLevelID = vipLevelID;
    }

    public String getVipLevelName() {
        return vipLevelName;
    }

    public void setVipLevelName(String vipLevelName) {
        this.vipLevelName = vipLevelName;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
