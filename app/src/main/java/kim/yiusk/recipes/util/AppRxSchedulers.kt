package kim.yiusk.recipes.util

import io.reactivex.Scheduler

/**
 * @author <a href="yisuk@mobilabsolutions.com">Yisuk Kim</a> on 07-09-2018.
 */
data class AppRxSchedulers(
        val io: Scheduler,
        val computation: Scheduler,
        val main: Scheduler
)