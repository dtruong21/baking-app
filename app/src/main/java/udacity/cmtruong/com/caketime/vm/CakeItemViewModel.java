package udacity.cmtruong.com.caketime.vm;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.util.Log;
import android.view.View;

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

}
