package com.yizhixionga.test3.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import com.yizhixionga.test3.BaseActivity;
import com.yizhixionga.test3.R;
import java.util.Map;

public class ShareReadActivity extends BaseActivity {

    private TextView tv_share_read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_read);
        tv_share_read=findViewById(R.id.tv_share_read);
        ReadShare();
    }

    //读取公共参数中的数据
    public void ReadShare(){
        SharedPreferences shared = getSharedPreferences("share",MODE_PRIVATE);
        String desc = "共享参数信息如下：\n";
        //获取共享参数保存的所有映射配对信息
        Map<String,Object> map = (Map<String, Object>) shared.getAll();
        //遍历该配对对象，并以文字形式展示
        for (Map.Entry<String,Object> item : map.entrySet()){
            String key = item.getKey();
            Object value = item.getValue();
            if (value instanceof String){
                desc = String.format("%s\n %s的取值为%s",desc,key,shared.getString(key,""));
            }else if (value instanceof Integer){
                desc = String.format("%s\n %s的取值为%d",desc,key,shared.getInt(key,0));
            }else if (value instanceof Boolean){
                desc = String.format("%s\n %s的取值为%s",desc,key,shared.getBoolean(key,false));
            }else if (value instanceof Float){
                desc = String.format("%s \n %s的取值为%f",desc,key,shared.getFloat(key,0.0f));
            }else if (value instanceof Long){
                desc = String.format("%s\n %s的取值为%d",desc,key,shared.getLong(key,0L));
            }else {
                desc = "%s\n 参数%s的参数类型为未知类型";
            }
            if (map.size()<0){
                desc = "共享参数的内容为空";
            }
            tv_share_read.setText(desc);
        }
    }

}