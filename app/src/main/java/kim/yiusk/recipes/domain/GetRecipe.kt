package kim.yiusk.recipes.domain

import io.reactivex.Flowable
import kim.yiusk.recipes.data.entities.Recipe
import kim.yiusk.recipes.data.repositories.recipe.RecipeRepository
import kim.yiusk.recipes.util.AppCoroutineDispatchers
import kim.yiusk.recipes.util.AppRxSchedulers
import kotlinx.coroutines.experimental.CoroutineDispatcher
import javax.inject.Inject

/**
 * @author <a href="yisuk@mobilabsolutions.com">yisuk</a>
 */
class GetRecipe @Inject constructor(
        dispatchers: AppCoroutineDispatchers,
        private val schedulers: AppRxSchedulers,
        private val recipeRepository: RecipeRepository
) : SubjectUseCase<String, Unit, Recipe>() {
    override val dispatcher: CoroutineDispatcher = dispatchers.io

    override fun createObservable(params: String): Flowable<Recipe> {
        return recipeRepository.observeRecipe(params)
                .subscribeOn(schedulers.io)
    }

    override suspend fun execute(params: String, executeParams: Unit) {
        recipeRepository.updateRecipe(params)
    }
}