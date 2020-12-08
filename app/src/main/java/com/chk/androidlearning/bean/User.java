package com.chk.androidlearning.bean;

import java.io.Serializable;

/**
 * Created by CHK on 20-12-3.
 */
public class User implements Serializable {

    String name;

    int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
