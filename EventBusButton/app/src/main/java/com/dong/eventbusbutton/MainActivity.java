package com.dong.eventbusbutton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * EventBus 使用守则
 * 1.把想要穿的值写进一个实体类中
 * 2.在传值的类中,对EventBus进行初始化 并且调用post方法进行传值
 * 3.在目标类(接收值的类中){
 *     1.注册EventBus (如果在Activity中注册,写在onCreate生命周期中)
 *                    (如果在Fragment中注册,写在onAttach生命周期中)
 *     2.注册EventBus (注销的方法必须和注册的生命周期相互对应,也就是说Activity中写在onDestroy)
 *                    (在fragment中 写在onDetach)
 *     3.写一个方法来接收值  方法上面加入 注解:标明线程  一般都用这个(@Subscribe(threadMode = ThreadMode.MAIN))
 *     !!!!!!!!******!!!!!
 *     使用EventBus的大忌 :目标类必须已经存在
 * }
 */

public class MainActivity extends AppCompatActivity  {


    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);


//        et = (EditText) findViewById(R.id.main_et);
//        findViewById(R.id.main_btn).setOnClickListener(this);
//        // 注册EventBus
//        EventBus.getDefault().register(this);
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        //解除注册
//        EventBus.getDefault().unregister(this);
//    }
//
//    // 这句话  叫做 注解
//    // 这句话代表 运行在主线程中
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void getData(Bean bean){
//        et.setText(bean.getData());
//    }
//
//    @Override
//    public void onClick(View view) {
//        Intent intent = new Intent(this,SecondActivity.class);
//        startActivity(intent);
//    }
}
