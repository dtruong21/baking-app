package udacity.cmtruong.com.caketime.helper;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class FragmentHelper {
    private static FragmentManager getFragmentManager(Context context) {
        return ((AppCompatActivity) context).getSupportFragmentManager();
    }

    public static void openFragment(Context context, int frameId, Fragment fragment) {
        getFragmentManager(context).beginTransaction()
                .replace(frameId, fragment, fragment.getClass().toString())
                .addToBackStack(null)
                .commit();
    }

    public static void addFragment(Context context, int frameId, Fragment fragment) {
        getFragmentManager(context).beginTransaction()
                .add(frameId, fragment, fragment.getClass().toString())
                .addToBackStack(null)
                .commit();
    }
}
