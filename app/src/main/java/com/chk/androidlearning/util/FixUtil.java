package com.chk.androidlearning.util;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CHK on 21-3-5.
 */
public class FixUtil {

    private final static String TAG = FixUtil.class.getSimpleName();

    public static void installPatch(Context context,String pathPatch) {
        File patchFile = new File(pathPatch);
        if (!patchFile.exists()) {
            Log.i(TAG,"patchFile not exist!");
            return;
        }
        File optDir = context.getCacheDir();
        try {
            ClassLoader classLoader = context.getClassLoader();
            Class<? extends ClassLoader> pathClassLoaderCls = classLoader.getClass();
            Log.i(TAG,"classLoader:"+classLoader);
            Log.i(TAG,"pathClassLoaderCls:"+pathClassLoaderCls);
            Field pathListField = ReflectUtil.getField(pathClassLoaderCls,"pathList");

            Object pathList = pathListField.get(classLoader);
            Log.i(TAG,"pathList is null:"+(pathList == null));
            Field dexElementField = ReflectUtil.getField(pathList.getClass(),"dexElements");
            Object[] dexElements = (Object[]) dexElementField.get(pathList);
            Object[] patchElements = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Method makeDexElementMethod =
                        ReflectUtil.getMethod(pathList.getClass(),"makeDexElements", List.class,File.class,List.class,ClassLoader.class);
                Log.i(TAG,"makeDexElementMethod is null:"+(makeDexElementMethod == null));
                List<File> dexFileList = new ArrayList<>();
                dexFileList.add(patchFile);
                ArrayList<IOException> expList = new ArrayList<>();
                patchElements = (Object[]) makeDexElementMethod.invoke(null,dexFileList,optDir,expList,classLoader);
            } else {
                Log.i(TAG,"当前版本不支持热更新！");
            }
            Object[] newElements = (Object[]) Array.newInstance(dexElements.getClass().getComponentType(),dexElements.length+patchElements.length);
            System.arraycopy(patchElements,0,newElements,0,patchElements.length);
            System.arraycopy(dexElements,0,newElements,patchElements.length,dexElements.length);
            dexElementField.set(pathList,newElements);
            Log.i(TAG,"热更新修复完成");
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG,"热更新修复失败");
        }
    }

    public static void installPatch2(Context context, String patchPath) {
        File patchFile = new File(patchPath);
        if (!patchFile.exists()) {
            Log.e(TAG, "installPatch: " + patchPath + "无文件，无需修复");
            return;
        }
        File optDir = context.getCacheDir();
        try {
            // 1.得到当前程序的classLoader
            ClassLoader classLoader = context.getClassLoader();
            // 2.反射获取类加载器的pathList属性
            Class<? extends ClassLoader> pathClassLoaderCls = classLoader.getClass();
            Field pathListField = ReflectUtil.getField(pathClassLoaderCls, "pathList");
            Object pathList = pathListField.get(classLoader);
            // 3.反射获取DexPathList中的dexElements
            Field dexElementsField = ReflectUtil.getField(pathList.getClass(), "dexElements");
            Object[] dexElements = (Object[]) dexElementsField.get(pathList);
            // 4.加载自己的dex,并转化为Element[]
            // 通过反射DexPathList 中的makeDexElements() 方法来实现
            // 4.1 反射获取makeDexElements方法
            Object[] patchElements = null;
            // 版本适配
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                // Android 7.0+ 使用makeDexElements(List,File,List,ClassLoader)
                Method makeDexElementsMethod = ReflectUtil.getMethod(pathList.getClass(), "makeDexElements", List.class, File.class, List.class, ClassLoader.class);
                List<File> dexFileList = new ArrayList<>();
                dexFileList.add(patchFile);
                ArrayList<IOException> suppressedExceptions = new ArrayList<IOException>();
                patchElements = (Object[]) makeDexElementsMethod.invoke(null, dexFileList, optDir, suppressedExceptions, classLoader);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // Android 6.0-7.0 使用makePathElements(List,File,List)
                Method makePathElements = ReflectUtil.getMethod(pathList.getClass(), "makePathElements", List.class, File.class, List.class);
                List<File> dexFileList = new ArrayList<>();
                dexFileList.add(patchFile);
                ArrayList<IOException> suppressedExceptions = new ArrayList<IOException>();
                patchElements = (Object[]) makePathElements.invoke(null, dexFileList, optDir, suppressedExceptions);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // Android 5.0-6.0 使用makeDexElements(ArrayList,File,ArrayList)  注意这里参数编程ArrayList类型
                Method makeDexElementsMethod = ReflectUtil.getMethod(pathList.getClass(), "makeDexElements", ArrayList.class, File.class, ArrayList.class);
                List<File> dexFileList = new ArrayList<>();
                dexFileList.add(patchFile);
                ArrayList<IOException> suppressedExceptions = new ArrayList<IOException>();
                patchElements = (Object[]) makeDexElementsMethod.invoke(null, dexFileList, optDir, suppressedExceptions);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                // Android 4.4-5.0 使用makeDexElements(ArrayList,File,ArrayList)  注意这里参数编程ArrayList类型
                // 和5.0一样的方法，注意4.4 会报错 Class ref in pre-verified class resolved to unexpected implementation
                Log.e(TAG, "installPatch: 当前版本：" + Build.VERSION.SDK_INT + "此版本暂未解决 Class ref in pre-verified class resolved to unexpected implementation 的问题，敬请期待！");
                return;
            } else {
                Log.e(TAG, "installPatch: 当前版本：" + Build.VERSION.SDK_INT + "不支持热更新");
                return;
            }
            // 5.合并两个Element[] patchElements要放在前面
            Object[] newElements = (Object[]) Array.newInstance(dexElements.getClass().getComponentType(), dexElements.length + patchElements.length);
            System.arraycopy(patchElements, 0, newElements, 0, patchElements.length);
            System.arraycopy(dexElements, 0, newElements, patchElements.length, dexElements.length);
            // 6.反射替换原来的dexElements
            dexElementsField.set(pathList, newElements);
            Log.e(TAG, "installPatch: 修复成功");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "installPatch: 修复失败!!" + e.getMessage());
        }
    }

}
