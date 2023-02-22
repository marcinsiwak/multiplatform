package pl.msiwak.multiplatform.di

import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.instance.InstanceFactory
import org.koin.core.module.Module
import org.koin.core.module.factory
import org.koin.core.qualifier.Qualifier
import pl.msiwak.multiplatform.ViewModel

actual inline fun <reified T : ViewModel> Module.viewModelDefinition(
    qualifier: Qualifier?,
    noinline definition: Definition<T>,
): KoinDefinition<T> = factory(qualifier = qualifier, definition = definition)