package pl.msiwak.multiplatfor.dependencies

object Modules {
    const val shared =":shared"
    const val core = "$shared:core"
    const val commonResources = "$shared:common-resources"
    const val commonObject = "$shared:common-object"
    const val database = "$shared:database"
}