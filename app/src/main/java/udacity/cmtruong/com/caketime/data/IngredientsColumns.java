package udacity.cmtruong.com.caketime.data;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;
import net.simonvt.schematic.annotation.References;

public interface IngredientsColumns {
    @DataType(DataType.Type.INTEGER)
    @PrimaryKey
    @AutoIncrement
    String _ID = "_id";

    @DataType(DataType.Type.TEXT)
    @NotNull
    String INGREDIENT = "ingredient";

    @DataType(DataType.Type.INTEGER)
    @References(table = CakeRecipeDatabase.CAKE_RECIPES, column = CakeRecipeColumns.CAKE_ID)
    String CAKE_RECIPE_ID = "cake_id";

    @DataType(DataType.Type.TEXT)
    @NotNull
    String MESURE = "mesure";

    @DataType(DataType.Type.REAL)
    @NotNull
    String QUANTITY = "quantity";


}
