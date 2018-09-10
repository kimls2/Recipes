package kim.yiusk.recipes.di

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

/**
 * @author <a href="yisuk@mobilabsolutions.com">yisuk</a>
 */
@AssistedModule
@Module(includes = [AssistedInject_AppAssistedModule::class])
abstract class AppAssistedModule