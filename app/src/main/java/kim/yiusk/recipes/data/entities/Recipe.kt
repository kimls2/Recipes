package kim.yiusk.recipes.data.entities

import android.arch.persistence.room.*
import java.util.*

/**
 * @author <a href="yisuk@mobilabsolutions.com">Yisuk Kim</a> on 07-09-2018.
 */
@Entity(
        tableName = "recipes",
        indices = [
            Index(value = ["recipe_id"], unique = true)
        ]
)
data class Recipe(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
        @ColumnInfo(name = "recipe_id") val recipeId: String? = null,
        @ColumnInfo(name = "publisher") val publisher: String? = null,
        @ColumnInfo(name = "f2f_url") val f2fUrl: String? = null,
        @ColumnInfo(name = "title") val title: String? = null,
        @ColumnInfo(name = "source_url") val sourceUrl: String? = null,
        @ColumnInfo(name = "image_url") val imageUrl: String? = null,
        @ColumnInfo(name = "social_rank") val socialRank: Int? = null,
        @ColumnInfo(name = "publisher_url") val publisherUrl: String? = null,
        @ColumnInfo(name = "ingredients") val ingredients: String? = null
) {
    @Ignore constructor() : this(0)

    companion object {
        val EMPTY_RECIPE = Recipe()
    }

    fun generateStableId(): Long {
        return Objects.hash(Recipe::class, this.id).toLong()
    }
}