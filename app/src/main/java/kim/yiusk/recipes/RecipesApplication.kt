package kim.yiusk.recipes

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import kim.yiusk.recipes.di.DaggerAppComponent

/**
 * @author <a href="yisuk@mobilabsolutions.com">Yisuk Kim</a> on 07-09-2018.
 */
class RecipesApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}