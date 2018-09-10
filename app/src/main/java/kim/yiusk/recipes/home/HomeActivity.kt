package kim.yiusk.recipes.home

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import kim.yiusk.recipes.R
import kim.yiusk.recipes.home.recipe.RecipeFragment
import kim.yiusk.recipes.home.recipe.RecipeViewModel
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var recipeViewModelViewModelFactory: RecipeViewModel.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.home_content, RecipeFragment())
                .commit()
    }


}
