package com.example.mytest.activity;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;

import com.example.mytest.BaseActivity;
import com.example.mytest.R;

public class StartActivitty extends BaseActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.start_activity);
    }
}
