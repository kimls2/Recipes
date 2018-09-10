package kim.yiusk.recipes.home.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import kim.yiusk.recipes.databinding.FragmentRecipeBinding
import kim.yiusk.recipes.util.BaseFragment

/**
 * @author <a href="yisuk@mobilabsolutions.com">yisuk</a>
 */
class RecipeFragment : BaseFragment() {

    private val viewModel: RecipeViewModel by fragmentViewModel()
    private lateinit var binding: FragmentRecipeBinding
    private lateinit var controller: RecipeEpoxyController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller = RecipeEpoxyController()
        binding.searchRv.setController(controller)
    }

    override fun invalidate() {
        withState(viewModel) {
            binding.state = it
            controller.setData(it)
        }
    }
}