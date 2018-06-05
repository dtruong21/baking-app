package udacity.cmtruong.com.caketime.view.activity;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

import udacity.cmtruong.com.caketime.R;
import udacity.cmtruong.com.caketime.config.Config;
import udacity.cmtruong.com.caketime.data.CakeRecipeColumns;
import udacity.cmtruong.com.caketime.data.IngredientsColumns;
import udacity.cmtruong.com.caketime.data.RecipeProvider;
import udacity.cmtruong.com.caketime.data.generated.CakeRecipeDatabase;
import udacity.cmtruong.com.caketime.model.Cake;
import udacity.cmtruong.com.caketime.model.Ingredient;

public class RecipeDetailActivity extends AppCompatActivity {
    private static final String TAG = RecipeDetailActivity.class.getSimpleName();
    private static final String ACTION_UPDATED = "udacity.cmtruong.com.caketime.view.activity.ACTION_UPDATE";
    Cake cake;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipe_detail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_favorite:
                addToFavorite(cake);
                if (!checkIfRecipeExist(this, cake.getId())) {
                    item.setIcon(getDrawable(R.drawable.ic_favorite_black_24px));
                } else {
                    item.setIcon(getDrawable(R.drawable.ic_favorite_border_black_24px));
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public Cake getRecipe() {
        Intent intent = getIntent();
        cake = intent.getParcelableExtra(Config.RECEIPE_INTENT_KEY);
        Log.d(TAG, "onCreate: started ... " + cake.toString());
        return cake;
    }

    private void addToFavorite(final Cake cake) {
        final Intent dataIntent = new Intent(ACTION_UPDATED);
        if (!checkIfRecipeExist(this, cake.getId())) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(CakeRecipeColumns.NAME, cake.getName());
                    contentValues.put(CakeRecipeColumns.CAKE_ID, cake.getId());
                    contentValues.put(CakeRecipeColumns.SERVING, cake.getServing());
                    ArrayList<ContentProviderOperation> operations = new ArrayList<>();
                    for (Ingredient ingredient : cake.getIngredients()) {
                        ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(RecipeProvider.Ingredients.INGREDIENTS_URI)
                                .withValue(IngredientsColumns.CAKE_RECIPE_ID, cake.getId())
                                .withValue(IngredientsColumns.INGREDIENT, ingredient.getIngredient())
                                .withValue(IngredientsColumns.MESURE, ingredient.getMeasure())
                                .withValue(IngredientsColumns.QUANTITY, ingredient.getQuantity());
                        operations.add(builder.build());
                    }

                    getContentResolver().delete(RecipeProvider.Ingredients.INGREDIENTS_URI, null, null);
                    getContentResolver().delete(RecipeProvider.CakeRecipes.RECIPES_URI, null, null);
                    getContentResolver().insert(RecipeProvider.CakeRecipes.RECIPES_URI, contentValues);
                    Log.d(TAG, "run: check " + contentValues.toString());
                    try {
                        getContentResolver().applyBatch(RecipeProvider.AUTHORITY, operations);
                        Log.d(TAG, "run: operations " + operations.toString());
                        sendBroadcast(dataIntent);
                    } catch (RemoteException | OperationApplicationException e) {
                        Log.e(TAG, "run: " + e, e);
                    }
                }
            }).start();

        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    getContentResolver().delete(RecipeProvider.Ingredients.INGREDIENTS_URI, null, null);
                    getContentResolver().delete(RecipeProvider.CakeRecipes.RECIPES_URI, null, null);
                    sendBroadcast(dataIntent);
                }
            }).start();
        }
    }


    private boolean checkIfRecipeExist(Context context, int cakeID) {
        SQLiteOpenHelper helper = CakeRecipeDatabase.getInstance(context);
        SQLiteDatabase database = helper.getReadableDatabase();
        long row = DatabaseUtils.queryNumEntries(database, udacity.cmtruong.com.caketime.data.CakeRecipeDatabase.CAKE_RECIPES, CakeRecipeColumns.CAKE_ID + " = " + cakeID, null);
        database.close();
        return row != 0;
    }


}
