package example.weisente.top.baselibrary.dialog;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import example.weisente.top.baselibrary.R;

/**
 * Created by san on 2017/11/1.
 */

public class GlideImageLoader extends HolderImageLoader {
    public GlideImageLoader(){};
    public GlideImageLoader(String imagePath) {
        super(imagePath);
    }

    @Override
    public void displayImage(Context context, ImageView imageView, String imagePath) {
        Glide.with(context).load(imagePath).placeholder(R.drawable.ic_discovery_default_channel).centerCrop().into(imageView);
    }
}
