package com.frontinelabs.rxsample.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frontinelabs.rxsample.Main3Activity;
import com.frontinelabs.rxsample.Main5Activity;
import com.frontinelabs.rxsample.model.DataSlider;
import com.github.piasy.biv.view.BigImageView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import java.util.ArrayList;

/**
 * Created by EBaba on 10/10/2017.
 */
public class SliderAdapter extends LoopPagerAdapter {

    private Context mContext;
    int mResource;
    LayoutInflater mLayoutInflater;
    private ArrayList<DataSlider> mSliderList;

    public SliderAdapter(Context context, int resource, RollPagerView viewPager, ArrayList<DataSlider>
            sliderList) {
        super(viewPager);
        mContext = context;
        mSliderList = sliderList;
        mResource = resource;
    }


    public View getView(ViewGroup container, final int position) {
        Log.i("RollViewPager", "getView:" + mSliderList.get(position).getImgUrl());
        Context context = container.getContext();

        mLayoutInflater = (LayoutInflater) context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        //convertView = mLayoutInflater.inflate(mResource, null);
        BigImageView view = new BigImageView(container.getContext());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("onClick",  mSliderList.get(position).getImgUrl());

                Intent intent = new Intent(mContext,
                        Main3Activity.class);
                mContext.startActivity(intent);
            }
        });
        //view.setScaleType(SimpleDraweeView.ScaleType.CENTER_CROP);
       /* GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(
                context.getResources()).setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY)
                .setProgressBarImage(
                        new ProgressBarDrawable()).build();
        view.setHierarchy(hierarchy);

        WindowManager wmDoodles = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display displayDoodles = wmDoodles.getDefaultDisplay();
        DisplayMetrics metricsDoodles = new DisplayMetrics();
        displayDoodles.getMetrics(metricsDoodles);
        int screenWidthDoodles = metricsDoodles.widthPixels;*/

        // int screenHeightDoodles = metricsDoodles.heightPixels;

        // int width = 1024;
        // int height = 600;

        /*float gcd = PublicMethod.aspectratio(Integer.parseInt(str_doodle_width),
                Integer.parseInt(str_doodle_height));
        float r1 = Integer.parseInt(str_doodle_width) / gcd;
        float r2 = Integer.parseInt(str_doodle_height) / gcd;
        //Logger.e("ratio", String.valueOf(r1 + " : " + r2));

        float resolution = screenWidthDoodles;
        if (r1 > r2) {

            final_width = resolution;
            final_height = Math.round(((resolution * r2) / r1));

            //Logger.e("ratioFinal", String.valueOf(final_width + " : " + final_height));
            // Log.e("ratioDP",
            // String.valueOf(convertPixelsToDp(resolution, this) +
            // " : "+convertPixelsToDp(((resolution*r2)/r1),
            // this)));

        } else {
            final_width = Math.round(((resolution * r2) / r1));
            final_height = resolution;

            //Logger.e("ratioFinal", String.valueOf((final_width + " : " + final_height)));
            // Log.e("ratioDP", String.valueOf(
            // convertPixelsToDp(((resolution*r1)/r2), this)+
            // " : "+convertPixelsToDp(resolution, this)));

        }
        view.setLayoutParams(new RelativeLayout.LayoutParams((int) final_width, (int) final_height));
        mLoopViewPager.setLayoutParams(new RelativeLayout.LayoutParams((int) final_width, (int) final_height));

        //view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
        // .LayoutParams.MATCH_PARENT));
        Logger.e("ImageURI", imgs[position]);*/
        //Uri uri = Uri.parse(mSliderList.get(position).getImgUrl());
        //view.setImageURI(uri);
        //Picasso.with(MainActivity.this).load(imgs[position]).into(view);

        view.showImage(Uri.parse(mSliderList.get(position).getImgUrl()));

        return view;
    }

    @Override
    public int getRealCount() {
        return mSliderList.size();
    }


}
