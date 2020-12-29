package com.chk.androidlearning.di;

import javax.inject.Inject;

/**
 * Created by CHK on 20-12-27.
 */
public class DiTest {

    String name;

    int age;

    @Inject
    public DiTest() {
        name = "DITest";
        age = 18;
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

    @Override
    public String toString() {
        return "DiTest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                "} HashCode:"+hashCode();
    }
}
