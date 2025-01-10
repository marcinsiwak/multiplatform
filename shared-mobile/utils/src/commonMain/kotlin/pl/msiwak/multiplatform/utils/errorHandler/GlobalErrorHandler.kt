package pl.msiwak.multiplatform.utils.errorHandler

import co.touchlab.kermit.Logger
import kotlinx.coroutines.CoroutineExceptionHandler

class GlobalErrorHandler {

    fun handleError(customHandle: (Throwable) -> Boolean = { _ -> false }) =
        CoroutineExceptionHandler { _, throwable: Throwable ->
            if (!customHandle(throwable)) {
                when (throwable) {
//                    is FirebaseAuthUserCollisionException -> {
//                        Logger.e("Same user error: $throwable")
//                    }

                    else -> {
                        println("ERrror: ${throwable.message}")
//                        Logger.e("Error: $throwable")
                    }
                }
            }
        }
}
