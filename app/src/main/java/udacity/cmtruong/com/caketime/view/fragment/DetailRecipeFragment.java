package udacity.cmtruong.com.caketime.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import udacity.cmtruong.com.caketime.R;
import udacity.cmtruong.com.caketime.model.Step;
import udacity.cmtruong.com.caketime.view.activity.StepRecipeActivity;

/**
 * @author davidetruong
 * @version 1.0
 * @since May 15th, 2018
 */
public class DetailRecipeFragment extends Fragment {

    private static final String TAG = DetailRecipeFragment.class.getSimpleName();

    @BindView(R.id.step_description)
    TextView step_description;

    @BindView(R.id.step_title)
    TextView step_title;

    Step step;
    ArrayList<Step> steps;
    int stepPosition;

    public DetailRecipeFragment() {
    }

    public static DetailRecipeFragment getInstance(ArrayList<Step> steps, int stepPosition) {
        return new DetailRecipeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.step_detail_fragment, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        StepRecipeActivity activity = (StepRecipeActivity) getActivity();
        step = activity.getStepDetail();
        steps = activity.getStepList();
        stepPosition = activity.getStepPosition();
        Log.d(TAG, "onCreateView: " + step.toString());
        step_title.setText(step.getShortDescription());
        step_description.setText(step.getDescription());
    }


}
