package com.shiny.androiddemo.spinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.shiny.androiddemo.R;
import com.shiny.androiddemo.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpinnerIconActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "SpinnerIconActivity";

    private static final int[] iconArray = {R.drawable.flower, R.drawable.grass};
    private static final String[] titleArray = {"鲜花", "绿草"};

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_icon);

        // 声明一个映射对象，用于保存植物图标和名称的对应关系
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < iconArray.length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("icon", iconArray[i]);
            item.put("name", titleArray[i]);
            list.add(item);
        }

        SimpleAdapter plantAdapter = new SimpleAdapter(this, list, R.layout.item_select_simple, new String[]{"icon", "name"}, new int[]{R.id.iv_icon, R.id.tv_name});

        spinner = findViewById(R.id.spinner_icon);
        spinner.setAdapter(plantAdapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ToastUtil.show(this, "你选择的是" + titleArray[i]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}