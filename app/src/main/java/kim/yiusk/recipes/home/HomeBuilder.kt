package kim.yiusk.recipes.home

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kim.yiusk.recipes.home.recipe.RecipeBuilder

/**
 * @author <a href="yisuk@mobilabsolutions.com">yisuk</a>
 */
@Module
internal abstract class HomeBuilder {
    @ContributesAndroidInjector(modules = [
        RecipeBuilder::class

    ])
    internal abstract fun homeActivity(): HomeActivity
}