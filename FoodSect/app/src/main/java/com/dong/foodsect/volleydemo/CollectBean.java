package com.dong.foodsect.volleydemo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dllo on 16/12/9.
 */

@Entity
public class CollectBean {
    @Id
    private Long id;
    private String url;


    @Transient
    private String say;


    @Generated(hash = 825994761)
    public CollectBean(Long id, String url) {
        this.id = id;
        this.url = url;
    }


    @Generated(hash = 420494524)
    public CollectBean() {
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getUrl() {
        return this.url;
    }


    public void setUrl(String url) {
        this.url = url;
    }

}
