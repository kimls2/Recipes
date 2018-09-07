package kim.yiusk.recipes.data.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Update
import kim.yiusk.recipes.data.entities.Recipe

/**
 * @author <a href="yisuk@mobilabsolutions.com">Yisuk Kim</a> on 07-09-2018.
 */
@Dao
abstract class RecipeDao {
    @Insert
    abstract fun insert(recipe: Recipe): Long

    @Update
    abstract fun update(recipe: Recipe)

    @Delete
    abstract fun delete(recipe: Recipe): Int
}