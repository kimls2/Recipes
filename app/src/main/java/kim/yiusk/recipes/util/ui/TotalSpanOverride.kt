package kim.yiusk.recipes.util.ui

import com.airbnb.epoxy.EpoxyModel

/**
 * @author <a href="yisuk@mobilabsolutions.com">Yisuk Kim</a> on 10-09-2018.
 */
object TotalSpanOverride : EpoxyModel.SpanSizeOverrideCallback {
    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int) = totalSpanCount
}