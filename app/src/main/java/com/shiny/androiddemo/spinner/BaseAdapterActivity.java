package com.shiny.androiddemo.spinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.shiny.androiddemo.R;
import com.shiny.androiddemo.adapter.PlantBaseAdapter;
import com.shiny.androiddemo.spinner.bean.Plant;
import com.shiny.androiddemo.utils.ToastUtil;

import java.util.List;

public class BaseAdapterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private List<Plant> plants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapter);

        spinner = findViewById(R.id.sp_plant);
        plants = Plant.getDefaultList();
        PlantBaseAdapter adapter = new PlantBaseAdapter(this, plants);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ToastUtil.show(this, "你选择的是：" + plants.get(i).name);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}