package pl.msiwak.multiplatform.commonObject.base

abstract class Mapper<T, S> {

    protected abstract fun map(value: T): S

    operator fun invoke(value: T): S = map(value)
}
