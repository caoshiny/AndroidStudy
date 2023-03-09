package com.shiny.androiddemo.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.shiny.androiddemo.R;
import com.shiny.androiddemo.adapter.PlantListWithButtonAdapter;
import com.shiny.androiddemo.spinner.bean.Plant;
import com.shiny.androiddemo.utils.ToastUtil;

import java.util.List;

public class ListViewFocusActivity extends AppCompatActivity {

    private ListView plantListView;
    private List<Plant> plants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_focus);
        plantListView = findViewById(R.id.lv_plant);
        plants = Plant.getDefaultList();
        PlantListWithButtonAdapter adapter = new PlantListWithButtonAdapter(this, plants);
        plantListView.setAdapter(adapter);

        plantListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ToastUtil.show(ListViewFocusActivity.this, "条目被点击了" + plants.get(i).name);
            }
        });
    }
}