package com.shiny.androiddemo.tabpageswitch;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.shiny.androiddemo.R;

import java.util.LinkedList;
import java.util.List;

/**
 * @author shiny
 * @description ViewPager2 + Fragment 实现tab页面切换
 * @date 2023/2/21
 */
public class MainPage extends AppCompatActivity implements View.OnClickListener {
    private ViewPager2 viewPager;
    Button[] buttons = new Button[4];
    int current = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_tab_page);

        buttons[0] = findViewById(R.id.index);
        buttons[0].setOnClickListener(this);
        buttons[1] = findViewById(R.id.info);
        buttons[1].setOnClickListener(this);
        buttons[2] = findViewById(R.id.note);
        buttons[2].setOnClickListener(this);
        buttons[3] = findViewById(R.id.mine);
        buttons[3].setOnClickListener(this);
        initViewPager();
    }

    private void initViewPager() {
        viewPager = findViewById(R.id.viewPager);
        List<Fragment> fragmentList = new LinkedList<>();
        fragmentList.add(BlankFragment.newInstance("home"));
        fragmentList.add(BlankFragment.newInstance("info"));
        fragmentList.add(BlankFragment.newInstance("note"));
        fragmentList.add(BlankFragment.newInstance("mine"));
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), getLifecycle(), fragmentList);
        viewPager.setAdapter(myFragmentPagerAdapter);
        viewPager.setUserInputEnabled(false);

        // 监听Fragment的界面变化
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                // 其中position为当前Fragment索引
                changePager(position);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void changePager(int position) {
        // 原本位置按钮变为透明
        buttons[current].setTextColor(Color.parseColor("#FFFFFF"));

        // 切换位置按钮变为红色
        current = position;
        switch (position) {
            case 0:
                buttons[current].setTextColor(Color.parseColor("#FC5531"));
                break;
            case 1:
                buttons[current].setTextColor(Color.parseColor("#FC5531"));
                break;
            case 2:
                buttons[current].setTextColor(Color.parseColor("#FC5531"));
                break;
            case 3:
                buttons[current].setTextColor(Color.parseColor("#FC5531"));
                break;
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.index:
                changePager(0);
                viewPager.setCurrentItem(0);
                break;
            case R.id.info:
                changePager(1);
                viewPager.setCurrentItem(1);
                break;
            case R.id.note:
                changePager(2);
                viewPager.setCurrentItem(2);
                break;
            case R.id.mine:
                changePager(3);
                viewPager.setCurrentItem(3);
                break;
        }
    }
}
