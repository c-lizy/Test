package com.yizhixionga.test3.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Author:lizy
 * time:2022/9/7 10:48
 * Description : ToastUtil
 **/
public class ToastUtil {
    public static void show(Context ctx, String desc) {
        Toast.makeText(ctx, desc, Toast.LENGTH_SHORT).show();
    }
}
