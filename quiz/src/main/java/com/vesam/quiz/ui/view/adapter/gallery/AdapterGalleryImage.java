package com.vesam.quiz.ui.view.adapter.gallery;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.vesam.quiz.R;
import com.vesam.quiz.utils.tools.GlideTools;

import java.util.ArrayList;
import java.util.List;

public class AdapterGalleryImage extends BaseAdapter {
    private final Context context;
    private final GlideTools glideTools;
    private final List<String> imageList=new ArrayList<>();


    public AdapterGalleryImage(Context context, GlideTools glideTools) {
        this.context = context;
        this.glideTools = glideTools;
    }



    public int getCount() {
        return imageList.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    @NonNull
    public View getView(int index, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(context);
        String url = imageList.get(index);
        if (!TextUtils.isEmpty(url))
            glideTools.displayHome(imageView,url);
        else
            imageView.setImageResource(R.drawable.ic_photo);
        imageView.setLayoutParams(new Gallery.LayoutParams(100, 100));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    public void updateList(List<String> imageList ) {
        this.imageList.clear();
        this.imageList.addAll(imageList);
        notifyDataSetChanged();
    }


}