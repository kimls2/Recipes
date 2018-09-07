package kim.yiusk.recipes.util

import kotlinx.coroutines.experimental.CoroutineDispatcher

/**
 * @author <a href="yisuk@mobilabsolutions.com">Yisuk Kim</a> on 07-09-2018.
 */
data class AppCoroutineDispatchers(
        val io: CoroutineDispatcher,
        val computation: CoroutineDispatcher,
        val main: CoroutineDispatcher
)