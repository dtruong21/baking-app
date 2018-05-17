package udacity.cmtruong.com.caketime.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import udacity.cmtruong.com.caketime.R;
import udacity.cmtruong.com.caketime.view.fragment.CakeListFragment;

/**
 * MainActivity
 *
 * @author davidetruong
 * @version 1.0
 * @since May, 8th
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String FRAGMENT_KEY = "my_fragment";

    CakeListFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: [MainActivity created]");


        Log.d(TAG, "onCreate: [Fragment]");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
