package kim.yiusk.recipes.data.mappers

/**
 * @author <a href="yisuk@mobilabsolutions.com">Yisuk Kim</a> on 07-09-2018.
 */
interface Mapper<F, T> {
    fun map(from: F): T
}