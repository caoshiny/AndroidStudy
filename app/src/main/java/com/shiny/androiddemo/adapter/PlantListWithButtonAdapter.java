package com.shiny.androiddemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.shiny.androiddemo.R;
import com.shiny.androiddemo.spinner.bean.Plant;
import com.shiny.androiddemo.utils.ToastUtil;

import java.util.List;

public class PlantListWithButtonAdapter extends BaseAdapter {
    private Context context;
    private List<Plant> plants;

    public PlantListWithButtonAdapter(Context context, List<Plant> plants) {
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
        PlantListWithButtonAdapter.ViewHolder holder;
        if (view == null) {
            // 根据布局文件生成转换视图对象
            view = LayoutInflater.from(context).inflate(R.layout.plant_select_with_button, null);
            holder = new PlantListWithButtonAdapter.ViewHolder();
            holder.iv_icon = view.findViewById(R.id.iv_icon);
            holder.tv_name = view.findViewById(R.id.tv_name);
            holder.tv_desc = view.findViewById(R.id.tv_desc);
            holder.btn_operate = view.findViewById(R.id.btn_operate);
            // 将视图持有者保存到转换视图中
            view.setTag(holder);
        } else {
            holder = (PlantListWithButtonAdapter.ViewHolder) view.getTag();
        }

        // 给控件绑定数据
        Plant plant = plants.get(i);
        holder.iv_icon.setImageResource(plant.image);
        holder.tv_name.setText(plant.name);
        holder.tv_desc.setText(plant.des);
        holder.btn_operate.setOnClickListener(view1 -> {
            ToastUtil.show(context, "按钮被点击了" + plant.name);
        });

        return view;
    }

    // 控件复用
    public final class ViewHolder {
        public ImageView iv_icon;
        public TextView tv_name;
        public TextView tv_desc;
        public ImageButton btn_operate;
    }
}
