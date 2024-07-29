package pl.msiwak.di

import org.koin.dsl.module
import pl.msiwak.commands.AddCategoryCommand
import pl.msiwak.commands.AddCategoryCommandImpl
import pl.msiwak.commands.AddUserCommand
import pl.msiwak.commands.AddUserCommandImpl
import pl.msiwak.database.dao.user.ExercisesDao
import pl.msiwak.database.dao.user.ExercisesDaoImpl
import pl.msiwak.database.dao.user.UserDao
import pl.msiwak.database.dao.user.UserDaoImpl
import pl.msiwak.queries.GetUserQuery
import pl.msiwak.queries.GetUserQueryImpl
import pl.msiwak.repositories.ExerciseRepository
import pl.msiwak.repositories.UserRepository

val diModule = module {
    single<AddUserCommand> { AddUserCommandImpl(get()) }
    single<AddCategoryCommand> { AddCategoryCommandImpl(get()) }
    single<GetUserQuery> { GetUserQueryImpl(get()) }
    single { UserRepository(get()) }
    single { ExerciseRepository(get()) }
    single<UserDao> { UserDaoImpl() }
    single<ExercisesDao> { ExercisesDaoImpl() }
}