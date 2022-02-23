package com.example.structure.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Permission {
    public static int PERMISSION_CODE = 1848;

    /**
     * check the persmission
     * */
    public static boolean checkPermission(Context mContext, String permission) {
        return ContextCompat.checkSelfPermission(mContext, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static void askPermission(Activity mActivity, String permission) {
        ActivityCompat.requestPermissions(mActivity, new String[]{permission}, PERMISSION_CODE);
    }

    public static void askPermission(Activity mActivity, String[] permission) {
        ActivityCompat.requestPermissions(mActivity, permission, PERMISSION_CODE);
    }

    public static void askPermission(Activity mActivity, String permission, int permissionCode) {
        ActivityCompat.requestPermissions(mActivity, new String[]{permission}, permissionCode);
    }

    public static void askPermission(Activity mActivity, String[] permission, int permissionCode) {
        ActivityCompat.requestPermissions(mActivity, permission, permissionCode);
    }
}
