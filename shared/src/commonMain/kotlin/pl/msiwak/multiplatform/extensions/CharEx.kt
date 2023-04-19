package pl.msiwak.multiplatform.extensions

fun Char.isNumber() = isDigit() || toString() == "." || toString() == ","
