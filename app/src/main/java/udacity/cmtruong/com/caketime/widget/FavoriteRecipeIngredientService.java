package udacity.cmtruong.com.caketime.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import udacity.cmtruong.com.caketime.R;
import udacity.cmtruong.com.caketime.api.PreferencesHelper;
import udacity.cmtruong.com.caketime.data.IngredientsColumns;
import udacity.cmtruong.com.caketime.data.RecipeProvider;

/**
 * @author davidetruong
 * @version 1.0
 * @since May 18th, 2017
 */
public class FavoriteRecipeIngredientService extends RemoteViewsService {
    private static final String TAG = FavoriteRecipeIngredientService.class.getSimpleName();

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
        Log.d(TAG, "onCreate: create the service");
        RecipeWidgetProvider.setRecipeText(this, PreferencesHelper.getSaveRecipe(this));
        Log.d(TAG, "onCreate: " + PreferencesHelper.getSaveRecipe(this));
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {
            Cursor mCursor = null;

            @Override
            public void onCreate() {
                Log.d(TAG, "onCreate: the view factory");
            }

            @Override
            public void onDataSetChanged() {
                if (mCursor != null)
                    mCursor.close();
                final long token = Binder.clearCallingIdentity();
                mCursor = getContentResolver().query(RecipeProvider.Ingredients.INGREDIENTS_URI,
                        INGREDIENTS_COL,
                        null,
                        null,
                        null);
                Binder.restoreCallingIdentity(token);
            }

            @Override
            public void onDestroy() {
                mCursor.close();
                mCursor = null;

            }

            @Override
            public int getCount() {
                if (mCursor == null)
                    return 0;
                return mCursor.getCount();
            }

            @Override
            public RemoteViews getViewAt(int position) {

                RemoteViews view = new RemoteViews(getPackageName(), R.layout.recipe_widget_provider_item);
                Log.d(TAG, "getViewAt: " + position);
                if (mCursor == null || !mCursor.moveToPosition(position)) return null;
                Log.d(TAG, "getViewAt: checked");
                String name = mCursor.getString(1);
                String mesure = mCursor.getString(2);
                double quantity = mCursor.getDouble(3);
                String text = "😀 " + name + " " + quantity + " " + mesure;

                Log.d(TAG, "getViewAt: " + mCursor.getColumnName(1));
                Log.d(TAG, "getViewAt: " + mCursor.getString(1));
                view.setTextViewText(R.id.widget_name, text);
                Log.d(TAG, "getViewAt: ");
                return view;
            }

            @Override
            public RemoteViews getLoadingView() {
                Log.d(TAG, "getLoadingView: checked");
                RemoteViews rv = new RemoteViews(getPackageName(), R.layout.recipe_widget_provider_item);
                return rv;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                Log.d(TAG, "getItemId: " + position);
                if (mCursor.moveToPosition(position))
                    return mCursor.getInt(0);
                return position;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
    }

}
