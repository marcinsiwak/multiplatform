package pl.msiwak.multiplatfor.dependencies

object Modules {
    const val shared =":shared"
    const val core = "$shared:core"
    const val commonResources = "$shared:commonResources"
    const val commonObject = "$shared:commonObject"
    const val database = "$shared:database"
    const val utils = "$shared:utils"
}