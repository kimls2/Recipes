package kim.yiusk.recipes.util

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 * @author <a href="yisuk@mobilabsolutions.com">Yisuk Kim</a> on 10-09-2018.
 */
class RxLoadingCounter {
    private var loaders = 0
    private val loadingState = BehaviorSubject.createDefault(loaders)

    val observable: Observable<Boolean>
        get() = loadingState.map { it > 0 }

    fun addLoader() {
        loadingState.onNext(++loaders)
    }

    fun removeLoader() {
        loadingState.onNext(--loaders)
    }
}