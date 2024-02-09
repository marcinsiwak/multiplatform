package pl.msiwak.multiplatform.shared

import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import pl.msiwak.multiplatform.core.ViewModel

expect inline fun <reified T : ViewModel> Module.viewModelDefinition(
    qualifier: Qualifier? = null,
    noinline definition: Definition<T>
): KoinDefinition<T>