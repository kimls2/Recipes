package kim.yiusk.recipes.data.repositories.recipe

import kim.yiusk.recipes.data.Result
import kim.yiusk.recipes.data.entities.Recipe
import kim.yiusk.recipes.data.mappers.RecipeResponseToRecipeEntity
import kim.yiusk.recipes.network.RecipeApi
import kim.yiusk.recipes.network.RetrofitRunner
import kim.yiusk.recipes.util.ext.executeWithRetry
import javax.inject.Inject

/**
 * @author <a href="yisuk@mobilabsolutions.com">yisuk</a>
 */
class RemoteRecipeDataSource @Inject constructor(
        private val recipeApi: RecipeApi,
        private val retrofitRunner: RetrofitRunner,
        private val mapper: RecipeResponseToRecipeEntity
) {

    suspend fun getRecipe(recipeId: String): Result<Recipe> {
        return retrofitRunner.executeForResponse(mapper) {
            recipeApi.getRecipe(recipeId).executeWithRetry()
        }
    }
}