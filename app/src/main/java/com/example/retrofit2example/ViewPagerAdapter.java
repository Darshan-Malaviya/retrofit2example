package com.example.retrofit2example;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import com.squareup.picasso.Picasso;
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.DataSource;
//import com.bumptech.glide.load.engine.GlideException;
//import com.bumptech.glide.request.RequestListener;
//import com.bumptech.glide.request.RequestOptions;
//import com.bumptech.glide.request.target.Target;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private String[] imageurls;

    ViewPagerAdapter(Context context,String[] imageurls){
        this.context = context;
        this.imageurls = imageurls;
    }
    @Override
    public int getCount() {
        return imageurls.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.getMaxHeight();

        Picasso.get()
                .load(imageurls[position])
                .fit()
                .into(imageView);
        container.addView(imageView);
//        Glide.with(context).load(imageurls[position]).apply(new RequestOptions().override(200,600)).fitCenter().listener(new RequestListener<Drawable>() {
//            @Override
//            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                Log.e("Glid Error ",e.getMessage());
//                return false;
//            }
//
//            @Override
//            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                Log.e("Glide Complete ","Complete");
//                return false;
//            }
//        }).into(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


}
