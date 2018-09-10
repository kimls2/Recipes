package kim.yiusk.recipes.home.recipe

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import kim.yiusk.recipes.data.entities.Recipe

/**
 * @author <a href="yisuk@mobilabsolutions.com">yisuk</a>
 */
data class RecipeFragmentViewState(
        val recipe: Async<Recipe> = Uninitialized,
        val searchResults: Async<List<Recipe>> = Uninitialized
) : MvRxState {
}