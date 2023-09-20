package pl.msiwak.multiplatfor.dependencies

object Modules {
    const val shared =":shared"
    const val core = "$shared:core"
    const val commonResources = "$shared:commonResources"
    const val commonObject = "$shared:commonObject"
    const val database = "$shared:database"
    const val utils = "$shared:utils"
    const val auth = "$shared:auth"
    const val network = "$shared:network"
    const val data = "$shared:data"
    const val remoteConfig = "$shared:remoteConfig"
}