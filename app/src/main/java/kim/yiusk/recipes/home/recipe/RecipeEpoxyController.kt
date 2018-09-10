package kim.yiusk.recipes.home.recipe

import com.airbnb.epoxy.TypedEpoxyController
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Success
import kim.yiusk.recipes.SearchResultItemBindingModel_
import kim.yiusk.recipes.data.entities.Recipe
import kim.yiusk.recipes.detailsTitle
import kim.yiusk.recipes.util.ext.carousel
import kim.yiusk.recipes.util.ext.withModelsFrom
import kim.yiusk.recipes.util.ui.TotalSpanOverride

/**
 * @author <a href="yisuk@mobilabsolutions.com">yisuk</a>
 */
class RecipeEpoxyController : TypedEpoxyController<RecipeFragmentViewState>() {

    override fun buildModels(state: RecipeFragmentViewState) {
        buildRecipeModel(state.recipe)
        buildSearchItemsModels(state.searchResults)
    }

    private fun buildRecipeModel(asyncRecipe: Async<Recipe>) {
        when (asyncRecipe) {
            is Success -> {
                val recipe = asyncRecipe()
                detailsTitle {
                    id("details_title")
                    recipe(recipe)
                    spanSizeOverride(TotalSpanOverride)
                }
            }
        }
    }

    private fun buildSearchItemsModels(searchResults: Async<List<Recipe>>) {
        when (searchResults) {
            is Success -> {
                val results = searchResults()
                if (results.isNotEmpty()) {
                    carousel {
                        id("search_results")
                        numViewsToShowOnScreen(2.25f)
                        hasFixedSize(true)
                        withModelsFrom(results) {
                            SearchResultItemBindingModel_()
                                    .id("result_${it.generateStableId()}")
                                    .recipe(it)
                        }
                    }
                }
            }
        }
    }
}