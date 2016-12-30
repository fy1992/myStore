package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 仓库交易日志纪录
 * Created by fy on 2016/12/29.
 */
@Table(name = "t_stock_log")
@Entity
public class StockLog {
    @Id
    @GeneratedValue
    private int id;
    //交易数量
    @Column(name = "opt_num")
    private int optNum;
    //交易类型  0 出货 1 进货
    @Column(name = "opt_type")
    private int optType;
    //交易时间
    @Column(name = "opt_date")
    private Date optDate;
    //备注
    private String description;
}
