package kim.yiusk.recipes.data

import java.util.concurrent.Callable

interface DatabaseTransactionRunner {
    operator fun <T> invoke(run: () -> T): T
}

class RoomTransactionRunner(private val db: RecipesDatabase) : DatabaseTransactionRunner {
    override operator fun <T> invoke(run: () -> T): T = db.runInTransaction(Callable<T> { run() })
}