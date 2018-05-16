package udacity.cmtruong.com.caketime.view.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    Bundle b;

    @BindView(R.id.previous_bt)
    Button previous_bt;

    @BindView(R.id.next_bt)
    Button next_bt;
    int stepPosition;

    Step step;
    ArrayList<Step> steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipe);
        ButterKnife.bind(this);
        b = getIntent().getExtras();
        getStepDetail();
        getStepPosition();
        getStepList();
        Log.d(TAG, "onCreate: " + getStepList().toString());
        if (savedInstanceState == null) {
            FragmentManager fm = getFragmentManager();
            fm.beginTransaction()
                    .add(R.id.recipe_container, DetailRecipeFragment.getInstance(steps, stepPosition))
                    .addToBackStack(null)
                    .commit();

            handleVisibility();
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
        step = b.getParcelable(getString(R.string.step_key));
        Log.d(TAG, "onCreate: " + step.toString());
        return step;
    }


    public int getStepPosition() {
        stepPosition = b.getInt(getString(R.string.step_position));
        return stepPosition;
    }

    public ArrayList<Step> getStepList() {
        steps = b.getParcelableArrayList(getString(R.string.step_list));
        return steps;
    }

    private void handleVisibility() {
        if (stepPosition == 0) {
            next_bt.setVisibility(View.VISIBLE);
            previous_bt.setVisibility(View.INVISIBLE);
        } else if (stepPosition == steps.size() - 1) {
            previous_bt.setVisibility(View.VISIBLE);
            next_bt.setVisibility(View.INVISIBLE);
        } else {
            previous_bt.setVisibility(View.VISIBLE);
            next_bt.setVisibility(View.VISIBLE);
        }
    }

}
