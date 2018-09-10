package kim.yiusk.recipes.util.ui

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet

/**
 * @author <a href="yisuk@mobilabsolutions.com">yisuk</a>
 */
class TwoThreeImageView(context: Context?, attrs: AttributeSet?) : AppCompatImageView(context, attrs) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec) * 3 / 2, MeasureSpec.EXACTLY)
        super.onMeasure(widthMeasureSpec, height)
    }
}