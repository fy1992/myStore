package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户登录状态
 * Created by fy on 2017/2/8.
 */
@Table(name = "t_cashier_login_status")
@Entity
public class CashierLoginStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //用户id
    @Column(name = "user_id")
    private String userId;
    //最后活动时间
    @Column(name = "last_active_time")
    private Date lastActiveTime;
    //是否在线 0 不在线 1 在线
    private int isOnline;
    //session id
    @Column(name = "session_id")
    private String sessionId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(Date lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

    public int getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(int isOnline) {
        this.isOnline = isOnline;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
