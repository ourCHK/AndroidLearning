package com.chk.androidlearning.bean;

/**
 * Created by CHK on 21-1-14.
 */
public  class  RecyclerItem<T> {

    public final static int HEADER = 1;
    public final static int DATA = 2;
    public final static int FOOTER = 3;

    int type;
    T data;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
