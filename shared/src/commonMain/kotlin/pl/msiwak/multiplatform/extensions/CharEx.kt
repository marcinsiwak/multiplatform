package pl.msiwak.multiplatform.extensions

fun Char.isNumber() = isDigit() || toString() == "." || toString() == ","
fun Char.isTime() = isDigit() || toString() == ":" || toString() == "."
