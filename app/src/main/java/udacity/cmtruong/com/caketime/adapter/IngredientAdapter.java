package udacity.cmtruong.com.caketime.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import udacity.cmtruong.com.caketime.R;
import udacity.cmtruong.com.caketime.model.Ingredient;

/**
 * Ingredient Adapter for RecyclerView
 *
 * @author davidetruong
 * @version 1.0
 * @since May, 9th
 */
public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private static final String TAG = IngredientAdapter.class.getSimpleName();
    private List<Ingredient> ingredients;

    public IngredientAdapter(List<Ingredient> ingredients) {
        Log.d(TAG, "IngredientAdapter: created");
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForVideoItem = R.layout.ingredient_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForVideoItem, parent, false);
        Log.d(TAG, "onCreateViewHolder: ");
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + position);
        Ingredient ingredient = ingredients.get(position);
        Log.d(TAG, "onBindViewHolder: " + ingredient);
        holder.bind(ingredient);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }


    class IngredientViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ingredient_tv)
        TextView ingredient_tv;

        public IngredientViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            Log.d(TAG, "IngredientViewHolder: created");
        }

        void bind(Ingredient ingredient) {
            Log.d(TAG, "bind: done");
            String text = "- " + ingredient.getIngredient() + ": " + ingredient.getQuantity() + " " + ingredient.getMeasure();
            ingredient_tv.setText(text);
        }
    }
}
