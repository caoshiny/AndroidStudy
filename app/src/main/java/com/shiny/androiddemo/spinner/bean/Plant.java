package com.shiny.androiddemo.spinner.bean;

import com.shiny.androiddemo.R;

import java.util.ArrayList;
import java.util.List;

public class Plant {
    public int image;
    public String name;
    public String des;

    public Plant(int image, String name, String des) {
        this.image = image;
        this.name = name;
        this.des = des;
    }

    private static final int[] plantImageArr = {R.drawable.flower, R.drawable.grass};
    private static final String[] plantNameArr = {"鲜花", "绿草"};
    private static final String[] descArr = {"鲜花好美！", "绿草好绿！"};

    public static List<Plant> getDefaultList() {
        List<Plant> plantList = new ArrayList<>();
        for (int i = 0; i < plantImageArr.length; i++) {
            plantList.add(new Plant(plantImageArr[i], plantNameArr[i], descArr[i]));
        }
        return plantList;
    }
}
