package com.example.retrofit2example;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

public class HingeAnimation implements ViewPager.PageTransformer {
    @Override
    public void transformPage(@NonNull View page, float position) {
        page.setTranslationX(-position*page.getWidth());
        page.setPivotX(0);
        page.setPivotY(0);

        if(position < -1)
        {
            page.setAlpha(0);
        }
        else if(position <= 0)
        {
            page.setRotation(90*Math.abs(position));
            page.setAlpha(1-Math.abs(position));
        }
        else if (position <= 1)
        {
            page.setRotation(0);
            page.setAlpha(1);
        }
        else
        {
            page.setAlpha(0);
        }
    }
}
