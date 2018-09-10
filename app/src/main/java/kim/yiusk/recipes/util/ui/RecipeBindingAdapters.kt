package kim.yiusk.recipes.util.ui

import android.databinding.BindingAdapter
import android.widget.ImageView

/**
 * @author <a href="yisuk@mobilabsolutions.com">yisuk</a>
 */
@BindingAdapter("recipeImageUrl")
fun loadPoster(view: ImageView, imageUrl: String?) {
    GlideApp.with(view).clear(view)
    if (imageUrl != null) {
//        view.doOnLayout {
//        }
        GlideApp.with(view)
                .load(imageUrl)
                .into(view)
    }
}