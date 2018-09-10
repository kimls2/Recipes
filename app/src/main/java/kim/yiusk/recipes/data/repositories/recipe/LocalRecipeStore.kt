package kim.yiusk.recipes.data.repositories.recipe

import io.reactivex.Flowable
import kim.yiusk.recipes.data.DatabaseTransactionRunner
import kim.yiusk.recipes.data.daos.RecipeDao
import kim.yiusk.recipes.data.entities.Recipe
import javax.inject.Inject

/**
 * @author <a href="yisuk@mobilabsolutions.com">yisuk</a>
 */
class LocalRecipeStore @Inject constructor(
        private val recipeDao: RecipeDao,
        private val transactionRunner: DatabaseTransactionRunner
) {

    fun getRecipe(recipeId: String) = recipeDao.getRecipeWithRecipeId(recipeId)

    fun getRecipeWithId(id: Long) = recipeDao.getRecipeWithId(id)

    fun observeRecipe(recipeId: String): Flowable<Recipe> = recipeDao.getRecipeWithIdFlowable(recipeId)

    fun getIdOrSavePlaceholder(recipe: Recipe): Long = transactionRunner {
        recipe.recipeId?.let { recipeDao.getRecipeWithRecipeId(it)?.id }
                ?: recipeDao.insert(recipe)
    }

    fun saveArtist(recipe: Recipe): Long {
        return transactionRunner {
            if (recipe.id == 0L) {
                recipeDao.insert(recipe)
            } else {
                recipeDao.update(recipe)
                recipe.id
            }

        }
    }

}