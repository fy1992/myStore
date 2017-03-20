package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 交接班
 * Created by fy on 2017/3/20.
 */
@Table(name = "t_change_shifts")
@Entity
public class ChangeShifts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //开始时间
    @Column(name = "start_time")
    private Date startTime;
    //结束时间
    @Column(name = "end_time")
    private Date endTime;
    //工号
    @Column(name = "cashier_no")
    private String cashierNo;
    @Column(name = "cashier_name")
    private String cashierName;
    //收银单数
    private int num;
    //总额
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "store_id")
    private int storeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCashierNo() {
        return cashierNo;
    }

    public void setCashierNo(String cashierNo) {
        this.cashierNo = cashierNo;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public ChangeShifts() {
    }

    public ChangeShifts(Date endTime, String cashierNo, String cashierName, int num, double totalPrice, int storeId) {
        this.startTime = new Date();
        this.endTime = endTime;
        this.cashierNo = cashierNo;
        this.cashierName = cashierName;
        this.num = num;
        this.totalPrice = totalPrice;
        this.storeId = storeId;
    }
}
