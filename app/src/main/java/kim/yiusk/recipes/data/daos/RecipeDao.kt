package kim.yiusk.recipes.data.daos

import android.arch.persistence.room.*
import io.reactivex.Flowable
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

    @Query("SELECT * FROM recipes WHERE id = :id")
    abstract fun getRecipeWithId(id: Long): Recipe?

    @Query("SELECT * FROM recipes WHERE recipe_id = :id")
    abstract fun getRecipeWithIdFlowable(id: String): Flowable<Recipe>

    @Query("SELECT * FROM recipes WHERE recipe_id = :recipeId")
    abstract fun getRecipeWithRecipeId(recipeId: String): Recipe?
}