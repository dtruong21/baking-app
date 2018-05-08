package udacity.cmtruong.com.caketime.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import udacity.cmtruong.com.caketime.R;

/**
 * MainActivity
 *
 * @author davidetruong
 * @version 1.0
 * @since May, 8th
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: [MainActivity created]");

        getFragmentManager().beginTransaction().add(R.id.cake_fragment, CakeListFragment.getInstance()).commit();
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
}
