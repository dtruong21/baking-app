package udacity.cmtruong.com.caketime.view.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import udacity.cmtruong.com.caketime.R;
import udacity.cmtruong.com.caketime.adapter.IngredientAdapter;
import udacity.cmtruong.com.caketime.adapter.RecipeStepAdapter;
import udacity.cmtruong.com.caketime.adapter.event.OnStepHandledListener;
import udacity.cmtruong.com.caketime.model.Cake;
import udacity.cmtruong.com.caketime.model.Ingredient;
import udacity.cmtruong.com.caketime.model.Step;
import udacity.cmtruong.com.caketime.view.activity.StepRecipeActivity;
import udacity.cmtruong.com.caketime.view.activity.RecipeDetailActivity;

public class RecipeItemFragment extends Fragment {
    private static final String TAG = RecipeItemFragment.class.getSimpleName();
    private static final String SCROLL_POSITION = "position";
    @BindView(R.id.ingredient_rv)
    RecyclerView ingredient_rv;

    @BindView(R.id.step_rv)
    RecyclerView step_rv;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;

    private int scrollPosition;

    public interface ItemCallBack {
        void getStepSelected(ArrayList<Step> steps, int position);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recipe_fragment, container, false);
        ButterKnife.bind(this, rootView);
        RecipeDetailActivity activity = (RecipeDetailActivity) getActivity();
        Cake cake = activity.getRecipe();
        Log.d(TAG, "onCreateView: " + cake.getIngredients());
        List<Ingredient> ingredients = cake.getIngredients();
        ingredient_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        ingredient_rv.setHasFixedSize(false);
        IngredientAdapter ingredientAdapter = new IngredientAdapter(ingredients);
        ingredient_rv.setAdapter(ingredientAdapter);

        final List<Step> steps = cake.getSteps();
        step_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        step_rv.setHasFixedSize(false);
        RecipeStepAdapter stepAdapter = new RecipeStepAdapter(steps);
        step_rv.setAdapter(stepAdapter);
        final ArrayList<Step> stepsList = new ArrayList<>(steps);
        stepAdapter.setOnItemClickedListener(new OnStepHandledListener() {
            @Override
            public void onStepClicked(View view, int position) {
                ((RecipeDetailActivity) getActivity()).getStepSelected(stepsList, position);
            }
        });

        if (savedInstanceState != null) {
            scrollPosition = savedInstanceState.getInt(SCROLL_POSITION);

            nestedScrollView.post(new Runnable() {
                @Override
                public void run() {
                    nestedScrollView.scrollTo(0, scrollPosition);
                }
            });
        }
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SCROLL_POSITION, nestedScrollView.getScrollY());
        Log.d(TAG, "onSaveInstanceState: " + nestedScrollView.getScrollY());
    }

    @Override
    public void onPause() {
        super.onPause();
        scrollPosition = nestedScrollView.getScrollY();
    }
}
