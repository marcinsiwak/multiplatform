package pl.msiwak.multiplatform.core

import kotlin.native.concurrent.ThreadLocal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@ThreadLocal
var createViewModelScope: () -> CoroutineScope = {
    CoroutineScope(Dispatchers.Main + SupervisorJob())
}