package kim.yiusk.recipes.data.mappers

import kim.yiusk.recipes.data.entities.Recipe
import kim.yiusk.recipes.network.dto.RecipeResponse
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author <a href="yisuk@mobilabsolutions.com">yisuk</a>
 */
@Singleton
class RecipeResponseToRecipeEntity @Inject constructor() : Mapper<RecipeResponse, Recipe> {
    override fun map(from: RecipeResponse): Recipe {
        return Recipe(
                recipeId = from.recipe.recipeId,
                publisher = from.recipe.publisher,
                f2fUrl = from.recipe.f2fUrl,
                title = from.recipe.title,
                sourceUrl = from.recipe.sourceUrl,
                imageUrl = from.recipe.imageUrl,
                socialRank = from.recipe.socialRank,
                publisherUrl = from.recipe.publisherUrl
        )
    }
}