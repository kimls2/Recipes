package kim.yiusk.recipes.data.repositories.search

import kim.yiusk.recipes.data.Result
import kim.yiusk.recipes.data.entities.Recipe
import kim.yiusk.recipes.data.mappers.SearchResponseToRecipes
import kim.yiusk.recipes.network.RecipeApi
import kim.yiusk.recipes.network.RetrofitRunner
import kim.yiusk.recipes.util.ext.executeWithRetry
import javax.inject.Inject

/**
 * @author <a href="yisuk@mobilabsolutions.com">yisuk</a>
 */
class RemoteSearchDataSource @Inject constructor(
        private val recipeApi: RecipeApi,
        private val retrofitRunner: RetrofitRunner,
        private val mapper: SearchResponseToRecipes
) {

    suspend fun search(title: String): Result<List<Recipe>> {
        return retrofitRunner.executeForResponse(mapper) {
            recipeApi.searchRecipes(title).executeWithRetry()
        }
    }
}