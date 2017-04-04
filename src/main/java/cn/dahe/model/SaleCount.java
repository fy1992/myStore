package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 销售统计
 * Created by fy on 2017/1/12.
 */
@Table(name = "t_sale_count")
@Entity
public class SaleCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //统计时间
    @Column(name = "count_date")
    private String countDate;
    //所属分店Id
    @Column(name = "store_id")
    private int storeId;
    //现金支付
    @Column(name = "paid_by_money")
    private double paidByMoney;
    //现金收入
    private double income;
    //现金支出
    private double pay;
    //营业额
    private double turnover;
    //利润
    private double gain;
    //单数
    @Column(name = "order_num")
    private int orderNum;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountDate() {
        return countDate;
    }

    public void setCountDate(String countDate) {
        this.countDate = countDate;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public double getPaidByMoney() {
        return paidByMoney;
    }

    public void setPaidByMoney(double paidByMoney) {
        this.paidByMoney = paidByMoney;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }

    public double getGain() {
        return gain;
    }

    public void setGain(double gain) {
        this.gain = gain;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }
}
