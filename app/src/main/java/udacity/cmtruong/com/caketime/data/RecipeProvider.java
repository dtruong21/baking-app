package udacity.cmtruong.com.caketime.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.NotifyBulkInsert;
import net.simonvt.schematic.annotation.NotifyInsert;
import net.simonvt.schematic.annotation.NotifyUpdate;
import net.simonvt.schematic.annotation.TableEndpoint;

import udacity.cmtruong.com.caketime.model.Ingredient;

/**
 * @author davidetruong
 * @version 1.0
 * @since May 28th, 2018
 */
@ContentProvider(authority = RecipeProvider.AUTHORITY, database = CakeRecipeDatabase.class)
public class RecipeProvider {

    private static final String TAG = RecipeProvider.class.getSimpleName();
    public static final String AUTHORITY = "udacity.cmtruong.com.caketime.data.RecipeProvider";
    public static final String CONTENT_URI = "content://" + AUTHORITY;

    @TableEndpoint(table = CakeRecipeDatabase.CAKE_RECIPES)
    public static class CakeRecipes {
        @ContentUri(
                path = Path.CAKE_RECIPE,
                type = "vnd.android.cursor.dir/cake_recipes",
                defaultSort = CakeRecipeColumns.CAKE_ID + " ASC"
        )
        public static final Uri RECIPES_URI = Uri.parse(CONTENT_URI + "/" + Path.CAKE_RECIPE);

        @InexactContentUri(
                path = Path.CAKE_RECIPE + "/#",
                name = "CAKE_RECIPE_ID",
                type = "vnd.android.cursor.item/cake_recipes",
                whereColumn = CakeRecipeColumns._ID,
                pathSegment = 1
        )
        public static Uri withId(int id) {
            return Uri.parse(CONTENT_URI + "/" + Path.CAKE_RECIPE + "/" + id);
        }

        @InexactContentUri(
                path = Path.CAKE_RECIPE + "/*",
                name = "CAKE_NAME",
                type = "vnd.android.cursor.item/cake_recipes",
                whereColumn = CakeRecipeColumns._ID,
                pathSegment = 1
        )
        public static Uri withCakeName(String name) {
            return Uri.parse(CONTENT_URI + "/" + Path.CAKE_RECIPE + "/" + name);
        }
    }

    @TableEndpoint(table = CakeRecipeDatabase.INGREDIENTS)
    public static class Ingredients {
        @ContentUri(
                path = Path.INGREDIENT,
                type = "vnd.android.cursor.dir/ingredients")
        public static final Uri INGREDIENTS_URI = Uri.parse(CONTENT_URI + "/" + Path.INGREDIENT);

        @InexactContentUri(
                path = Path.INGREDIENT + "/" + Path.RECIPE_INGREDIENT + "/#",
                name = "INGREDIENT_ID",
                type = "vnd.android.cursor.dir/cake_recipes",
                whereColumn = IngredientsColumns.CAKE_RECIPE_ID,
                pathSegment = 2
        )
        public static Uri withId(int id) {
            return Uri.parse(CONTENT_URI + "/" + Path.INGREDIENT + "/" + Path.RECIPE_INGREDIENT + "/" + id);
        }

        @NotifyInsert(paths = Path.INGREDIENT)
        public static Uri[] onInsert(ContentValues values) {
            final int recipeIngredientId = values.getAsInteger(IngredientsColumns.CAKE_RECIPE_ID);
            return new Uri[]{
                    CakeRecipes.withId(recipeIngredientId), withId(recipeIngredientId)
            };
        }

        @NotifyBulkInsert(paths = Path.INGREDIENT)
        public static Uri[] onBulkInsert(Context context, Uri uri, ContentValues[] values, long[] ids) {
            return new Uri[]{
                    uri,
            };
        }

    }
}
