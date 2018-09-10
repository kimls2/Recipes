package kim.yiusk.recipes.data.repositories.search

import kim.yiusk.recipes.data.Success
import kim.yiusk.recipes.data.entities.Recipe
import kim.yiusk.recipes.data.repositories.recipe.LocalRecipeStore
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author <a href="yisuk@mobilabsolutions.com">yisuk</a>
 */
@Singleton
class SearchRepository @Inject constructor(
        private val localSearchStore: LocalSearchStore,
        private val localRecipeStore: LocalRecipeStore,
        private val remoteSearchDataSource: RemoteSearchDataSource
) {

    suspend fun search(title: String): List<Recipe> {
        val remoteResult = remoteSearchDataSource.search(title)

        return when (remoteResult) {
            is Success -> remoteResult.data.map {
                val id = localRecipeStore.getIdOrSavePlaceholder(it)
                localRecipeStore.getRecipeWithId(id)!!
            }
            else -> emptyList()
        }
    }
}