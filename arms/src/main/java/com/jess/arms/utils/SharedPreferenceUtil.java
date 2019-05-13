package com.jess.arms.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Set;

/**
 * @since 关于SharedPreference的操作方法, 必须在Application里面调用init()方法
 */
public class SharedPreferenceUtil {

    private static SharedPreferences mCacheSharedPreferences;

    private static Editor mEditor = null;

    /**
     * 使用这个工具类之前必须在Application里面调用这个初始化方法
     *
     * @param context 上下文
     * @return 返回工具类的实例
     */
    public static void init(Context context,String name) {
        if (mCacheSharedPreferences == null) {
            mCacheSharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        }
    }

    /**
     * 往users.xml里面保存 int 数据
     *
     * @param key   键值对名称
     * @param value 保存的值
     */
    public static void set(String key, int value) {
        mEditor = mCacheSharedPreferences.edit();
        mEditor.putInt(key, value);
        mEditor.apply();
    }

    public static void remove(String key) {
        mCacheSharedPreferences.edit().remove(key).apply();
    }

    /**
     * 往users.xml里面保存 long 数据
     *
     * @param key   键值对名称
     * @param value 保存的值
     */
    public static void set(String key, long value) {
        mEditor = mCacheSharedPreferences.edit();
        mEditor.putLong(key, value);
        mEditor.apply();
    }

    /**
     * 往users.xml里面保存 float 数据
     *
     * @param key   键值对名称
     * @param value 保存的值
     */
    public static void set(String key, float value) {
        mEditor = mCacheSharedPreferences.edit();
        mEditor.putFloat(key, value);
        mEditor.apply();
    }

    /**
     * 往users.xml里面保存 boolean 数据
     *
     * @param key   键值对名称
     * @param value 保存的值
     */
    public static void set(String key, boolean value) {
        mEditor = mCacheSharedPreferences.edit();
        mEditor.putBoolean(key, value);
        mEditor.apply();
    }

    /**
     * 往users.xml里面保存 String 数据
     *
     * @param key   键值对名称
     * @param value 保存的值
     */
    public static void set(String key, String value) {
        mEditor = mCacheSharedPreferences.edit();
        mEditor.putString(key, value);
        mEditor.apply();
    }

    /**
     * 往users.xml里面保存 Set<String> 数据
     *
     * @param key   键值对名称
     * @param value 保存的值
     */
    public static void set(String key, Set<String> value) {
        mEditor = mCacheSharedPreferences.edit();
        mEditor.putStringSet(key, value);
        mEditor.apply();
    }

    /**
     * 从users.xml里面获取 字段为 key 的 int 数据
     *
     * @param key 键值对名称
     * @param def 默认返回的值
     * @return key对应的 int 数据
     */
    public static int getInt(String key, int def) {
        return mCacheSharedPreferences.getInt(key, def);
    }

    /**
     * 从users.xml里面获取 字段为 key 的 long 数据
     *
     * @param key 键值对名称
     * @param def 默认返回的值
     * @return key对应的 long 数据
     */
    public static long getLong(String key, long def) {
        return mCacheSharedPreferences.getLong(key, def);
    }

    public static boolean isContains(String key) {
        return mCacheSharedPreferences.contains(key);
    }

    /**
     * 从users.xml里面获取 字段为 key 的 float 数据
     *
     * @param key 键值对名称
     * @param def 默认返回的值
     * @return key对应的 float 数据
     */
    public static float getFloat(String key, float def) {
        return mCacheSharedPreferences.getFloat(key, def);
    }

    /**
     * 从users.xml里面获取 字段为 key 的 boolean 数据
     *
     * @param key 键值对名称
     * @param def 默认返回的值
     * @return key对应的 boolean 数据
     */
    public static boolean getBoolean(String key, boolean def) {
        return mCacheSharedPreferences.getBoolean(key, def);
    }

    /**
     * 从users.xml里面获取 字段为 key 的 String 数据，默认返回值为 ""
     *
     * @param key 键值对名称
     * @return key对应的 String 数据
     */
    public static String getString(String key) {
        return getString(key, "");
    }

    /**
     * 从users.xml里面获取 字段为 key 的 String 数据
     *
     * @param key 键值对名称
     * @param def 默认返回的值
     * @return key对应的 String 数据
     */
    public static String getString(String key, String def) {
        return mCacheSharedPreferences.getString(key, def);
    }

    /**
     * 从users.xml里面获取 字段为 key 的 Set<String> 数据
     *
     * @param key 键值对名称
     * @param def 默认返回的值
     * @return key对应的 Set<String> 数据
     */
    public static Set<String> getStringSet(String key, Set<String> def) {
        return mCacheSharedPreferences.getStringSet(key, def);
    }

    /**
     * 从users.xml里面删除字段为 keys 数据
     *
     * @param keys
     */
    public static void removeAllKeys(String[] keys) {
        mEditor = mCacheSharedPreferences.edit();
        for (int i = 0, l = keys.length; i < l; i++) {
            mEditor.remove(keys[i]);
        }
        mEditor.apply();
    }

    /**
     * 清除所有数据
     */
    public static void clear() {
        mEditor = mCacheSharedPreferences.edit();
        mEditor.clear();
        mEditor.apply();
    }

    public static void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener callback) {
        mCacheSharedPreferences.registerOnSharedPreferenceChangeListener(callback);
    }

    public static void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener callback) {
        mCacheSharedPreferences.unregisterOnSharedPreferenceChangeListener(callback);
    }
}
