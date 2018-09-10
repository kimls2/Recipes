package kim.yiusk.recipes.network

import kim.yiusk.recipes.data.ErrorResult
import kim.yiusk.recipes.data.Result
import kim.yiusk.recipes.data.Success
import kim.yiusk.recipes.data.mappers.Mapper
import kim.yiusk.recipes.util.ext.bodyOrThrow
import kim.yiusk.recipes.util.ext.toException
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author <a href="yisuk@mobilabsolutions.com">yisuk</a>
 */
@Singleton
class RetrofitRunner @Inject constructor() {
    suspend fun <T, E> executeForResponse(mapper: Mapper<T, E>, request: suspend () -> Response<T>): Result<E> {
        return try {
            val response = request()
            if (response.isSuccessful) {
                val okHttpNetworkResponse = response.raw().networkResponse()
                val notModified = okHttpNetworkResponse == null || okHttpNetworkResponse.code() == 304
                Success(data = mapper.map(response.bodyOrThrow()), responseModified = !notModified)
            } else {
                ErrorResult(response.toException())
            }
        } catch (e: Exception) {
            ErrorResult(e)
        }
    }
}