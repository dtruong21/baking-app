package udacity.cmtruong.com.caketime.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import udacity.cmtruong.com.caketime.config.Config;

/**
 * @author davidetruong
 * @version 1.0
 * @since June 6th, 2018
 */
public class PreferencesHelper {

    public static String getSaveRecipe(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(Config.RECIPE_NAME, "");
    }

    public static void setSaveRecipe(Context context, String name) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Config.RECIPE_NAME, name);
        editor.apply();
    }
}
