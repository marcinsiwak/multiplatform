package pl.msiwak.multiplatform.database.mapper

abstract class Mapper<T, S> {

    protected abstract fun map(value: T): S

    operator fun invoke(value: T): S = map(value)
}
