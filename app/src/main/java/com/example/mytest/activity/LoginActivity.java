package com.example.mytest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mytest.BaseActivity;
import com.example.mytest.R;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}