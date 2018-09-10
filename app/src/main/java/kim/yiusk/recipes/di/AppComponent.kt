package kim.yiusk.recipes.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import kim.yiusk.recipes.RecipesApplication
import kim.yiusk.recipes.data.DatabaseModule
import kim.yiusk.recipes.home.HomeBuilder
import kim.yiusk.recipes.network.NetworkModule
import javax.inject.Singleton

/**
 * @author <a href="yisuk@mobilabsolutions.com">Yisuk Kim</a> on 07-09-2018.
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    NetworkModule::class,
    DatabaseModule::class,
    HomeBuilder::class,
    AppAssistedModule::class
])
interface AppComponent : AndroidInjector<RecipesApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<RecipesApplication>()
}