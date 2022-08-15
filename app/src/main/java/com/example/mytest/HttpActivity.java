package com.example.mytest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpActivity extends BaseActivity implements View.OnClickListener {
    protected Button but_post, but_get;
    private EditText edit_pg;
    private String url = "";
    private String url2 = " https://blackhole-m.m.jd.com/getinfo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /*初始化控件*/
    private void init() {
        but_post = findViewById(R.id.btn_post);
        but_get = findViewById(R.id.btn_get);
        edit_pg = findViewById(R.id.edit_pg);
        but_post.setOnClickListener(this);
        but_get.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_post:
//                int_jump();
                post();
                break;
            case R.id.btn_get:
//                int_jump();
                get();
                break;
        }
    }

    /*异步post请求*/
    public void post() {
        //第一步：创建OKHttpClient
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        //第二步：创建RequestBody（Json表达）
        Map map = new HashMap();
        map.put("username", "13612345678");
        map.put("password", "405353");
        JSONObject jsonObject = new JSONObject(map);
        String jsonStr = jsonObject.toString();
        RequestBody requestBodyJson = RequestBody.create(MediaType.parse("application/json;charset:UTF-8"), jsonStr);

        //第三步：创建Request
        Request request = new Request.Builder()
                .url(url)
                .addHeader("ContentType", "application/json;charset:UTF-8")
                .post(requestBodyJson)
                .build();
        //第四步：创建Call回调对象
        final Call call = client.newCall(request);
        //第五步：发起请求
        call.enqueue(new Callback() {
            //回调失败
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("onFailure", "=====" + e.getMessage());
            }

            //回调成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("onResponse", "=====" + response.body().string());
            }
        });

    }

    /*异步get请求*/
    public void get() {
        //第一步：创建OKHttpClient
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        //第二步：创建Request
        Request request = new Request.Builder()
                .url(url2)
                .get()
                .build();
        //第三步：创建Call回调对象
        final Call call = client.newCall(request);
        //第四步：异步get请求
        call.enqueue(new Callback() {
            //回调失败
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("onFailure", "=====" + e.getMessage());
            }

            //回调成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Log.i("onResponse", "=====" + res);
                edit_pg.setText(res);
            }
        });


    }

}
