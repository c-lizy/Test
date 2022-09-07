package com.yizhixionga.test3.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Author:lizy
 * time:2022/9/5 13:26
 * Description : ViewUtil
 **/
public class ViewUtil {

    public static void hideAllinputMethod(Activity act){
        //从系统服务中获取输入法管理器
        InputMethodManager imm = (InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()){//软键盘如果已经打开则关闭它
            imm.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void hideAllinputMethod(Activity act, View v){
        //从系统服务中获取输入法管理器
        InputMethodManager imm = (InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE);
        //关闭屏幕上的输入法软键盘
        imm.hideSoftInputFromWindow(v.getWindowToken(),0);
    }


}
