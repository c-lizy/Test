package com.example.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    protected Button but_post, but_get;
    private EditText edit_pg;
    private String url = "";
    private String url2 = " https://www.baidu.com/sugrec?prod=pc_his&from=pc_web&json=1&sid=36551_36465_37107_36413_36955_36918_36569_36804_37135_37055_26350_36862&hisdata=%5B%7B%22time%22%3A1659772180%2C%22kw%22%3A%22%E4%BD%A0%E5%A5%BD%22%7D%2C%7B%22time%22%3A1659772255%2C%22kw%22%3A%22%E4%B8%8D%E7%B3%8A%22%7D%2C%7B%22time%22%3A1659772619%2C%22kw%22%3A%22android%20studio%22%7D%2C%7B%22time%22%3A1659780106%2C%22kw%22%3A%22%E5%86%99%E5%B0%8F%E8%AF%B4%E7%9A%84%E7%BD%91%E7%AB%99%22%7D%2C%7B%22time%22%3A1659780203%2C%22kw%22%3A%22%E5%86%99%E6%97%A5%E8%AE%B0%22%7D%2C%7B%22time%22%3A1659780744%2C%22kw%22%3A%22%E8%85%BE%E8%AE%AF%E8%A7%86%E9%A2%91%22%7D%2C%7B%22time%22%3A1659790010%2C%22kw%22%3A%22mac%E7%94%B5%E8%84%91%E5%A3%81%E7%BA%B8%22%7D%2C%7B%22time%22%3A1659790038%2C%22kw%22%3A%22mac%E5%A3%81%E7%BA%B8%E9%AB%98%E6%B8%85%E5%85%A8%E5%B1%8F%22%7D%5D&_t=1660486616302&req=2&csor=0";


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
        edit_pg=findViewById(R.id.edit_pg);
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
    public void  get(){
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
                Log.i("onFailure", "====="+e.getMessage());
            }
            //回调成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Log.i("onResponse", "====="+res);
                edit_pg.setText(res);
            }
        });


    }

}