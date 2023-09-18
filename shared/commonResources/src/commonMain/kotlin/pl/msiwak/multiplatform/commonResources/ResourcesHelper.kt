package pl.msiwak.multiplatform.commonResources

import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

object ResourcesHelper {

    fun getMyString(stringId: StringResource): StringDesc {
        return StringDesc.Resource(stringId)
    }
}