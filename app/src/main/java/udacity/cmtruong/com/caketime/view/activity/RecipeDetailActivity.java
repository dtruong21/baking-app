package udacity.cmtruong.com.caketime.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import udacity.cmtruong.com.caketime.R;
import udacity.cmtruong.com.caketime.config.Config;
import udacity.cmtruong.com.caketime.model.Cake;

public class RecipeDetailActivity extends AppCompatActivity {
    private static final String TAG = RecipeDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipe_detail);
        Intent intent = getIntent();
        Cake cake = intent.getParcelableExtra(Config.RECEIPE_INTENT_KEY);
        Log.d(TAG, "onCreate: started ... " + cake.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
