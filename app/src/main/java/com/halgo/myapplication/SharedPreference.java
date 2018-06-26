/*
 *
 * 	Smart Development Service (Pagez)
 *
 * @createdBy  AndroidDev on jeu., 24 mai 2018 14:07:40 +0100
 * @copyright  Copyright (c) 2018 Smart Development Service (Pagez)
 * @email      smart.dev.service@gmail.com
 *
 * 	© Copyright 2018 Smart Development Service (Pagez). Smart Development Service is the copyright holder
 * 	of all code contained in this file. Do not redistribute or
 *  	re-use without permission.
 *
 * @lastModifiedOn jeu., 24 mai 2018 14:04:48 +0100
 */

/**
 * @author B.A.Ismail
 */
package com.halgo.myapplication;
import java.util.Map;
import java.util.Map.Entry;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

/**
 * MGM Preference class
 */
public class SharedPreference {

    // Login related
    public final static String ID = "id";
    public final static String API_TOKEN = "api_token";
    public final static String USER_ITEM = "user_item";
    public final static String COMPANY_ITEM = "company_item";
    public final static String ACCOUNT_TYPE = "account_type";
    /**
     * setting language.
     */
    public static final String KEY_LANGUAGE = "language";

    /*
     */
    public static final String IS_LOGGED = "is_logged";


    private static final String TAG = SharedPreference.class.getName();
    /**
     * No log should be done on keys starting with this prefix
     */
    private static final Boolean LOG_ENABLED = false;
    private static final String DOUBLE_QUOTE_CHAR = "\"";
    private static final String DOUBLE_QUOTE_CHAR_REPLACEMENT = "&#34;";
    /**
     * Singleton instance
     */
    private static SharedPreference mInstance;
    private static Context mContext;
    /**
     * System preference class
     */
    private SharedPreferences mPreferences;

    /**
     * Constructor
     */
    private SharedPreference(Context context)
    {
        mContext = context;
        String sharedPreferencesFileName = context.getPackageName() + "_preferences";
        Log.d(TAG, "Loading shared preferences file : " + sharedPreferencesFileName);
        mPreferences = context.getSharedPreferences(sharedPreferencesFileName, Context.MODE_PRIVATE);
    }

    /**
     * Singleton
     */
    public static SharedPreference getInstance(Context context)
    {
        if (mInstance == null) {
            synchronized (SharedPreference.class) {
                if (mInstance == null) {
                    mInstance = new SharedPreference(context);
                }
            }
        }
        return mInstance;
    }

    public boolean getBoolean(String key, boolean defValue)
    {
        boolean value = mPreferences.getBoolean(key, defValue);
        log("getBooleanPreference", key, String.valueOf(value));
        return value;
    }

    public float getFloat(String key, float defValue)
    {
        Float value = mPreferences.getFloat(key, defValue);
        log("getFloatPreference", key, String.valueOf(value));
        return value;
    }

    public int getInt(String key, int defValue)
    {
        int value = defValue;
        try {
            value = mPreferences.getInt(key, defValue);
        } catch (ClassCastException e) {
            value = Integer.parseInt(mPreferences.getString(key, String.valueOf(defValue)));
            putInt(key, value);
        }
        log("getIntPreference", key, String.valueOf(value));
        return value;
    }

    public long getLong(String key, long defValue)
    {
        Long value;
        try {
            value = mPreferences.getLong(key, defValue);
        } catch (ClassCastException e) {
            value = Long.parseLong(mPreferences.getString(key, String.valueOf(defValue)));
            putLong(key, value);
        }
        log("getLongPreference", key, String.valueOf(value));
        return value;
    }

    public String getString(String key, String defValue)
    {
        String value = mPreferences.getString(key, defValue).replace(DOUBLE_QUOTE_CHAR_REPLACEMENT, DOUBLE_QUOTE_CHAR);
        log("getStringPreference", key, value);
        return value;
    }

    public void putBoolean(String key, boolean value)
    {
        log("setBooleanPreference", key, String.valueOf(value));
        Editor edit = mPreferences.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    public void putFloat(String key, float value)
    {
        log("setFloatPreference", key, String.valueOf(value));
        Editor edit = mPreferences.edit();
        edit.putFloat(key, value);
        edit.commit();
    }

    public void putInt(String key, int value)
    {
        log("setIntPreference", key, String.valueOf(value));
        Editor edit = mPreferences.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    public void putLong(String key, long value)
    {
        log("setLongPreference", key, String.valueOf(value));
        Editor edit = mPreferences.edit();
        edit.putLong(key, value);
        edit.commit();
    }

    public void putString(String key, String value)
    {
        log("setStringPreference", key, value);
        Editor edit = mPreferences.edit();
        edit.putString(key, value.replace(DOUBLE_QUOTE_CHAR, DOUBLE_QUOTE_CHAR_REPLACEMENT));
        edit.commit();
    }

    public void clear()
    {
        Editor edit = mPreferences.edit();
        edit.clear();
        edit.commit();
    }


    /**
     * Whether MGM preference has this type of value
     */
    public boolean contains(String key)
    {
        return mPreferences.contains(key);
    }

    public void remove(String key)
    {
        Editor edit = mPreferences.edit();
        edit.remove(key);
        edit.commit();
    }

    public void clearAll()
    {
        Map<String, ?> preferences = mPreferences.getAll();

        Editor edit = mPreferences.edit();
        for (Entry<String, ?> me : preferences.entrySet()) {
            String key = me.getKey();
            edit.remove(key);
        }
        edit.commit();
    }

    /**
     * exception for Double put as a String
     *
     * @param key
     * @param value
     */
    public void put(String key, Object value)
    {
        log("setStringPreference", key, "" + value);
        if (value instanceof Boolean) {
            putBoolean(key, (Boolean) value);
        } else if (value instanceof String) {
            putString(key, (String) value);
        } else if (value instanceof Integer) {
            putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            putLong(key, (Long) value);
        } else if (value instanceof Float) {
            putFloat(key, (Float) value);
        } else if (value instanceof Double) {
            putLong(key, ((Double) value).longValue());
        }
    }

    /**
     * @param content
     * @param key
     * @param value
     */
    private void log(String content, String key, String value)
    {
        if (!LOG_ENABLED) {
            return;
        }

        if (null == key) {
            key = "";
        }
        if (null == value) {
            value = "";
        }

        Log.d(TAG, content + " " + key + " = " + value);
    }

    public boolean isUserLogin()
    {
        boolean isLogged = mPreferences.getBoolean(IS_LOGGED, DefaultValues.DEFAULT_IS_LOGGED);

        return isLogged && mPreferences.contains(ID) && mPreferences.contains(API_TOKEN);
    }

    public interface DefaultValues {
        /**
         * default of first installation state.
         */
        boolean DEFAULT_FIRST_INSTALLATION = true;
        boolean DEFAULT_IS_LOGGED = false;
    }

    public String getAccessToken(Context context){
        return getInstance(context).getString(BaseConstant.ACCESS_TOKEN, "");
    }

}
