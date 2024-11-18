package pl.msiwak.infrastructure.di

import org.koin.dsl.module
import pl.msiwak.application.usecases.*
import pl.msiwak.domain.repositories.ExerciseRepository
import pl.msiwak.domain.repositories.UserRepository
import pl.msiwak.infrastructure.database.dao.exercise.ExercisesDao
import pl.msiwak.infrastructure.database.dao.exercise.ExercisesDaoImpl
import pl.msiwak.infrastructure.database.dao.user.UserDao
import pl.msiwak.infrastructure.database.dao.user.UserDaoImpl
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
    single<AddCategoryUseCase> { AddCategoryUseCaseImpl(get(), get()) }
    single<AddExerciseUseCase> { AddExerciseUseCaseImpl(get(), get()) }
    single<AddResultUseCase> { AddResultUseCaseImpl(get(), get()) }
    single<GetUserUseCase> { GetUserUseCaseImpl(get(), get()) }
    single<GetCategoryUseCase> { GetCategoryUseCaseImpl(get(), get()) }
    single<GetExerciseUseCase> { GetExerciseUseCaseImpl(get(), get()) }
    single<GetCategoriesUseCase> { GetCategoriesUseCaseImpl(get(), get()) }
    single<RemoveCategoryUseCase> { RemoveCategoryUseCaseImpl(get()) }
    single<RemoveExerciseUseCase> { RemoveExerciseUseCaseImpl(get()) }
    single<RemoveResultUseCase> { RemoveResultUseCaseImpl(get()) }
    single<SynchronizeDataUseCase> { SynchronizeDataUseCaseImpl(get()) }
}

val diRepositoryModule = module {
    single { UserRepository(get()) }
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
