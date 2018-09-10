package kim.yiusk.recipes.util.ui

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import androidx.core.view.doOnLayout
import kim.yiusk.recipes.R

/**
 * @author <a href="yisuk@mobilabsolutions.com">yisuk</a>
 */
@BindingAdapter("recipeImageUrl")
fun loadPoster(view: ImageView, imageUrl: String?) {
    GlideApp.with(view).clear(view)
    if (imageUrl != null) {
        view.doOnLayout {
            GlideApp.with(view)
                    .load(imageUrl)
                    .placeholder(R.color.grey)
                    .into(view)
        }
    }
}

@BindingAdapter("visibleIfShow")
fun visibleIfShow(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}