package com.dong.a_greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dllo on 16/12/5.
 *
 */
@Entity
public class Person {
    @Id
    // 切记 Long是大写的
    private Long id;
    private String name,sex;
    private int age;

    @Transient
    // 当添加 Transient 这注解的时候表明: 下面的内容不会存入数据库
    // 就是一个普通的成员变量
    private String say;

    @Generated(hash = 749025181)
    public Person(Long id, String name, String sex, int age) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    @Generated(hash = 1024547259)
    public Person() {
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

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
