package com.dong.foodsect.volleydemo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by dllo on 16/12/8.
 */
@Entity
public class RecentSearchBean {
    @Id
    private Long id;
    private String name;

    @Transient
    private String say;

    @Generated(hash = 728222498)
    public RecentSearchBean(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 436873297)
    public RecentSearchBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    
}
