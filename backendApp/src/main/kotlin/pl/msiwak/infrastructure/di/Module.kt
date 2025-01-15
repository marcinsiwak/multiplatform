package pl.msiwak.infrastructure.di

import org.koin.dsl.module
import pl.msiwak.domain.usecases.AddCategoryUseCase
import pl.msiwak.domain.usecases.AddCategoryUseCaseImpl
import pl.msiwak.domain.usecases.AddExerciseUseCase
import pl.msiwak.domain.usecases.AddExerciseUseCaseImpl
import pl.msiwak.domain.usecases.AddResultUseCase
import pl.msiwak.domain.usecases.AddResultUseCaseImpl
import pl.msiwak.domain.usecases.AddUserUseCase
import pl.msiwak.domain.usecases.AddUserUseCaseImpl
import pl.msiwak.domain.usecases.GetCategoriesUseCase
import pl.msiwak.domain.usecases.GetCategoriesUseCaseImpl
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
import pl.msiwak.domain.usecases.UpdateUserUseCase
import pl.msiwak.domain.usecases.UpdateUserUseCaseImpl
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
    single<AddUserUseCase> { AddUserUseCaseImpl(get()) }
    single<UpdateUserUseCase> { UpdateUserUseCaseImpl(get()) }
    single<AddCategoryUseCase> {
        AddCategoryUseCaseImpl(
            get(),
            get()
        )
    }
    single<AddExerciseUseCase> {
        AddExerciseUseCaseImpl(
            get(),
            get()
        )
    }
    single<AddResultUseCase> { AddResultUseCaseImpl(get(), get()) }
    single<GetUserUseCase> { GetUserUseCaseImpl(get(), get()) }
    single<GetCategoryUseCase> { GetCategoryUseCaseImpl(get(), get()) }
    single<GetExerciseUseCase> { GetExerciseUseCaseImpl(get(), get()) }
    single<GetCategoriesUseCase> {
        GetCategoriesUseCaseImpl(
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
    single<UserController> { UserControllerImpl(get(), get(), get()) }
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
