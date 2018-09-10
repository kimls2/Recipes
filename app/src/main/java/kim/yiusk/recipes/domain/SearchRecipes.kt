package kim.yiusk.recipes.domain

import kim.yiusk.recipes.data.entities.Recipe
import kim.yiusk.recipes.data.repositories.search.SearchRepository
import kim.yiusk.recipes.util.AppCoroutineDispatchers
import kotlinx.coroutines.experimental.CoroutineDispatcher
import javax.inject.Inject

/**
 * @author <a href="yisuk@mobilabsolutions.com">yisuk</a>
 */
class SearchRecipes @Inject constructor(
        dispatchers: AppCoroutineDispatchers,
        private val searchRepository: SearchRepository
) : ChannelUseCase<String, List<Recipe>>() {
    override val dispatcher: CoroutineDispatcher = dispatchers.io

    override suspend fun execute(executeParams: String): List<Recipe> {
        return searchRepository.search(executeParams)
    }
}