package pl.msiwak.multiplatform.core.extensions

fun String.safeToDouble(): Double {
    return replace(",", ".").toDouble()
}