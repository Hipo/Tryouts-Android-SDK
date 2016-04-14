package com.hipo.tryouts.androidsdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

class SharedPrefUtil {

    public static void putString(Context context, String key, String value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString(key, value).apply();
    }

    public static String getString(Context context, final String key, final String defaultValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(key, defaultValue);
    }

    public static void putLong(Context context, String key, long value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putLong(key, value).apply();
    }

    public static long getLong(Context context, final String key, final long defaultValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getLong(key, defaultValue);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context context, final String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean(key, false);
    }


    public static <T> void putObject(Context context, final String key, T value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        final String jsonValue = gson.toJson(value);
        prefs.edit().putString(key, jsonValue).apply();
    }

    public static <T> T getObject(Context context, String key, Type type) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final String jsonValue = prefs.getString(key, null);
        if(jsonValue != null) {
            Gson gson = new Gson();
            return gson.fromJson(jsonValue, type);
        }
        return null;
    }

    public static <T> void putObjectList(Context context, String key, List<T> valueList) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        final String jsonValue = gson.toJson(valueList);
        prefs.edit().putString(key, jsonValue).apply();
    }

    public static <T> List<T> getObjectList(Context context, String key, Type listType) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final String jsonValue = prefs.getString(key, null);
        List<T> list = null;
        if(jsonValue != null) {
            Gson gson = new Gson();
            T[] array = gson.fromJson(jsonValue, listType);
            list = Arrays.asList(array);
        }
        return list;
    }

    public static void removeData(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().clear().apply();
    }
}
