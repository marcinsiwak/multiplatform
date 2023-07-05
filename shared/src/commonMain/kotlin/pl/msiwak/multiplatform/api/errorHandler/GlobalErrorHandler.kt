package pl.msiwak.multiplatform.api.errorHandler

//import dev.gitlive.firebase.auth.FirebaseAuthUserCollisionException
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineExceptionHandler

class GlobalErrorHandler {

    fun handleError() = CoroutineExceptionHandler { _, throwable: Throwable ->
        when(throwable) {
//            is FirebaseAuthUserCollisionException -> {
//                Napier.e("Same user error: $throwable")
//            }
            else -> {
                Napier.e("Error: $throwable")
            }
        }
    }
}