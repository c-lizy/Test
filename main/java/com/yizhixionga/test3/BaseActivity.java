package com.yizhixionga.test3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Author:lizy
 * time:2022/9/2 9:49
 * Description : BaseActivity
 **/
public class BaseActivity extends AppCompatActivity {
    Context mcontext=this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /*跳转方法*/
    public void intJump(Class cla){
        Intent intent = new Intent(mcontext,cla);
        startActivity(intent);
    }
}
