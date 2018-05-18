package udacity.cmtruong.com.caketime.data;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

public class RecipeProvider {

    public static final String AUTHORITY = "udacity.cmtruong.com.caketime.data.RecipeProvider";

    @TableEndpoint(table = CakeRecipeDatabase.CAKE_RECIPES)
    public static class CakeRecipes {
        @ContentUri(
                path = Path.CAKE_RECIPE,
                type = "vnd.android.cursor.dir/cake_recipes",
                defaultSort = CakeRecipeColumns.CAKE_ID + " ASC"
        )
        public static final Uri RECIPES = Uri.parse("content://" + AUTHORITY + "/cake_recipes");

        @InexactContentUri(
                path = Path.CAKE_RECIPE + "/#",
                name = "CAKE_RECIPE_ID",
                type = "vnd.android.cursor.item/cake_recipes",
                whereColumn = CakeRecipeColumns._ID,
                pathSegment = 1
        )
        public static Uri withId(int id) {
            return Uri.parse("content://" + AUTHORITY + "/cake_recipes/" + id);
        }

        @InexactContentUri(
                path = Path.CAKE_RECIPE + "/*",
                name = "CAKE_NAME",
                type = "vnd.android.cursor.item/cake_recipes",
                whereColumn = CakeRecipeColumns._ID,
                pathSegment = 1
        )
        public static Uri withCakeName(String name) {
            return Uri.parse("content://" + AUTHORITY + "/cake_recipes/" + name);
        }
    }

    @TableEndpoint(table = CakeRecipeDatabase.INGREDIENTS)
    public static class Ingredients {
        @ContentUri(
                path = Path.INGREDIENT,
                type = "vnd.android.cursor.dir/ingredients")
        public static final Uri INGREDIENTS = Uri.parse("content://" + AUTHORITY + "/ingredients");

        @InexactContentUri(
                path = Path.INGREDIENT + "/" + Path.RECIPE_INGREDIENT + "/#",
                name = "INGREDIENT_ID",
                type = "vnd.android.cursor.dir/cake_recipes",
                whereColumn = IngredientsColumns.CAKE_RECIPE_ID,
                pathSegment = 2
        )
        public static Uri withId(int id) {
            return Uri.parse("content://" + AUTHORITY + "/" + Path.INGREDIENT + "/" + Path.RECIPE_INGREDIENT + "/" + id);
        }


    }
}
