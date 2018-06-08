package udacity.cmtruong.com.caketime.widget;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import udacity.cmtruong.com.caketime.R;
import udacity.cmtruong.com.caketime.api.PreferencesHelper;
import udacity.cmtruong.com.caketime.view.activity.MainActivity;
import udacity.cmtruong.com.caketime.view.activity.RecipeDetailActivity;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {
    private static final String TAG = RecipeWidgetProvider.class.getSimpleName();
    Context mContext;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.app_name);
        String name = PreferencesHelper.getSaveRecipe(context);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_provider);
        Log.d(TAG, "updateAppWidget: " + name);
        if (name.equals(""))
            views.setTextViewText(R.id.appwidget_text, widgetText);
        views.setTextViewText(R.id.appwidget_text, name);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        // Construct the RemoteViews object

        views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);
        // Instruct the widget manager to update the widget
        setRemoteAdapter(context, views);

        Intent ingredientIntent = new Intent(context, RecipeDetailActivity.class);
        PendingIntent pendingIngredientIntent = TaskStackBuilder.create(context)
                .addNextIntentWithParentStack(ingredientIntent).getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.widget_ingredient_list, pendingIngredientIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);

        Log.d(TAG, "updateAppWidget: checked");

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        Intent intent = new Intent(mContext, FavoriteRecipeIngredientService.class);
        mContext.startService(intent);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(RecipeDetailActivity.ACTION_UPDATED)) {
            Log.d(TAG, "onReceive: " + intent.getAction());
            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            int[] widgetIds = manager.getAppWidgetIds(new ComponentName(context, getClass()));
            manager.notifyAppWidgetViewDataChanged(widgetIds, R.id.widget_ingredient_list);
            onUpdate(context, manager, widgetIds);
        }
        super.onReceive(context, intent);
    }

    private static void setRemoteAdapter(Context context, RemoteViews remoteViews) {
        remoteViews.setRemoteAdapter(R.id.widget_ingredient_list, new Intent(context, FavoriteRecipeIngredientService.class));
    }

    public static void setRecipeText(Context context, String name) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_provider_item);

        views.setTextViewText(R.id.appwidget_text, name);

    }

}

