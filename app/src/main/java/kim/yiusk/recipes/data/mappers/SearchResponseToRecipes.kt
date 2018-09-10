package kim.yiusk.recipes.data.mappers

import kim.yiusk.recipes.data.entities.Recipe
import kim.yiusk.recipes.network.dto.SearchResponse
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author <a href="yisuk@mobilabsolutions.com">yisuk</a>
 */
@Singleton
class SearchResponseToRecipes @Inject constructor() : Mapper<SearchResponse, List<Recipe>> {

    override fun map(from: SearchResponse): List<Recipe> {
        return from.recipes.map { foodPorkRecipe ->
            Recipe(
                    recipeId = foodPorkRecipe.recipeId,
                    publisher = foodPorkRecipe.publisher,
                    f2fUrl = foodPorkRecipe.f2fUrl,
                    title = foodPorkRecipe.title,
                    sourceUrl = foodPorkRecipe.sourceUrl,
                    imageUrl = foodPorkRecipe.imageUrl,
                    socialRank = foodPorkRecipe.socialRank,
                    publisherUrl = foodPorkRecipe.publisherUrl
            )
        }
    }
}