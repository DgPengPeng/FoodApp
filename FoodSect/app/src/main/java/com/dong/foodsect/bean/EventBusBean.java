package com.dong.foodsect.bean;

/**
 * Created by dllo on 16/12/13.
 */
public class EventBusBean {

    private String name;
    private String img;


    public EventBusBean() {
    }

    public EventBusBean(String name, String img) {
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
