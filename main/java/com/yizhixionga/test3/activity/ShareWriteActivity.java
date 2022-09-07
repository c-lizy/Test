package com.yizhixionga.test3.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.yizhixionga.test3.BaseActivity;
import com.yizhixionga.test3.R;
import com.yizhixionga.test3.util.DateUtil;
import com.yizhixionga.test3.util.ToastUtil;

public class ShareWriteActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private SharedPreferences shared;
    private EditText ed_name, ed_age, ed_height, ed_weight;
    private Boolean isMarriage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_write);
        ed_name = findViewById(R.id.ed_share_name);
        ed_age = findViewById(R.id.ed_share_age);
        ed_height = findViewById(R.id.ed_share_height);
        ed_weight = findViewById(R.id.ed_share_weight);
        findViewById(R.id.btn_share_write).setOnClickListener(this);
        CheckBox ck_share = findViewById(R.id.checkbox_share_write);
        ck_share.setOnCheckedChangeListener(this);
        // 从share.xml中获取共享参数对象,第一个参数是文件名，第二个参数是操作模式，MODE_PRIVATE表示私有模式
        shared = getSharedPreferences("share", MODE_PRIVATE);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_share_write) {
            String name = ed_name.getText().toString();
            String age = ed_age.getText().toString();
            String height = ed_height.getText().toString();
            String weight = ed_weight.getText().toString();
            if (TextUtils.isEmpty(name)) {
                ToastUtil.show(this,"请先输入姓名");
                return;
            }
            if (TextUtils.isEmpty(age)) {
                ToastUtil.show(this,"请先输入年龄");
                return;
            }
            if (TextUtils.isEmpty(height)) {
                ToastUtil.show(this,"请先输入身高");
                return;
            }
            if (TextUtils.isEmpty(weight)) {
                ToastUtil.show(this, "请先输入体重");
                return;
            }

            SharedPreferences.Editor editor = shared.edit();
            editor.putString("name", name);
            editor.putString("age", age);
            editor.putString("height", height);
            editor.putString("weight", weight);
            editor.putBoolean("marriage", isMarriage);
            editor.putString("update_time", DateUtil.getNowDateTime("yy-MM-dd HH-mm-ss"));
            editor.commit();
            ToastUtil.show(this, "数据已保存");
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        isMarriage = b;
    }
}