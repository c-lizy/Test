package com.yizhixionga.test3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yizhixionga.test3.activity.DatePickerActivity;
import com.yizhixionga.test3.activity.LoginActivity;
import com.yizhixionga.test3.activity.ShareReadActivity;
import com.yizhixionga.test3.activity.ShareWriteActivity;
import com.yizhixionga.test3.activity.TimePickerActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_date, btn_TiMe, btn_login, btn_share_write, btn_share_read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inition();
    }

    //初始化
    private void inition() {
        btn_date = findViewById(R.id.btn_date);
        btn_TiMe = findViewById(R.id.btn_TiMe);
        btn_login = findViewById(R.id.btn_login);
        btn_share_write = findViewById(R.id.btn_share_write);
        btn_share_read = findViewById(R.id.btn_share_read);

        btn_share_read.setOnClickListener(this);
        btn_share_write.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_date.setOnClickListener(this);
        btn_TiMe.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_date:
                intJump(DatePickerActivity.class);
                break;
            case R.id.btn_TiMe:
                intJump(TimePickerActivity.class);
                break;
            case R.id.btn_login:
                intJump(LoginActivity.class);
                break;
            case R.id.btn_share_write:
                intJump(ShareWriteActivity.class);
                break;
            case R.id.btn_share_read:
                intJump(ShareReadActivity.class);
                break;
        }

    }
}