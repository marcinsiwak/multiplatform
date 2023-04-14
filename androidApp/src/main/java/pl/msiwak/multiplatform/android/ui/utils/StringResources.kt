package pl.msiwak.multiplatform.android.ui.utils

import android.content.Context
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.ResourceFormatted
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.resources.format


fun getString(context: Context, resource: StringResource): String {
    return resource.desc().toString(context = context)
}

fun getString(context: Context, resource: StringResource, arg: String): String {
    return resource.format(arg).toString(context)
}