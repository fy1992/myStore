package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 店面
 * Created by fy on 2016/12/29.
 */
@Table(name = "t_store")
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //店面名称
    private String name;
    //店面类型  0 总店 1 分店
    private int type;
    //创建日期
    @Column(name = "create_date")
    private Date createDate;
    //营业时间
    @Column(name = "work_time")
    private String workTime;
    //店面简介
    private String description;
    //地址
    private String addr;
    //联系电话
    private String phone;
    //联系人
    private String contact;
    //店面编号
    @Column(name = "store_no")
    private String storeNo;
    //状态  0 非营业 1 营业
    private int status;
    @Column(name = "industry_id")
    private int industry;
    //是否是连锁店 0 不是 1 是
    @Column(name = "is_multiple", columnDefinition = "INT DEFAULT 0")
    private int multiple;
    @ManyToOne
    @JoinColumn(name = "pid")
    private Store parent;
    @ManyToMany
    @JoinTable(name = "t_supplier_store",
            joinColumns = {@JoinColumn(name = "store_id")},
            inverseJoinColumns = {@JoinColumn(name = "supplier_id")})
    private Set<Supplier> supplierSet = new HashSet<>();
    //配送费
    @Column(name = "express_fee")
    private double expressFee;
    //送达时间
    @Column(name = "express_time")
    private String expressTime;
    //起送价
    @Column(name = "start_price")
    private double startPrice;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIndustry() {
        return industry;
    }

    public void setIndustry(int industry) {
        this.industry = industry;
    }

    public int getMultiple() {
        return multiple;
    }

    public void setMultiple(int multiple) {
        this.multiple = multiple;
    }

    public Set<Supplier> getSupplierSet() {
        return supplierSet;
    }

    public void setSupplierSet(Set<Supplier> supplierSet) {
        this.supplierSet = supplierSet;
    }

    public Store getParent() {
        return parent;
    }

    public void setParent(Store parent) {
        this.parent = parent;
    }

    public double getExpressFee() {
        return expressFee;
    }

    public void setExpressFee(double expressFee) {
        this.expressFee = expressFee;
    }

    public String getExpressTime() {
        return expressTime;
    }

    public void setExpressTime(String expressTime) {
        this.expressTime = expressTime;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }
}
