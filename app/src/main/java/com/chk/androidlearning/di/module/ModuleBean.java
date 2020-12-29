package com.chk.androidlearning.di.module;

/**
 * Created by CHK on 20-12-27.
 */
public class ModuleBean {

    String schoolName;
    int schoolClass;

    public ModuleBean(String schoolName, int schoolClass) {
        this.schoolName = schoolName;
        this.schoolClass = schoolClass;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(int schoolClass) {
        this.schoolClass = schoolClass;
    }

    @Override
    public String toString() {
        return "ModuleBean{" +
                "schoolName='" + schoolName + '\'' +
                ", schoolClass=" + schoolClass +
                '}'+" HashCode:"+hashCode();
    }
}
