package cn.dahe.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * 行业
 * Created by fy on 2017/1/12.
 */
@Table(name = "t_industry")
@Entity
public class Industry {
    @Id
    @GeneratedValue
    private int id;
    //行业名称
    private String name;
    //行业对应
    @OneToMany(mappedBy = "industry", cascade = CascadeType.ALL)
    private Set<Store> storeSet = new HashSet<>();

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

    public Set<Store> getStoreSet() {
        return storeSet;
    }

    public void setStoreSet(Set<Store> storeSet) {
        this.storeSet = storeSet;
    }
}
