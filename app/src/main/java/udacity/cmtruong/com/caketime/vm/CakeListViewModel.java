package udacity.cmtruong.com.caketime.vm;

import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import rx.Subscriber;
import udacity.cmtruong.com.caketime.adapter.CakeReceiptAdapter;
import udacity.cmtruong.com.caketime.api.CakeRetrofitImpl;
import udacity.cmtruong.com.caketime.model.Cake;

public class CakeListViewModel {
    private static final String TAG = CakeListViewModel.class.getSimpleName();
    public ObservableField<Integer> recyclerViewVisibility;
    public ObservableField<Integer> progressBarVisibility;
    private Subscriber<Cake> mSubscriber;
    private CakeReceiptAdapter mAdapter;

    public CakeListViewModel(CakeReceiptAdapter mAdapter) {
        this.mAdapter = mAdapter;
        Log.d(TAG, "CakeListViewModel: [CakeListVM] Started");
        initData();
        getCakes();
        Log.d(TAG, "CakeListViewModel: [CakeListVM] Continue");
    }


    private void initData() {
        recyclerViewVisibility = new ObservableField<>();
        progressBarVisibility = new ObservableField<>();
        loadingData();
    }

    private void loadingData() {
        progressBarVisibility.set(View.VISIBLE);
        recyclerViewVisibility.set(View.GONE);
    }

    private void displayData() {
        progressBarVisibility.set(View.GONE);
        recyclerViewVisibility.set(View.VISIBLE);
    }


    private void getCakes() {
        Log.d(TAG, "getCakes: [Subscriber] " + mSubscriber);
        mSubscriber = new Subscriber<Cake>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: [Subscriber] checked");
                displayData();
            }

            @Override
            public void onError(Throwable e) {
                loadingData();
                Log.d(TAG, "onError: [ERROR] " + e.getMessage());
            }

            @Override
            public void onNext(Cake cake) {
                Log.d(TAG, "onNext: " + cake.toString());
                mAdapter.addItem(cake);
            }
        };
        CakeRetrofitImpl.getInstance().getCakes(mSubscriber);
        Log.d(TAG, "getCakes: done");
    }

}
