package udacity.cmtruong.com.caketime.view.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import udacity.cmtruong.com.caketime.R;
import udacity.cmtruong.com.caketime.model.Step;
import udacity.cmtruong.com.caketime.view.fragment.DetailRecipeFragment;

/**
 * @author davidetruong
 * @version 1.0
 * @since May 15th, 2018
 */
public class StepRecipeActivity extends AppCompatActivity {
    private static final String TAG = StepRecipeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipe);


        if (savedInstanceState == null) {
            DetailRecipeFragment fragment = new DetailRecipeFragment();
            FragmentManager fm = getFragmentManager();
            fm.beginTransaction()
                    .add(R.id.recipe_container, fragment)
                    .addToBackStack(null)
                    .commit();
        }
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

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(StepRecipeActivity.this, RecipeDetailActivity.class);
        startActivity(intent);

    }

    public Step getStepDetail() {
        Intent intent = getIntent();
        Step step = intent.getParcelableExtra(getString(R.string.step_key));
        Log.d(TAG, "onCreate: " + step.toString());
        return step;
    }
}
