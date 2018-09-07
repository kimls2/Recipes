package kim.yiusk.recipes.data

import android.arch.persistence.room.Room
import android.content.Context
import android.os.Debug
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author <a href="yisuk@mobilabsolutions.com">Yisuk Kim</a> on 07-09-2018.
 */
@Module
class DatabaseModule {
    companion object {
        private const val DATABASE_NAME = "recipes.db"
    }

    @Singleton
    @Provides
    fun provideDatabase(context: Context): RecipesDatabase {
        val builder = Room.databaseBuilder(context, RecipesDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
        if (Debug.isDebuggerConnected()) {
            builder.allowMainThreadQueries()
        }
        return builder.build()
    }

    @Provides
    fun providerecipeDao(database: RecipesDatabase) = database.recipeDao()

    @Singleton
    @Provides
    fun provideDatabaseTransactionRunner(db: RecipesDatabase): DatabaseTransactionRunner = RoomTransactionRunner(db)

}