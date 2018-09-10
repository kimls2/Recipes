package kim.yiusk.recipes.home.recipe

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author <a href="yisuk@mobilabsolutions.com">yisuk</a>
 */
@Module
internal abstract class RecipeBuilder {
    @ContributesAndroidInjector
    internal abstract fun recipeFragment(): RecipeFragment
}