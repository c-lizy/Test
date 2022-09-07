package com.yizhixionga.test3.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.yizhixionga.test3.BaseActivity;
import com.yizhixionga.test3.R;

import java.util.Random;

public class ForgetLoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_newpsw, et_psw_agin, et_psw_code;
    private String mVerifyCode;//验证码
    private String mPhone;//手机号码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_login);
        findViewById(R.id.get_code).setOnClickListener(this);
        mPhone = getIntent().getStringExtra("phone");
        findViewById(R.id.get_code).setOnClickListener(this);
        findViewById(R.id.btn_ok).setOnClickListener(this);
        et_newpsw = findViewById(R.id.et_newpsw);
        et_psw_agin = findViewById(R.id.et_psw_agin);
        et_psw_code = findViewById(R.id.et_psw_code);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_code:
                if (mPhone == null || mPhone.length() < 11) {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                //随机生成一个六位数的验证码
                mVerifyCode = String.format("%06d", new Random().nextInt(999999));
                //提示框弹出，以便客户记住验证码
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("请记住验证码")
                        .setMessage("手机号" + mPhone + "\n" + "本次验证码为" + mVerifyCode + "\n" + "请返回输入验证码登录")
                        .setPositiveButton("好的", null);
                AlertDialog dialog = builder.create();
                dialog.show();//显示提醒对话框
                break;
            case R.id.btn_ok:
                String first = et_newpsw.getText().toString();
                String second = et_psw_agin.getText().toString();
                if (first.length() < 6 | second.length() < 6) {
                    Toast.makeText(this, "请输入正确的密码", Toast.LENGTH_SHORT).show();
                }
                if (!first.equals(second)) {
                    Toast.makeText(this, "两次密码不一致，请确认", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!et_psw_code.getText().toString().equals(mVerifyCode)) {
                    Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(this, "密码修改成功", Toast.LENGTH_SHORT).show();
                    //把修改好的密码返回上个页面
                    Intent intent = new Intent();
                    intent.putExtra("new_psw", first);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }

                break;
        }

    }
}