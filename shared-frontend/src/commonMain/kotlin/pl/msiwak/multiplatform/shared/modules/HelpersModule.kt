package pl.msiwak.multiplatform.shared.modules

import org.koin.dsl.module
import pl.msiwak.multiplatform.network.mapper.CategoryMapper
import pl.msiwak.multiplatform.network.mapper.ExerciseMapper
import pl.msiwak.multiplatform.network.mapper.ResultMapper
import pl.msiwak.multiplatform.network.mapper.UserMapper
import pl.msiwak.multiplatform.notifications.NotificationsManager
import pl.msiwak.multiplatform.permissionmanager.PermissionBridge
import pl.msiwak.multiplatform.utils.DateFormatter
import pl.msiwak.multiplatform.utils.NumberFormatter
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler
import pl.msiwak.multiplatform.utils.validators.ResultValidator
import pl.msiwak.multiplatform.utils.validators.Validator

val helpersModule = module {
    single { GlobalErrorHandler() }
    single { Validator() }
    single { ResultValidator() }
    single { DateFormatter() }
    single { NumberFormatter() }
    single { UserMapper() }
    single { ExerciseMapper(get()) }
    single { ResultMapper() }
    single { CategoryMapper(get()) }
    single { NotificationsManager() }
    single { PermissionBridge() }
}