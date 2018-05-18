package udacity.cmtruong.com.caketime.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * @author davidetruong
 * @version 1.0
 * @since May 18th, 2017
 */
public class FavoriteRecipeIngredientService extends RemoteViewsService {
    private static final String TAG = FavoriteRecipeIngredientService.class.getSimpleName();

    public FavoriteRecipeIngredientService() {
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return null;
    }


}
