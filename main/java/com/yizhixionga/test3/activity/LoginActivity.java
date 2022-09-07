package com.yizhixionga.test3.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.yizhixionga.test3.BaseActivity;
import com.yizhixionga.test3.R;
import com.yizhixionga.test3.util.ViewUtil;

import java.util.Random;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private String TAG = "LoginActivity";
    private RadioGroup rg_login;
    private RadioButton rb_pswLogin, rb_verifyCodeLogin;
    private EditText et_phone, et_psw;
    private TextView tv_psw;
    private Button forget_psw;
    private CheckBox remember_psw;

    private String mPassword = "666666"; // 默认密码
    private boolean isRemember;//是否记住密码
    private int mRequestCode = 0; // 跳转页面时的请求代码
    private String mVerifyCode; // 验证码
    private SharedPreferences mShared;//声明一个共享参数对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inition();
    }

    //    初始化
    private void inition() {
        rg_login = findViewById(R.id.rg_login);
        // 给rg_login设置单选监听器
        rg_login.setOnCheckedChangeListener(new RadioListener());
        rb_verifyCodeLogin = findViewById(R.id.rb_verifyCodeLogin);
        rb_pswLogin = findViewById(R.id.rb_pswLogin);
        et_psw = findViewById(R.id.et_psw);
        et_phone = findViewById(R.id.et_phone);
        tv_psw = findViewById(R.id.tv_psw);
        forget_psw = findViewById(R.id.forget_psw);
        forget_psw.setOnClickListener(this);
        remember_psw = findViewById(R.id.remember_psw);
        //给remember_psw设置勾选监听器
        remember_psw.setOnCheckedChangeListener(new CheckListener());
        findViewById(R.id.btn_login).setOnClickListener(this);
        //给et_psw、et_phone添加文本变更监听器
        et_psw.addTextChangedListener(new HideTextWatcher(et_psw, 6));
        et_phone.addTextChangedListener(new HideTextWatcher(et_phone, 11));
        //从login——activity获取共享参数对象
        mShared = getSharedPreferences("activity_login", MODE_PRIVATE);
        //获取共享参数保存的手机号
        String phone = mShared.getString("phone", "");
        //获取共享参数保存的密码
        String password = mShared.getString("psw", "");
        et_phone.setText(phone);//往手机号码编辑框填写上次保存的手机号
        et_psw.setText(password);//往密码编辑框填写上次保存的密码
    }

    //点击事件监听
    @Override
    public void onClick(View view) {
        String phone = et_phone.getText().toString();
        switch (view.getId()) {
            case R.id.forget_psw://忘记密码点击
                if (phone.length() < 11) {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rb_pswLogin.isChecked()) {//密码方式登录，携带手机号跳转到找回密码界面
                    Intent intent = new Intent(this, ForgetLoginActivity.class);
                    intent.putExtra("phone", phone);
                    startActivityForResult(intent, mRequestCode);//携带意图返回上一个页面
                } else if (rb_verifyCodeLogin.isChecked()) {//验证码方式登录，点击会生成验证码
                    //随机生成一个六位数的验证码
                    mVerifyCode = String.format("%06d", new Random().nextInt(999999));
                    //提示框弹出，以便客户记住验证码
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("请记住验证码")
                            .setMessage("手机号" + phone + "\n" + "本次验证码为" + mVerifyCode + "\n" + "请返回输入验证码登录")
                            .setPositiveButton("好的", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();//显示提醒对话框
                }
                break;
            case R.id.btn_login:
                if (phone.length() < 11) {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rb_pswLogin.isChecked()) {
                    if (!et_psw.getText().toString().equals(mPassword)) {
                        Toast.makeText(this, "请输入正确的密码", Toast.LENGTH_SHORT).show();
                    } else {
                        LoginSuccess();
                    }
                } else if (rb_verifyCodeLogin.isChecked()) {
                    if (!et_psw.getText().toString().equals(mVerifyCode)) {
                        Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
                    } else {
                        LoginSuccess();
                    }
                }
                break;
        }
    }

    //从下一个页面携带参数返回当前页面时触发
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == mRequestCode && data != null) {
            //用户已修改密码，密码需更新
            mPassword = data.getStringExtra("new_psw");
        }
    }

    // 从修改密码页面返回登录页面，要清空密码的输入框
    @Override
    protected void onRestart() {
        super.onRestart();
        et_psw.setText("");
    }

    //校验通过，登陆成功
    private void LoginSuccess() {
        String desc = String.format("您的手机号码是：%s,\n恭喜您成功登录，点击确定返回上个页面。", et_phone.getText().toString());
        //弹出对话框提示用户登陆成功
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("登陆成功")
                .setMessage(desc)
                .setPositiveButton("确定返回", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("我再想想", null);
        AlertDialog dialog = builder.create();
        dialog.show();
        if (isRemember) {
            SharedPreferences.Editor editor = mShared.edit();//获取编辑器的对象
            editor.putString("phone", et_phone.getText().toString());
            editor.putString("psw", et_psw.getText().toString());
            editor.commit();//提交
        }
    }

    //定义一个编辑框监听器，在输入文本达到指定长度时自动隐藏输入法
    private class HideTextWatcher implements TextWatcher {
        private EditText mView;//声明一个编辑框对象
        private int mMaxlenght;//声明一个最大长度变量

        public HideTextWatcher(EditText v, int maxlenght) {
            super();
            mView = v;
            mMaxlenght = maxlenght;
        }

        //在编辑框的输入文本变化前触发
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        //在编辑框的输入文本变化时触发
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        //在编辑框的输入文本变化后触发
        @Override
        public void afterTextChanged(Editable editable) {
            String str = editable.toString();//获取已输入的文本字符串
            //输入文本达到11位（如手机号码），或者达到6位（如密码）时关闭输入法
            if ((str.length() == 11 && mMaxlenght == 11) || (str.length() == 6 && mMaxlenght == 6)) {
                ViewUtil.hideAllinputMethod(LoginActivity.this, mView);
            }
        }
    }

    //是否记住密码的勾选监听器
    private class CheckListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (compoundButton.getId() == R.id.remember_psw) {
                isRemember = b;
            }
        }
    }

    //定义登录方式的单选监听器
    private class RadioListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
            if (checkId == R.id.rb_pswLogin) {
                tv_psw.setText(R.string.psw_login);
                et_psw.setHint(R.string.ple_psw);
                forget_psw.setText(R.string.forget_psw);
                remember_psw.setVisibility(View.VISIBLE);
            } else if (checkId == R.id.rb_verifyCodeLogin) {
                tv_psw.setText("    验证码：");
                et_psw.setHint("请输入验证码");
                forget_psw.setText("获取验证码");
                remember_psw.setVisibility(View.INVISIBLE);
            }
        }
    }
}