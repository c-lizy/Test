package com.example.mytest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    /*跳转Intent*/
    public void int_jump(Class cla){
        Intent intent = new Intent(mContext,cla);
        startActivity(intent);
    }
    /*跳转方法*/
    public void intJump(Class cla){
        Intent intent = new Intent(mContext,cla);
        startActivity(intent);
    }

}
