package pl.msiwak.di

import org.koin.dsl.module
import pl.msiwak.commands.AddUserCommand
import pl.msiwak.commands.AddUserCommandImpl
import pl.msiwak.database.dao.user.UserDao
import pl.msiwak.database.dao.user.UserDaoImpl
import pl.msiwak.repositories.UserRepository

val diModule = module {
    single<AddUserCommand> { AddUserCommandImpl(get()) }
    single { UserRepository(get()) }
    single<UserDao> { UserDaoImpl() }
}