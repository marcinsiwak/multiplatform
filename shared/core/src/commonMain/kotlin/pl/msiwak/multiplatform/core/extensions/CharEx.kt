package pl.msiwak.multiplatform.core.extensions

fun Char.isNumber() = isDigit() || toString() == "." || toString() == ","
fun Char.isTime() = isDigit() || toString() == ":" || toString() == "."
