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
    private String title;


    @Transient
    private String say;


    @Generated(hash = 258558950)
    public CollectBean(Long id, String url, String title) {
        this.id = id;
        this.url = url;
        this.title = title;
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


    public String getTitle() {
        return this.title;
    }


    public void setTitle(String title) {
        this.title = title;
    }




}
