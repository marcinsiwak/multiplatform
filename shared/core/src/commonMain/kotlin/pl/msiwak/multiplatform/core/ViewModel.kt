package pl.msiwak.multiplatform.core

import kotlinx.coroutines.CoroutineScope

expect open class ViewModel() {
    protected val viewModelScope: CoroutineScope

    open fun onCleared()
}
