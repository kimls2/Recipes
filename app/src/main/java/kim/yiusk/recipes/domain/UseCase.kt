package kim.yiusk.recipes.domain

import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import kim.yiusk.recipes.util.ext.toFlowable
import kotlinx.coroutines.experimental.CoroutineDispatcher
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.rx2.asObservable

/**
 * @author <a href="yisuk@mobilabsolutions.com">Yisuk Kim</a> on 07-09-2018.
 */
interface UseCase<in P> {
    val dispatcher: CoroutineDispatcher
    suspend operator fun invoke(executeParams: P)
}

abstract class SubjectUseCase<P : Any, EP, T> : UseCase<EP> {

    private var disposable: Disposable? = null
    private val subject: BehaviorSubject<T> = BehaviorSubject.create()

    val loading = BehaviorSubject.createDefault(false)

    private lateinit var params: P

    fun setParams(params: P) {
        this.params = params
        setSource(createObservable(params))
    }

    final override suspend fun invoke(executeParams: EP) {
        try {
            loading.onNext(true)
            execute(this.params, executeParams)
            loading.onNext(false)
        } catch (t: Throwable) {
            loading.onError(t)
        }
    }

    protected abstract fun createObservable(params: P): Flowable<T>

    protected abstract suspend fun execute(params: P, executeParams: EP)

    fun clear() {
        disposable?.dispose()
        disposable = null
    }

    fun observe(): Flowable<T> = subject.toFlowable()

    private fun setSource(source: Flowable<T>) {
        disposable?.dispose()
        disposable = source.subscribe(subject::onNext, subject::onError)
    }
}

abstract class ChannelUseCase<P, T> : UseCase<P> {
    private val channel = Channel<T>()

    final override suspend fun invoke(executeParams: P) {
        channel.offer(execute(executeParams))
    }

    fun observe(): Flowable<T> = channel.asObservable(dispatcher).toFlowable()

    protected abstract suspend fun execute(executeParams: P): T

    fun clear() {
        channel.close()
    }
}