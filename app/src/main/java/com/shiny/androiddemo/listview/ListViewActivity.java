package com.shiny.androiddemo.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.shiny.androiddemo.R;
import com.shiny.androiddemo.adapter.PlantBaseAdapter;
import com.shiny.androiddemo.spinner.bean.Plant;
import com.shiny.androiddemo.utils.ToastUtil;

import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private ListView lv_plant;
    private List<Plant> plants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        plants = Plant.getDefaultList();
        PlantBaseAdapter adapter = new PlantBaseAdapter(this, plants);
        lv_plant = findViewById(R.id.lv_plant);
        lv_plant.setAdapter(adapter);

        lv_plant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ToastUtil.show(ListViewActivity.this, "你选择的是：" + plants.get(i).name);
            }
        });
    }
}