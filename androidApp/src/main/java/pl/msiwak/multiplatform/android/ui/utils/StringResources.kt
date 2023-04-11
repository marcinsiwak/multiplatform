package pl.msiwak.multiplatform.android.ui.utils

import android.content.Context
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.desc


fun getString(context: Context, resource: StringResource): String {
    return resource.desc().toString(context = context)
}