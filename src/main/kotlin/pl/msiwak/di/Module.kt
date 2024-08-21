package pl.msiwak.di

import org.koin.dsl.module
import pl.msiwak.commands.*
import pl.msiwak.database.dao.exercise.ExercisesDao
import pl.msiwak.database.dao.exercise.ExercisesDaoImpl
import pl.msiwak.database.dao.user.UserDao
import pl.msiwak.database.dao.user.UserDaoImpl
import pl.msiwak.queries.*
import pl.msiwak.repositories.ExerciseRepository
import pl.msiwak.repositories.UserRepository

val diModule = module {
    single<AddUserCommand> { AddUserCommandImpl(get(), get()) }
    single<AddCategoryCommand> { AddCategoryCommandImpl(get()) }
    single<AddExerciseCommand> { AddExerciseCommandImpl(get()) }
    single<AddResultCommand> { AddResultCommandImpl(get()) }
    single<GetUserQuery> { GetUserQueryImpl(get()) }
    single<GetCategoryQuery> { GetCategoryQueryImpl(get()) }
    single<GetExerciseQuery> { GetExerciseQueryImpl(get()) }
    single<GetCategoriesQuery> { GetCategoriesQueryImpl(get()) }
    single<RemoveCategoryCommand> { RemoveCategoryCommandImpl(get()) }
    single<RemoveExerciseCommand> { RemoveExerciseCommandImpl(get()) }
    single<RemoveResultCommand> { RemoveResultCommandImpl(get()) }
    single { UserRepository(get()) }
    single { ExerciseRepository(get()) }
    single<UserDao> { UserDaoImpl() }
    single<ExercisesDao> { ExercisesDaoImpl() }
}