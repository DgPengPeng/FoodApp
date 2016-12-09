package com.dong.foodsect.activity;


import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dong.foodsect.R;
import com.dong.foodsect.fragment.LibraryFragment;
import com.dong.foodsect.fragment.MyFragment;
import com.dong.foodsect.fragment.ShopFragment;

public class MainActivity extends BaseActivity {

    private RadioGroup mainGroup;
    private RadioButton libraryRb, shopRb, myRb;
    private FrameLayout mainFrameLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    void initView() {
        mainGroup = bindView(R.id.rg_main);
        libraryRb = bindView(R.id.rb_main_library);
        shopRb = bindView(R.id.rb_main_shop_eat);
        myRb = bindView(R.id.rb_main_my);
        mainFrameLayout = bindView(R.id.main_frameLayout);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frameLayout, LibraryFragment.newInstance());
        fragmentTransaction.commit();
    }

    @Override
    void initData() {
        setRadioGroup();
        detailtoMyFragment();
    }

    private void setRadioGroup() {
        mainGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (i) {
                    case R.id.rb_main_library:
                        fragmentTransaction.replace(R.id.main_frameLayout, LibraryFragment.newInstance());
                        break;
                    case R.id.rb_main_shop_eat:
                        fragmentTransaction.replace(R.id.main_frameLayout, ShopFragment.newInstance());
                        break;
                    case R.id.rb_main_my:
                        fragmentTransaction.replace(R.id.main_frameLayout, MyFragment.newInstance());
                        break;
                    default:
                        break;
                }
                fragmentTransaction.commit();
            }


        });
    }

    private void detailtoMyFragment() {
        int id = getIntent().getIntExtra("id",0);
        if (id == 1){
            mainGroup.check(R.id.rb_main_my);
        }else if (id== 2){
            mainGroup.check(R.id.rb_main_my);
        }else if (id == 3){
            mainGroup.check(R.id.rb_main_library);
        }
    }
}
