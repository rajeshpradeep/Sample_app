package com.example.structure.utils;

import javax.inject.Inject;

/**
 * Created by Rajesh Pradeep G on 30-10-2019
 */
public class SharedPreferencesUtil {

    private android.content.SharedPreferences mSharedPreferences;

    /**
     * initialize the shared preference
     * */
    @Inject
    public SharedPreferencesUtil(android.content.SharedPreferences mSharedPreferences) {
        this.mSharedPreferences = mSharedPreferences;
    }

    public SharedPreferencesUtil() {

    }

    public void clearDateDetails(String key) {
        mSharedPreferences.edit().remove(key).apply();
    }

    /*  Delete all data in sharedPreference*/
    public void deleteAllSession() {
        mSharedPreferences.edit().clear().apply();
    }

    /* Save user Email from API*/
    public void saveUserEmail(String key, String data) {
        mSharedPreferences.edit().putString(key, data).apply();
    }

    public String getUserEmail(String key) {
        return mSharedPreferences.getString(key, null);
    }

    /* Save user Name from API*/
    public void saveUserName(String key, String data) {
        mSharedPreferences.edit().putString(key, data).apply();
    }

    public String getUserName(String key) {
        return mSharedPreferences.getString(key, null);
    }

    /* Save Token from API*/
    public void saveToken(String key, String data) {
        mSharedPreferences.edit().putString(key, data).apply();
    }

    public String getToken(String key) {
        return mSharedPreferences.getString(key, null);
    }

    /* Save User Type from API*/
    public void saveUserType(String key, String data) {
        mSharedPreferences.edit().putString(key, data).apply();
    }

    public String getUserType(String key) {
        return mSharedPreferences.getString(key, null);
    }

    /* Save user id from API*/
    public void saveUserID(String key, int data) {
        mSharedPreferences.edit().putInt(key, data).apply();
    }

    public int getUserID(String key) {
        return mSharedPreferences.getInt(key, 0);
    }

    /* Save device id from API*/
    public void saveDeviceID(String key, String data) {
        mSharedPreferences.edit().putString(key, data).apply();
    }

    public String getDeviceID(String key) {
        return mSharedPreferences.getString(key, null);
    }

    /* Save firebase token*/
    public void saveFirebaseToken(String key, String data) {
        mSharedPreferences.edit().putString(key, data).apply();
    }

    public String getFirebaseToken(String key) {
        return mSharedPreferences.getString(key, null);
    }
}
