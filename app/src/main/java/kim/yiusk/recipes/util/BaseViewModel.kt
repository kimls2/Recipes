package kim.yiusk.recipes.util

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState
import io.reactivex.disposables.CompositeDisposable
import kim.yiusk.recipes.domain.UseCase
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.launch

/**
 * @author <a href="yisuk@mobilabsolutions.com">Yisuk Kim</a> on 07-09-2018.
 */
open class BaseMvRxViewModel<S : MvRxState>(
        initialState: S
) : BaseMvRxViewModel<S>(initialState, debugMode = false) {
    val viewModelJob = Job()
    val disposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
        viewModelJob.cancel()
    }

    fun <P> launchInteractor(interactor: UseCase<P>, param: P) =
            launch(context = interactor.dispatcher, parent = viewModelJob, block = { interactor(param) })
}