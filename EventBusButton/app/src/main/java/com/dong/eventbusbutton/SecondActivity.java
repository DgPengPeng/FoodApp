package com.dong.eventbusbutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et;
    private Button btn;
    private EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        et = (EditText) findViewById(R.id.second_et);
        btn = (Button) findViewById(R.id.second_btn);
        btn.setOnClickListener(this);
        // 初始化 EventBus 对象
        eventBus = EventBus.getDefault();
    }

    @Override
    public void onClick(View view) {
        String data = et.getText().toString();
        Bean bean = new Bean();
        bean.setData(data);
        // 利用 EventBus 传值 就一个post方法
        eventBus.post(bean);
        finish();
    }
}
