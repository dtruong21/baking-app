package udacity.cmtruong.com.caketime.view.fragment;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import udacity.cmtruong.com.caketime.R;
import udacity.cmtruong.com.caketime.adapter.CakeRecipeAdapter;
import udacity.cmtruong.com.caketime.databinding.CakeFragmentBinding;
import udacity.cmtruong.com.caketime.vm.CakeListViewModel;

/**
 * Cake Fragment List
 *
 * @author davidetruong
 * @version 1.0
 * @since May, 8th
 */
public class CakeListFragment extends Fragment {
    private static final String TAG = CakeListFragment.class.getSimpleName();
    private CakeRecipeAdapter mAdapter;
    private CakeListViewModel mViewModel;
    private CakeFragmentBinding cakeFragmentBinding;

    public static CakeListFragment getInstance() {
        return new CakeListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //View contentView = inflater.inflate(R.layout.cake_fragment, container, false);
        cakeFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.cake_fragment, container, false);
        View contentView = cakeFragmentBinding.getRoot();
        Log.d(TAG, "onCreateView: [New view created]");
        initData();
        return contentView;
    }

    private void initData() {
        mAdapter = new CakeRecipeAdapter();
        Log.d(TAG, "initData: [mAdapter] " + mAdapter.toString());
        cakeFragmentBinding.cakeListRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        cakeFragmentBinding.cakeListRecycler.setItemAnimator(new DefaultItemAnimator());
        cakeFragmentBinding.cakeListRecycler.setAdapter(mAdapter);
        mViewModel = new CakeListViewModel(mAdapter);
        Log.d(TAG, "initData: [mViewModel] " + mViewModel.toString());
        cakeFragmentBinding.setVm(mViewModel);
    }

}
