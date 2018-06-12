package udacity.cmtruong.com.caketime.vm;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import udacity.cmtruong.com.caketime.R;
import udacity.cmtruong.com.caketime.config.Config;
import udacity.cmtruong.com.caketime.model.Cake;
import udacity.cmtruong.com.caketime.view.activity.RecipeDetailActivity;

public class CakeItemViewModel extends BaseObservable {
    private static final String TAG = CakeItemViewModel.class.getSimpleName();
    private Cake cake;

    public CakeItemViewModel(Cake cake) {
        this.cake = cake;
    }

    public String getName() {
        return cake.getName();
    }

    public void onCardClicked(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, RecipeDetailActivity.class);
        intent.putExtra(Config.RECEIPE_INTENT_KEY, cake);
        context.startActivity(intent);
        Log.d(TAG, "onCardClicked: " + cake.toString());

    }

    public String getImageUrl() {
        if (TextUtils.isEmpty(cake.getImage())) {
            return null;
        }
        return cake.getImage();
    }

    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        if (!TextUtils.isEmpty(url))
            Glide.with(imageView.getContext()).load(url).into(imageView);
        else
            Log.d(TAG, "loadImage: iamge URL is empty");
    }

}
