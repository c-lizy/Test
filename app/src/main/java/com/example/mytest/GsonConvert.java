//package com.example.mytest;
//
//
//import android.text.TextUtils;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonIOException;
//import com.google.gson.JsonParseException;
//import com.google.gson.JsonParser;
//import com.google.gson.JsonSyntaxException;
//import com.google.gson.stream.JsonReader;
//
//import java.io.Reader;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//
//import cn.com.spdb.interbank.per.logs.Logs;
//
//
///**
// * 格式化JSON工具类（使用GSON）
// * Author: ZPing
// * Email: zp2@yitong.com.cn
// * Version: 2016-11-1  0001 13:18 Inc. All rights reserved.
// */
//public class GsonConvert {
//
//    private static Gson create() {
//        return GsonHolder.gson;
//    }
//
//    private static class GsonHolder {
//        private static Gson gson = new Gson();
//    }
//
//    public static <T> T fromJson(String json, Class<T> type) throws JsonIOException, JsonSyntaxException {
//        return create().fromJson(json, type);
//    }
//
//    public static <T> T fromJson(String json, Type type) {
//        return create().fromJson(json, type);
//    }
//
//    public static <T> T fromJson(JsonReader reader, Type typeOfT) throws JsonIOException, JsonSyntaxException {
//        return create().fromJson(reader, typeOfT);
//    }
//
//    public static <T> T fromJson(Reader json, Class<T> classOfT) throws JsonSyntaxException, JsonIOException {
//        return create().fromJson(json, classOfT);
//    }
//
//    public static <T> T fromJson(Reader json, Type typeOfT) throws JsonIOException, JsonSyntaxException {
//        return create().fromJson(json, typeOfT);
//    }
//
//    public static String toJson(Object src) {
//        return create().toJson(src);
//    }
//
//    public static String toJson(Object src, Type typeOfSrc) {
//        return create().toJson(src, typeOfSrc);
//    }
//
//
//    /**
//     * 返回带有泛型的原型类
//     *
//     * @param raw  原型class
//     * @param args 泛型列表
//     * @return
//     */
//    public static ParameterizedType type(final Class raw, final Type... args) {
//        return new ParameterizedType() {
//            public Type getRawType() {
//                return raw;
//            }
//
//            public Type[] getActualTypeArguments() {
//                return args;
//            }
//
//            public Type getOwnerType() {
//                return null;
//            }
//        };
//    }
//
//    /**
//     * 判断数据是否为JSON
//     * @param content
//     * @return
//     */
//    public static boolean isJSON(String content) {
//
//        if (TextUtils.isEmpty(content)) {
//            return false;
//        }
//
//        try {
//            new JsonParser().parse(content);
//            return true;
//        } catch (JsonParseException e) {
//            Logs.e("bad json: " + content);
//            return false;
//        }
//    }
//}
