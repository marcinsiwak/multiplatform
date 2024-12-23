package pl.msiwak.infrastructure.di

import org.koin.dsl.module
import pl.msiwak.domain.usecases.GetCategoryUseCase
import pl.msiwak.domain.usecases.GetCategoryUseCaseImpl
import pl.msiwak.domain.usecases.GetExerciseUseCase
import pl.msiwak.domain.usecases.GetExerciseUseCaseImpl
import pl.msiwak.domain.usecases.GetUserUseCase
import pl.msiwak.domain.usecases.GetUserUseCaseImpl
import pl.msiwak.domain.usecases.RemoveCategoryUseCase
import pl.msiwak.domain.usecases.RemoveCategoryUseCaseImpl
import pl.msiwak.domain.usecases.RemoveExerciseUseCase
import pl.msiwak.domain.usecases.RemoveExerciseUseCaseImpl
import pl.msiwak.domain.usecases.RemoveResultUseCase
import pl.msiwak.domain.usecases.RemoveResultUseCaseImpl
import pl.msiwak.domain.usecases.SynchronizeDataUseCase
import pl.msiwak.domain.usecases.SynchronizeDataUseCaseImpl
import pl.msiwak.infrastructure.config.auth.roles.RoleManager
import pl.msiwak.infrastructure.database.dao.exercise.ExercisesDao
import pl.msiwak.infrastructure.database.dao.exercise.ExercisesDaoImpl
import pl.msiwak.infrastructure.database.dao.user.UserDao
import pl.msiwak.infrastructure.database.dao.user.UserDaoImpl
import pl.msiwak.infrastructure.repositories.ExerciseRepository
import pl.msiwak.infrastructure.repositories.UserRepository
import pl.msiwak.interfaces.controller.ExerciseController
import pl.msiwak.interfaces.controller.ExerciseControllerImpl
import pl.msiwak.interfaces.controller.UserController
import pl.msiwak.interfaces.controller.UserControllerImpl
import pl.msiwak.interfaces.mapper.ApiCategoryMapper
import pl.msiwak.interfaces.mapper.ApiExerciseMapper
import pl.msiwak.interfaces.mapper.ApiResultMapper
import pl.msiwak.interfaces.mapper.ApiUserMapper

val diModule = module {
    single<pl.msiwak.domain.usecases.AddUserUseCase> { pl.msiwak.domain.usecases.AddUserUseCaseImpl(get()) }
    single<pl.msiwak.domain.usecases.AddCategoryUseCase> {
        pl.msiwak.domain.usecases.AddCategoryUseCaseImpl(
            get(),
            get()
        )
    }
    single<pl.msiwak.domain.usecases.AddExerciseUseCase> {
        pl.msiwak.domain.usecases.AddExerciseUseCaseImpl(
            get(),
            get()
        )
    }
    single<pl.msiwak.domain.usecases.AddResultUseCase> { pl.msiwak.domain.usecases.AddResultUseCaseImpl(get(), get()) }
    single<GetUserUseCase> { GetUserUseCaseImpl(get(), get()) }
    single<GetCategoryUseCase> { GetCategoryUseCaseImpl(get(), get()) }
    single<GetExerciseUseCase> { GetExerciseUseCaseImpl(get(), get()) }
    single<pl.msiwak.domain.usecases.GetCategoriesUseCase> {
        pl.msiwak.domain.usecases.GetCategoriesUseCaseImpl(
            get(),
            get()
        )
    }
    single<RemoveCategoryUseCase> { RemoveCategoryUseCaseImpl(get()) }
    single<RemoveExerciseUseCase> { RemoveExerciseUseCaseImpl(get()) }
    single<RemoveResultUseCase> { RemoveResultUseCaseImpl(get()) }
    single<SynchronizeDataUseCase> { SynchronizeDataUseCaseImpl(get()) }
}

val diRepositoryModule = module {
    single { UserRepository(get(), get()) }
    single { ExerciseRepository(get()) }
}

val diDaoModule = module {
    single<UserDao> { UserDaoImpl() }
    single<ExercisesDao> { ExercisesDaoImpl() }
}

val diMapperModule = module {
    single { ApiCategoryMapper(get()) }
    single { ApiExerciseMapper(get()) }
    single { ApiResultMapper() }
    single { ApiUserMapper() }
}

val diControllerModule = module {
    single<UserController> { UserControllerImpl(get(), get()) }
    single<ExerciseController> {
        ExerciseControllerImpl(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
}

val diUtilsModule = module {
    single { RoleManager() }
}
