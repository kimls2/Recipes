package kim.yiusk.recipes.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import kim.yiusk.recipes.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author <a href="yisuk@mobilabsolutions.com">Yisuk Kim</a> on 07-09-2018.
 */
@Module
class NetworkModule {
    companion object {
        private const val BASE_URL = "http://food2fork.com/api/"
        private const val DISK_CACHE_SIZE = (10 * 1024 * 1024).toLong()
        private const val READ_TIME_OUT = 15.toLong()
        private const val CONNECT_TIME_OUT = 30.toLong()
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(@Named("food2fork-api-key") apiKey: String): AuthInterceptor {
        return AuthInterceptor(apiKey)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
            @Named("cache") cacheDir: File,
            authInterceptor: AuthInterceptor): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        builder.apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                addNetworkInterceptor(StethoInterceptor())
            }
            addInterceptor(authInterceptor)
            readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            cache(Cache(File(cacheDir, "app_cache"), DISK_CACHE_SIZE))
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .baseUrl(BASE_URL)
                .build()
    }

//    @Provides
//    @Singleton
//    fun provideArtistApi(retrofit: Retrofit): ArtistApi {
//        return retrofit.create(ArtistApi::class.java)
//    }
}