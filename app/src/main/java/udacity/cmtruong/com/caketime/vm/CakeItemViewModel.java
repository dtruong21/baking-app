package udacity.cmtruong.com.caketime.vm;

import android.databinding.BaseObservable;

import udacity.cmtruong.com.caketime.model.Cake;

public class CakeItemViewModel extends BaseObservable {
    private Cake cake;

    public CakeItemViewModel(Cake cake) {
        this.cake = cake;
    }

    public String getName() {
        return cake.getName();
    }

}
