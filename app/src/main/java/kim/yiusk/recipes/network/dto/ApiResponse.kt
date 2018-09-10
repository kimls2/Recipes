package kim.yiusk.recipes.network.dto

import com.google.gson.annotations.SerializedName

/**
 * @author <a href="yisuk@mobilabsolutions.com">yisuk</a>
 */
data class SearchResponse(
        val count: Int,
        val recipes: List<FoodPorkRecipe>
)

data class RecipeResponse(
        val recipe: FoodPorkRecipe
)

data class FoodPorkRecipe(
        @SerializedName("recipe_id")
        val recipeId: String,
        val publisher: String,
        @SerializedName("f2f_url")
        val f2fUrl: String,
        val title: String,
        @SerializedName("source_url")
        val sourceUrl: String,
        @SerializedName("image_url")
        val imageUrl: String,
        val socialRank: Int,
        @SerializedName("publisher_url")
        val publisherUrl: String,
        val ingredients: List<String>
)