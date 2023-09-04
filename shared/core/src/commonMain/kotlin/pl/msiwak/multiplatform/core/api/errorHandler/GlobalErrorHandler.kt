package pl.msiwak.multiplatform.core.api.errorHandler

import dev.gitlive.firebase.auth.FirebaseAuthUserCollisionException
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineExceptionHandler

class GlobalErrorHandler {

    fun handleError(customHandle: (Throwable) -> Boolean = { _ -> false }) =
        CoroutineExceptionHandler { _, throwable: Throwable ->
            if (!customHandle(throwable)) {
                when (throwable) {
                    is FirebaseAuthUserCollisionException -> {
                        Napier.e("Same user error: $throwable")
                    }

                    else -> {
                        Napier.e("Error: $throwable")
                    }
                }
            }
        }
}