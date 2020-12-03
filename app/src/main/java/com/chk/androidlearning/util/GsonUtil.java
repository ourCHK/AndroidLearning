package com.chk.androidlearning.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;

public class GsonUtil {
	
	private static Gson gson = new Gson();
	
	private static Gson gsonExpose = new GsonBuilder().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create();
	
	private GsonUtil() {
	}
	
	private static Gson getGson() {
		return gson;
	}

	/**
	 * 序列化Gson
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		return getGson().toJson(object);
	}

	/**
	 * 序列化Gson,只序列化带有@Expose注解的Field
	 * @param object
	 * @return
	 */
	public static String toJsonExpose(Object object) {
		return gsonExpose.toJson(object);
	}
	
	public static <T> T fromJson(String json,Class<T> jsonClass) {
		return gson.fromJson(json, jsonClass);
	}
	
	public static <T> T fromJson(String json,TypeToken<T> typeToken) {
		return gson.fromJson(json, typeToken.getType());
	}

	public static <T> T fromJson(Reader reader,Class<T> jsonClass) {
		return gson.fromJson(reader,jsonClass);
	}

	public static <T> T fromJson(Reader reader,TypeToken<T> typeToken) {
		return gson.fromJson(reader, typeToken.getType());
	}
	
}
