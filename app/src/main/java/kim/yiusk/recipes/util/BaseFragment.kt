package kim.yiusk.recipes.util

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import com.airbnb.mvrx.MvRxView
import com.airbnb.mvrx.MvRxViewModelStore
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * @author <a href="yisuk@mobilabsolutions.com">Yisuk Kim</a> on 07-09-2018.
 */
abstract class BaseFragment : DaggerFragment(), MvRxView {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val mvrxViewModelStore by lazy { MvRxViewModelStore(viewModelStore) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mvrxViewModelStore.restoreViewModels(this, savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mvrxViewModelStore.saveViewModels(outState)
    }

    override fun onStart() {
        super.onStart()
        postInvalidate()
    }
}