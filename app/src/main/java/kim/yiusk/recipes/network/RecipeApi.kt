package kim.yiusk.recipes.network

import kim.yiusk.recipes.network.dto.RecipeResponse
import kim.yiusk.recipes.network.dto.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author <a href="yisuk@mobilabsolutions.com">yisuk</a>
 */
interface RecipeApi {
    @GET("search")
    fun searchRecipes(
            @Query("q") title: String,
            @Query("sort") sort: String = "r"
    ): Call<SearchResponse>

    @GET("get")
    fun getRecipe(
            @Query("rId") recipeId: String
    ): Call<RecipeResponse>
}