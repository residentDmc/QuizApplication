package com.vesam.quiz.ui.view.adapter.gallery;

import android.content.Context;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.vesam.quiz.R;
import com.vesam.quiz.interfaces.OnClickListenerAny;
import com.vesam.quiz.utils.tools.GlideTools;
import com.vesam.quiz.utils.tools.HandelErrorTools;

import java.util.List;


public class AdapterSlidingImage extends PagerAdapter {


    private List<String> imageList;
    private LayoutInflater inflater;
    private Boolean clickable;
    private Boolean zoomable;
    private GlideTools glideTools;
    private HandelErrorTools handelErrorTools;
    private OnClickListenerAny onClickListenerAny;

    public AdapterSlidingImage() {
    }


    public void setAdapterSlidingImage(Context context, List<String> imageList, Boolean clickable, GlideTools glideTools, HandelErrorTools handelErrorTools, Boolean zoomable) {
        this.imageList = imageList;
        this.clickable = clickable;
        this.glideTools = glideTools;
        this.handelErrorTools = handelErrorTools;
        this.zoomable = zoomable;
        inflater = LayoutInflater.from(context);
    }

    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


    public int getCount() {
        return imageList.size();
    }

    public void setOnclick(OnClickListenerAny onclick) {
        this.onClickListenerAny = onclick;
    }

    @NonNull

    public Object instantiateItem(@NonNull ViewGroup view, int position) {
        try {
            View imageLayout;
            ImageView imageView;
            SubsamplingScaleImageView imageViewZoomable;
            if (zoomable) {
                    imageLayout = inflater.inflate(R.layout.item_slidingimages_zoomable, view, false);
                imageViewZoomable = imageLayout.findViewById(R.id.image);
                String url = imageList.get(position);
                if (!TextUtils.isEmpty(url))
                    glideTools.displayImageSliderZoom(imageViewZoomable, url);
                else imageViewZoomable.setImage(ImageSource.resource(R.drawable.ic_no_pic_detail));

                imageViewZoomable.setMaxScale(1000);
                imageViewZoomable.setOnClickListener(v -> onClickListenerAny.onClickListener(imageLayout));
            } else {
                imageLayout = inflater.inflate(R.layout.item_slidingimages, view, false);
                imageView = imageLayout.findViewById(R.id.image);
                String url = imageList.get(position);
                if (!TextUtils.isEmpty(url))
                    glideTools.displayImageSliderDefault(imageView, url);
                else
                    imageView.setImageResource(R.drawable.ic_no_pic_detail);

                if (clickable) imageView.setOnClickListener(v -> showFullScreen(position));
            }
            view.addView(imageLayout);
            return imageLayout;
        } catch (Exception e) {
            handelErrorTools.handelError(e);
        }
        return null;
    }

    private void showFullScreen(int position) {


    }


    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }


    public void restoreState(Parcelable state, ClassLoader loader) {
    }


    public Parcelable saveState() {
        return null;
    }


}