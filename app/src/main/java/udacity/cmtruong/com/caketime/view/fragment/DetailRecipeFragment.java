package udacity.cmtruong.com.caketime.view.fragment;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

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
    @BindView(R.id.error_tv)
    TextView error_tv;
    @BindView(R.id.playback)
    SimpleExoPlayerView simpleExoPlayerView;

    private SimpleExoPlayer mPlayer;
    private Step step;
    private ArrayList<Step> steps;
    private static final String STEP_LIST = "list_step";
    private long currentPositionPlay;
    private static final String PLAYER_POSITION = "player_position";
    private static final String STEP_POSITION = "step_position";
    private boolean isStopped;
    private int stepPosition;

    public DetailRecipeFragment() {
    }

    public static DetailRecipeFragment getInstance(ArrayList<Step> steps, int stepPosition) {
        DetailRecipeFragment mFragment = new DetailRecipeFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(STEP_LIST, steps);
        bundle.putInt(STEP_POSITION, stepPosition);
        mFragment.setArguments(bundle);
        return mFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (savedInstanceState != null && savedInstanceState.containsKey(PLAYER_POSITION))
            currentPositionPlay = savedInstanceState.getLong(PLAYER_POSITION);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.step_detail_fragment, container, false);
        setRetainInstance(true);
        ButterKnife.bind(this, view);
        if (getArguments() != null) {
            Log.d(TAG, "onCreateView: " + getArguments().toString());
            steps = getArguments().getParcelableArrayList(STEP_LIST);
            stepPosition = getArguments().getInt(STEP_POSITION);
        }
        initData();
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mPlayer != null) {
            currentPositionPlay = mPlayer.getCurrentPosition();
            Log.d(TAG, "onStop: " + currentPositionPlay);
            isStopped = true;
            releasePlayer();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        initPlayer(Uri.parse(step.getVideoURL()));
    }

    private void initData() {
        //StepRecipeActivity activity = (StepRecipeActivity) getActivity();
        //steps = activity.getStepList();
        //stepPosition = activity.getStepPosition();
        step = steps.get(stepPosition);
        Log.d(TAG, "onCreateView: " + step.toString());
        step_title.setText(step.getShortDescription());
        step_description.setText(step.getDescription());
        Log.d(TAG, "initData: " + step.getVideoURL());
        handleVisibility();
    }

    private void handleVisibility() {
        if (step.getVideoURL().equals("")) {
            simpleExoPlayerView.setVisibility(View.GONE);
            error_tv.setVisibility(View.VISIBLE);
            initPlayer(Uri.parse(step.getVideoURL()));
        } else {
            error_tv.setVisibility(View.GONE);
            simpleExoPlayerView.setVisibility(View.VISIBLE);
        }

    }

    private void initPlayer(Uri mUri) {
        Log.d(TAG, "initPlayer: " + mUri);
        if (mPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
            simpleExoPlayerView.setPlayer(mPlayer);
            String userAgent = Util.getUserAgent(getActivity(), getString(R.string.app_name));
            MediaSource mediaSource = new ExtractorMediaSource(mUri, new DefaultDataSourceFactory(
                    getActivity(), userAgent), new DefaultExtractorsFactory(), null, null
            );
            mPlayer.prepare(mediaSource);
            mPlayer.setPlayWhenReady(true);
            Log.d(TAG, "initPlayer: " + currentPositionPlay);
            if (currentPositionPlay != 0)
                mPlayer.seekTo(currentPositionPlay);
            else
                mPlayer.seekTo(0);

        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        releasePlayer();
    }

    private void releasePlayer() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(PLAYER_POSITION, currentPositionPlay);
        Log.d(TAG, "onSaveInstanceState: " + currentPositionPlay);
    }
}
