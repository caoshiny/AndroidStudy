package com.shiny.androiddemo.tabpageswitch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * @author shiny
 * @description Fragment 适配器
 * @date 2023/2/21
 */
public class MyFragmentPagerAdapter extends FragmentStateAdapter {
    List<Fragment> fragmentList = new LinkedList<>();
    public MyFragmentPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, List<Fragment> listFragment) {
        super(fragmentManager, lifecycle);
        this.fragmentList = listFragment;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}
