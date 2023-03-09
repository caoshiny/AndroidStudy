package com.shiny.androiddemo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shiny.androiddemo.R;
import com.shiny.androiddemo.spinner.bean.Plant;

import java.util.List;

public class PlanetBaseAdapter extends BaseAdapter {
    private Context context;
    private List<Plant> plants;

    public PlanetBaseAdapter(Context context, List<Plant> plants) {
        this.context = context;
        this.plants = plants;
    }

    @Override
    public int getCount() {
        return plants.size();
    }

    @Override
    public Object getItem(int i) {
        return plants.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // 根据布局文件生成转换视图对象
        @SuppressLint("ViewHolder") View targetView = LayoutInflater.from(context).inflate(R.layout.plant_select, null);
        ImageView iv_icon = targetView.findViewById(R.id.iv_icon1);
        TextView tv_name = targetView.findViewById(R.id.tv_name);
        TextView tv_desc = targetView.findViewById(R.id.tv_desc);

        // 给控件绑定数据
        Plant plant = plants.get(i);
        iv_icon.setImageResource(plant.image);
        tv_name.setText(plant.name);
        tv_desc.setText(plant.des);

        return targetView;
    }
}
