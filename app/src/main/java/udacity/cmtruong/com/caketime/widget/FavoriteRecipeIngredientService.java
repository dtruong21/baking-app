package udacity.cmtruong.com.caketime.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import udacity.cmtruong.com.caketime.R;
import udacity.cmtruong.com.caketime.data.IngredientsColumns;
import udacity.cmtruong.com.caketime.data.RecipeProvider;

/**
 * @author davidetruong
 * @version 1.0
 * @since May 18th, 2017
 */
public class FavoriteRecipeIngredientService extends RemoteViewsService {
    private static final String TAG = FavoriteRecipeIngredientService.class.getSimpleName();

    private static final int INDEX_INGREDIENT_ID = 0;

    private static final String[] INGREDIENTS_COL = {
            IngredientsColumns._ID,
            IngredientsColumns.INGREDIENT,
            IngredientsColumns.MESURE,
            IngredientsColumns.QUANTITY,
            IngredientsColumns.CAKE_RECIPE_ID
    };

    public FavoriteRecipeIngredientService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {
            Context context;
            Cursor mCursor = null;

            @Override
            public void onCreate() {

            }

            @Override
            public void onDataSetChanged() {
                if (mCursor != null)
                    mCursor.close();
                mCursor = getContentResolver().query(RecipeProvider.Ingredients.INGREDIENTS_URI,
                        INGREDIENTS_COL,
                        null,
                        null,
                        null);

            }

            @Override
            public void onDestroy() {
                if (mCursor != null) {
                    mCursor.close();
                    mCursor = null;
                }

            }

            @Override
            public int getCount() {
                if (mCursor == null) return 0;
                return mCursor.getCount();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                RemoteViews view = new RemoteViews(getPackageName(), R.layout.recipe_widget_provider_item);
                Log.d(TAG, "getViewAt: started");
                String name = mCursor.getString(1);
                String mesure = mCursor.getString(2);
                double quantity = mCursor.getDouble(3);
                String text = name + " " + mesure + " " + quantity;
                view.setTextViewText(R.id.widget_name, text);
                Log.d(TAG, "getViewAt: " + text);
                return view;
            }

            @Override
            public RemoteViews getLoadingView() {
                RemoteViews rv = new RemoteViews(getPackageName(), R.layout.recipe_widget_provider_item);
                return rv;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                if (mCursor.moveToPosition(position))
                    return mCursor.getInt(INDEX_INGREDIENT_ID);
                return position;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
    }

}
