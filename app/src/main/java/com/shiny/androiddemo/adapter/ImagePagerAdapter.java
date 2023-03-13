package com.shiny.androiddemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import com.shiny.androiddemo.spinner.bean.Plant;

import java.util.ArrayList;
import java.util.List;

public class ImagePagerAdapter extends PagerAdapter {
    private final Context context;
    private final List<Plant> plants;
    private List<ImageView> imageViews = new ArrayList<>();

    public ImagePagerAdapter(Context context, List<Plant> plants) {
        this.context = context;
        this.plants = plants;
        for (Plant plant: plants) {
            ImageView view = new ImageView(context);
            view.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            view.setImageResource(plant.image);
            imageViews.add(view);
        }
    }

    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    // 实例化指定位置的页面，并将其添加到容器中
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // 添加一个view到container中，而后返回一个跟这个view可以关联起来的对象
        // 这个对象能够是view自身，也能是其余对象
        // 关键是在isViewFromObject可以将这个view和这个object关联起来
        ImageView item = imageViews.get(position);
        container.addView(item);
        return item;
    }

    // 从容器中销毁指定位置的页面
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(imageViews.get(position));
    }

    // 给 PageTabScript 设置标题栏
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return plants.get(position).name;
    }
}
