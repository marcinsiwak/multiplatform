package pl.msiwak.multiplatform.utils

import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.getImageByFileName
import org.example.library.MR

class Resources {
    fun getImageByFileName(name: String): ImageResource {
        val placeHolder = MR.images.bg_running_field
        return MR.images.getImageByFileName(name) ?:  placeHolder
    }
}
