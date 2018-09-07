package kim.yiusk.recipes.data

/**
 * @author <a href="yisuk@mobilabsolutions.com">Yisuk Kim</a> on 07-09-2018.
 */

sealed class Result<T>

data class Success<T>(val data: T, val responseModified: Boolean = true) : Result<T>()

data class ErrorResult<T>(val exception: Exception) : Result<T>()