package com.yizhixionga.test3.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.yizhixionga.test3.BaseActivity;
import com.yizhixionga.test3.R;

import java.util.Calendar;

/**
 * Author:lizy
 * time:2022/9/2 9:56
 * Description : DatePickerActivity
 **/
public class DatePickerActivity extends BaseActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private DatePicker dp_date;
    private TextView tv_dateDP;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_picker_activity);
        dp_date = findViewById(R.id.dp_date);
        tv_dateDP = findViewById(R.id.tv_dateDP);
        findViewById(R.id.btn_date).setOnClickListener(this);
        findViewById(R.id.btn_sure).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_date:
                //获取一个日历的实例，里面包含年月日
                Calendar calendar = Calendar.getInstance();
                //构建一个日期对话框，
                DatePickerDialog dialog = new DatePickerDialog(this, this,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
                break;
            case R.id.btn_sure:
                String desc = String.format("您选择的日期是%d年%d月%d日", dp_date.getYear(), dp_date.getMonth() + 1, dp_date.getDayOfMonth());
                tv_dateDP.setText(desc);
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        String desc = String.format("您选择的日期是%d年%d月%d日", i, i1 + 1, i2);
        tv_dateDP.setText(desc);
    }
}
