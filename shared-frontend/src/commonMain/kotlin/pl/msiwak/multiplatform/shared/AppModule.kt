package pl.msiwak.multiplatform.shared

import pl.msiwak.multiplatform.shared.modules.apiModule
import pl.msiwak.multiplatform.shared.modules.authModule
import pl.msiwak.multiplatform.shared.modules.databaseModule
import pl.msiwak.multiplatform.shared.modules.helpersModule
import pl.msiwak.multiplatform.shared.modules.navigationModule
import pl.msiwak.multiplatform.shared.modules.platformRepositoryModule
import pl.msiwak.multiplatform.shared.modules.repositoryModule
import pl.msiwak.multiplatform.shared.modules.serviceModule
import pl.msiwak.multiplatform.shared.modules.storeModule
import pl.msiwak.multiplatform.shared.modules.useCaseModule
import pl.msiwak.multiplatform.shared.modules.viewModelsModule

fun appModule() = listOf(
    apiModule,
    navigationModule,
    databaseModule,
    helpersModule,
    storeModule,
    serviceModule,
    repositoryModule,
    platformRepositoryModule,
    useCaseModule,
    viewModelsModule,
    authModule
)
