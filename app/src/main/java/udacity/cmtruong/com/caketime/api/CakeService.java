package udacity.cmtruong.com.caketime.api;


import java.util.List;

import retrofit2.http.GET;
import rx.Observable;
import udacity.cmtruong.com.caketime.model.Cake;

/**
 * Retrofit interface which request the cake list
 *
 * @author davidetruong
 * @version 1.0
 * @since May, 8th
 */
public interface CakeService {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Observable<List<Cake>> getCakes();
}
