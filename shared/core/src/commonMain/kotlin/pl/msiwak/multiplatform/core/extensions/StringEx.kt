package pl.msiwak.multiplatform.core.extensions

import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

fun String.safeToDouble(): Double {
    return replace(",", ".").toDouble()
}