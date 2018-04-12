package com.example.a74993.speaktest02.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * 此类用于检查用户权限,用于检查用户权限，如果用户权限未开，需要提醒用户权限开启
 * Created by 74993 on 2018/3/9.
 */

public class PermissionChecker {
    private  Context mcontext = null;
    public PermissionChecker(Context context){
        mcontext = context;
    }
    //判断权限集合
    public boolean lacksPermissions(String... permisions){
        for(String permission : permisions){
            if(lacksPermissions(permission))
                return true;
        }
        return false;
    }

    //判断是否缺少权限
    private boolean lacksPermission(String permission){
        return ContextCompat.checkSelfPermission(mcontext,permission) == PackageManager.PERMISSION_DENIED;
    }
}
