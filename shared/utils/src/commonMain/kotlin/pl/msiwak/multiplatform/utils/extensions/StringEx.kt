package pl.msiwak.multiplatform.utils.extensions

fun String.safeToDouble(): Double {
    return replace(",", ".").toDouble()
}