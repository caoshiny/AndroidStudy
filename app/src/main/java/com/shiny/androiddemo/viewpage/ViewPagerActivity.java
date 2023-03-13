package com.shiny.androiddemo.viewpage;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import com.shiny.androiddemo.R;
import com.shiny.androiddemo.adapter.ImagePagerAdapter;
import com.shiny.androiddemo.spinner.bean.Plant;
import com.shiny.androiddemo.utils.ToastUtil;

import java.util.List;

/**
 * @author shiny
 * @description viewPager + PagerTabStrip
 * @date 2023/3/13
 */
public class ViewPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ViewPager vp_content;
    private PagerTabStrip pst_tab;
    private List<Plant> plants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        pst_tab = findViewById(R.id.pts_tab);
        pst_tab.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        pst_tab.setTextColor(Color.BLACK);

        vp_content = findViewById(R.id.vp_content);
        plants = Plant.getDefaultList();
        ImagePagerAdapter adapter = new ImagePagerAdapter(this, plants);
        vp_content.setAdapter(adapter);
        vp_content.addOnPageChangeListener(this);
    }

    /**
     * 翻页过程中触发
     * @param position 当前页面序号
     * @param positionOffset 页面偏移百分比，取值0到1
     * @param positionOffsetPixels 页面偏移距离
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 翻页结束后触发
     * @param position 当前滑倒了第几页
     */
    @Override
    public void onPageSelected(int position) {
        ToastUtil.show(this, plants.get(position).name);
    }

    /**
     * 翻页状态改变时触发
     * @param state 0 - 静止  1 - 滑动   2 - 滑动完毕
     *              状态值依次变化是： 正在滑动 -> 滑动完毕 -> 静止
     */
    @Override
    public void onPageScrollStateChanged(int state) {

    }
}