package com.shiny.androiddemo.spinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.shiny.androiddemo.R;
import com.shiny.androiddemo.utils.ToastUtil;

public class SpinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private final static String[] starArray = {"▯  竖条形", "○  圆形"};

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        spinner = findViewById(R.id.spinner);
        // 声明下拉列表的数组适配器
        ArrayAdapter<String> starAdapter = new ArrayAdapter<>(this, R.layout.item_select, starArray);
        spinner.setAdapter(starAdapter);
        // 默认选择第一项
        spinner.setSelection(0);
        // 给下拉框设置选择监听器，一单用户选择某一项，就会触发监听器的onItemSelected方法
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ToastUtil.show(this, "你选择的是" + starArray[i]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}