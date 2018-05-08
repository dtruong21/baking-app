package udacity.cmtruong.com.caketime.api;

import android.util.Log;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import udacity.cmtruong.com.caketime.config.Config;
import udacity.cmtruong.com.caketime.model.Cake;

/**
 * Implementation class which handle the http request
 *
 * @author davidetruong
 * @version 1.0
 * @since May, 8th
 */
public class CakeRetrofitImpl {

    private static final String TAG = CakeRetrofitImpl.class.getSimpleName();
    private Retrofit retrofit;
    private CakeService mService;
    private OkHttpClient.Builder mBuilder;

    /**
     * Create a singleton class
     */
    static class Singleton {
        private static final CakeRetrofitImpl INSTANCE = new CakeRetrofitImpl();
    }

    /**
     * Get an instance of this class
     *
     * @return Singleton.INSTANCE
     */
    public static CakeRetrofitImpl getInstance() {
        Log.d(TAG, "getInstance: [Instance created] " + Singleton.INSTANCE.toString());
        return Singleton.INSTANCE;
    }

    /**
     * Constructor
     */
    public CakeRetrofitImpl() {
        mBuilder = new OkHttpClient.Builder();
        Log.d(TAG, "CakeRetrofitImpl: [builder created] " + mBuilder.toString());
        retrofit = new Retrofit.Builder()
                .client(mBuilder.build())
                .baseUrl(Config.CAKE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Log.d(TAG, "CakeRetrofitImpl: [Retrofit client creted] " + retrofit.toString());
        mService = retrofit.create(CakeService.class);
    }

    /**
     * Function get all cake receipts and subscribe on observable
     *
     * @param subscriber
     */
    public void getCakes(Subscriber<Cake> subscriber) {
        mService.getCakes()
                .flatMap(new Func1<List<Cake>, Observable<Cake>>() {
                    @Override
                    public Observable<Cake> call(List<Cake> cakes) {

                        Log.d(TAG, "call: [Call to observable] " + cakes.toString());
                        return Observable.from(cakes);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
