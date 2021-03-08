package com.chk.androidlearning.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by CHK on 21-3-5.
 */
public class ReflectUtil {

    public static Field getField(Class cls,String fieldName) {
        Field field = null;
        while (cls != null) {
            try {
                field = cls.getDeclaredField(fieldName);
                if (field != null) {
                    field.setAccessible(true);
                    return field;
                }
            } catch (NoSuchFieldException e) {
//                e.printStackTrace();
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

    public static Method getMethod(Class cls,String methodName,Class<?>... paramTypes) {
        Method method = null;
        while (cls != null) {
            try {
                method = cls.getDeclaredMethod(methodName,paramTypes);
                if (method != null) {
                    method.setAccessible(true);
                    return method;
                }
            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

}
