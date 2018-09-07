package kim.yiusk.recipes.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kim.yiusk.recipes.BuildConfig
import kim.yiusk.recipes.RecipesApplication
import kim.yiusk.recipes.util.AppCoroutineDispatchers
import kim.yiusk.recipes.util.AppRxSchedulers
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.rx2.asCoroutineDispatcher
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author <a href="yisuk@mobilabsolutions.com">Yisuk Kim</a> on 03-09-2018.
 */
@Module
class AppModule {
    @Provides
    fun provideContext(application: RecipesApplication): Context = application.applicationContext

    @Provides
    @Singleton
    @Named("cache")
    fun provideCacheDir(application: RecipesApplication): File = application.cacheDir

    @Named("app")
    @Provides
    @Singleton
    fun provideAppPreferences(application: RecipesApplication): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

    @Singleton
    @Provides
    fun provideRxSchedulers(): AppRxSchedulers = AppRxSchedulers(
            io = Schedulers.io(),
            computation = Schedulers.computation(),
            main = AndroidSchedulers.mainThread()
    )

    @Singleton
    @Provides
    fun provideCoroutineDispatchers(schedulers: AppRxSchedulers) = AppCoroutineDispatchers(
            io = schedulers.io.asCoroutineDispatcher(),
            computation = schedulers.computation.asCoroutineDispatcher(),
            main = UI
    )

    @Provides
    @Named("food2fork-api-key")
    fun provideApiKey(): String = BuildConfig.FOOD2PORK_APIKEY

//    @Provides
//    @Singleton
//    @Named("app")
//    fun provideAppNavigator(context: Context): AppNavigator {
//        return ArtistAppNavigator(context)
//    }
}