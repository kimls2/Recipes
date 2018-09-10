package kim.yiusk.recipes.home.recipe

import android.support.v4.app.FragmentActivity
import com.airbnb.mvrx.MvRxViewModelFactory
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.BehaviorSubject
import kim.yiusk.recipes.domain.GetRecipe
import kim.yiusk.recipes.domain.SearchRecipes
import kim.yiusk.recipes.home.HomeActivity
import kim.yiusk.recipes.util.AppRxSchedulers
import kim.yiusk.recipes.util.BaseViewModel
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * @author <a href="yisuk@mobilabsolutions.com">yisuk</a>
 */
class RecipeViewModel @AssistedInject constructor(
        @Assisted initialState: RecipeFragmentViewState,
        schedulers: AppRxSchedulers,
        private val getRecipe: GetRecipe,
        private val searchRecipes: SearchRecipes
) : BaseViewModel<RecipeFragmentViewState>(initialState) {

    @AssistedInject.Factory
    interface Factory {
        fun create(initialState: RecipeFragmentViewState): RecipeViewModel
    }

    companion object : MvRxViewModelFactory<RecipeFragmentViewState> {
        @JvmStatic
        override fun create(activity: FragmentActivity, state: RecipeFragmentViewState): RecipeViewModel {
            return (activity as HomeActivity).recipeViewModelViewModelFactory.create(state)
        }
    }

    private val randomIds = listOf("49700", "397170", "54472", "dddc54", "35191", "846e3b", "12811", "ba2e74", "78705f", "8d53c6")
    private val randomId = randomIds.shuffled().take(1)[0]
    private val randomTitle = "pasta"

    private var searchQuery = BehaviorSubject.createDefault(randomTitle)

    init {
        disposables += searchQuery.observeOn(schedulers.main)
                .debounce(500, TimeUnit.MILLISECONDS, schedulers.io)
                .distinctUntilChanged()
                .subscribe(this::runSearchQuery, Timber::e)

        getRecipe.observe()
                .toObservable()
                .subscribeOn(schedulers.io)
                .execute {
                    copy(recipe = it)
                }
        searchRecipes.observe()
                .toObservable()
                .subscribeOn(schedulers.io)
                .execute {
                    copy(searchResults = it)
                }

        getRecipe.setParams(randomId)
        refresh()
    }

    override fun onCleared() {
        super.onCleared()
        getRecipe.clear()
        searchRecipes.clear()
    }

    private fun refresh() {
        launchInteractor(getRecipe, Unit)
    }

    private fun runSearchQuery(query: String) {
        launchInteractor(searchRecipes, query)
    }

}