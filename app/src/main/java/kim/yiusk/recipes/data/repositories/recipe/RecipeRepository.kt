package kim.yiusk.recipes.data.repositories.recipe

import kim.yiusk.recipes.data.Success
import kim.yiusk.recipes.data.entities.Recipe
import kim.yiusk.recipes.util.AppCoroutineDispatchers
import kotlinx.coroutines.experimental.async
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author <a href="yisuk@mobilabsolutions.com">Yisuk Kim</a> on 07-09-2018.
 */
@Singleton
class RecipeRepository @Inject constructor(
        private val dispatchers: AppCoroutineDispatchers,
        private val localRecipeStore: LocalRecipeStore,
        private val remoteRecipeDataSource: RemoteRecipeDataSource

) {

    fun observeRecipe(recipeId: String) = localRecipeStore.observeRecipe(recipeId)

    suspend fun updateRecipe(recipeId: String) {
        val localRecipe = localRecipeStore.getRecipe(recipeId) ?: Recipe.EMPTY_RECIPE
        val remoteJob = async(dispatchers.io) { remoteRecipeDataSource.getRecipe(recipeId) }
        val remoteResult = remoteJob.await()
        if (remoteResult is Success) {
            localRecipeStore.saveArtist(mergeRecipe(localRecipe, remoteResult.data))
        }

    }

    private fun mergeRecipe(local: Recipe, remote: Recipe) =
            local.copy(
                    recipeId = remote.recipeId ?: local.recipeId,
                    publisher = remote.publisher ?: local.publisher,
                    f2fUrl = remote.f2fUrl ?: local.f2fUrl,
                    title = remote.title ?: local.title,
                    sourceUrl = remote.sourceUrl ?: local.sourceUrl,
                    imageUrl = remote.imageUrl ?: local.imageUrl,
                    socialRank = remote.socialRank ?: local.socialRank,
                    publisherUrl = remote.publisherUrl ?: local.publisherUrl,
                    ingredients = remote.ingredients ?: local.ingredients
            )

}