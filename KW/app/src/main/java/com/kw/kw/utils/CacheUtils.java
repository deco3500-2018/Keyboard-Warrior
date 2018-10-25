package com.kw.kw.utils;

import android.content.Context;
import android.content.SharedPreferences;

//缓冲数据和参数
public class CacheUtils {
    public static boolean getBoolean(Context context, String key) {

        SharedPreferences sp = context.getSharedPreferences("keyboard",Context.MODE_PRIVATE);

        return sp.getBoolean(key,false);
    }
//保存软件参数
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences("keyboard",Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }
//缓存文本数据
    public static void putString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences("keyboard",Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("keyboard",Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }
}
