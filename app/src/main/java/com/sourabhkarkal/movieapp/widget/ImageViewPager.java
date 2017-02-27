package com.sourabhkarkal.movieapp.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sourabhkarkal.movieapp.R;
import com.sourabhkarkal.movieapp.utils.Constants;

import java.util.ArrayList;

/**
 * Created by sourabhkarkal on 26/02/17.
 *
 * A {@link ViewPager} that allows pseudo-infinite paging with a wrap-around effect
 * Used in class {@link com.sourabhkarkal.movieapp.fragment.MovieDetailFragment}
 */

public class ImageViewPager extends PagerAdapter {
    Context context;
    ArrayList<String> pathList;

    public ImageViewPager(Context context, ArrayList<String> pathList){
        this.context=context;
        this.pathList = pathList;
    }
    @Override
    public int getCount() {
        return pathList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        int padding = context.getResources().getDimensionPixelSize(R.dimen.small_margin);
        imageView.setPadding(padding, padding, padding, padding);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context)
                .load(Constants.IMAGE_BASE_PATH + pathList.get(position))
                .animate(android.R.anim.fade_in)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.movie_placeholder)
                .into(imageView);
        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}