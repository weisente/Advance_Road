package example.weisente.top.baselibrary.recycleviewbase.common;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import example.weisente.top.baselibrary.R;
import example.weisente.top.baselibrary.recycleviewbase.adapter.ViewHolder;

/**
 * Created by san on 2017/11/1.
 */

public class GlideImageLoader extends ViewHolder.HolderImageLoader {
    public GlideImageLoader(String imagePath) {
        super(imagePath);
    }

    @Override
    public void displayImage(Context context, ImageView imageView, String imagePath) {
        Glide.with(context).load(imagePath).placeholder(R.drawable.ic_discovery_default_channel).centerCrop().into(imageView);
    }
}
