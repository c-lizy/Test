package com.yizhixionga.test3.activity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;

import com.yizhixionga.test3.BaseActivity;
import com.yizhixionga.test3.R;

import java.util.Calendar;

/**
 * Author:lizy
 * time:2022/9/2 11:03
 * Description : TimePickerActivity
 **/
public class TimePickerActivity extends BaseActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {

    private TimePicker Time_tp;
    private TextView tv_TimeTP;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_picker_activity);
        tv_TimeTP = findViewById(R.id.tv_TimeTP);
        Time_tp = findViewById(R.id.Time_tp);
        findViewById(R.id.btn_sure).setOnClickListener(this);
        findViewById(R.id.btn_time).setOnClickListener(this);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        String desc = String.format("您选择的时间是%d时%d分", i, i1);
        tv_TimeTP.setText(desc);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_time:
                //构建一个日历的实例，包含当前的时分秒
                Calendar calendar = Calendar.getInstance();
                //构建一个时间对话框
                TimePickerDialog dialog = new TimePickerDialog(this, this,
                        calendar.get(Calendar.DAY_OF_MONTH),
                        calendar.get(Calendar.MINUTE),
                        true);
                dialog.show();
                break;
            case R.id.btn_sure:
                String desc = String.format("您选择的时间是%d时%d分", Time_tp.getHour(), Time_tp.getMinute());
                tv_TimeTP.setText(desc);
                break;
        }
    }
}
