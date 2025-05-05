package com.example.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;



public class MyAdapter extends PagerAdapter {
    public int location;
    Context c;
    MyAdapter(Context c){
        this.c = c;
    }

    private int[] hkwimages = {R.drawable.hkwsun, R.drawable.hkwmon, R.drawable.hkwtue, R.drawable.hkwwed, R.drawable.hkwthu, R.drawable.hkwfri, R.drawable.hkwsat};
    private int[] tpwimages = {R.drawable.tpwsun, R.drawable.tpwmon, R.drawable.tpwtue, R.drawable.tpwwed, R.drawable.tpwthu, R.drawable.tpwfri, R.drawable.tpwsat};
    private int[] tkwimages = {R.drawable.tkwsun, R.drawable.tkwmon, R.drawable.tkwtue, R.drawable.tkwwed, R.drawable.tkwthu, R.drawable.tkwfri, R.drawable.tkwsat};

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==object);
    }

    @Override
    public int getCount() {
        return hkwimages.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.customlayout, container, false);
        ImageView iv = v.findViewById(R.id.imageView2);


        switch (location) {
            case 0:
                iv.setImageResource(hkwimages[position]);
                container.addView(v);
                return v;
            case 1:
                iv.setImageResource(tpwimages[position]);
                container.addView(v);
                return v;
            case 2:
                iv.setImageResource(tkwimages[position]);
                container.addView(v);
                return v;

        }
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

