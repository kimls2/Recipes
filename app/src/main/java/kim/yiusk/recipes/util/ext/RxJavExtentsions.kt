package kim.yiusk.recipes.util.ext

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * @author <a href="yisuk@mobilabsolutions.com">Yisuk Kim</a> on 07-09-2018.
 */

fun <T> Observable<T>.toFlowable() = toFlowable(BackpressureStrategy.LATEST)!!

fun <T> emptyFlowableList() = Flowable.just(emptyList<T>())