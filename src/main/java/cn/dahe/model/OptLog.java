package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 操作日志
 * Created by fy on 2017/1/13.
 */
@Table(name = "t_opt_log")
@Entity
public class OptLog {
    @Id
    @GeneratedValue
    private int id;
    //操作时间
    @Column(name = "opt_date")
    private Date operatDate;
    //操作IP
    @Column(name = "opt_ip")
    private String operatIp;
    //具体操作
    @Column(name = "opt_msg")
    private String operatMsg;
    //操作人所属店面Id
    @Column(name = "store_id")
    private int storeId;
    //操作人所属店面名称
    @Column(name = "store_name")
    private String storeName;
    //操作人id
    @Column(name = "operator_id")
    private int operatorId;
    //操作人账号
    @Column(name = "operator_name")
    private String operatorName;
    //被操作对象Id
    @Column(name = "object_id")
    private int objectId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOperatDate() {
        return operatDate;
    }

    public void setOperatDate(Date operatDate) {
        this.operatDate = operatDate;
    }

    public String getOperatIp() {
        return operatIp;
    }

    public void setOperatIp(String operatIp) {
        this.operatIp = operatIp;
    }

    public String getOperatMsg() {
        return operatMsg;
    }

    public void setOperatMsg(String operatMsg) {
        this.operatMsg = operatMsg;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public OptLog() {
    }

    public OptLog(Date operatDate, String operatIp,
                  String operatMsg, int operatorId,
                  String operatorName, int storeId,
                  String storeName, int objectId) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.operatDate = operatDate;
        this.operatIp = operatIp;
        this.operatMsg = operatMsg;
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.objectId = objectId;
    }
}
