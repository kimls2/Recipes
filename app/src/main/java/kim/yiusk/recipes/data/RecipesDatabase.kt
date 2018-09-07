package kim.yiusk.recipes.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import kim.yiusk.recipes.data.daos.RecipeDao
import kim.yiusk.recipes.data.entities.Recipe

/**
 * @author <a href="yisuk@mobilabsolutions.com">Yisuk Kim</a> on 07-09-2018.
 */
@Database(
        entities = [
            Recipe::class
        ],
        version = 1
)
abstract class RecipesDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}