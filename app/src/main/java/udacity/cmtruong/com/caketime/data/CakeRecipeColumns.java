package udacity.cmtruong.com.caketime.data;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;
import net.simonvt.schematic.annotation.Unique;

public interface CakeRecipeColumns {

    @DataType(DataType.Type.INTEGER)
    @PrimaryKey
    @AutoIncrement
    String _ID = "_id";

    @DataType(DataType.Type.INTEGER)
    @NotNull
    String CAKE_ID = "cake_id";

    @DataType(DataType.Type.TEXT)
    @NotNull
    @Unique
    String NAME = "name";

    @DataType(DataType.Type.INTEGER)
    @NotNull
    String SERVING = "serving";

    @DataType(DataType.Type.TEXT)
    String IMAGE_URL = "image";
}
