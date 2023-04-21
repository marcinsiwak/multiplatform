package pl.msiwak.multiplatform.extensions

fun String.safeToDouble(): Double {
    return replace(",", ".").toDouble()
}