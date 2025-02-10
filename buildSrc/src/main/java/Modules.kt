package pl.msiwak.multiplatform.dependencies

object Modules {
    // Shared frontend
    const val sharedFrontend = ":shared-frontend"
    const val commonResources = "$sharedFrontend:commonResources"
    const val commonObject = "$sharedFrontend:commonObject"
    const val database = "$sharedFrontend:database"
    const val utils = "$sharedFrontend:utils"
    const val auth = "$sharedFrontend:auth"
    const val network = "$sharedFrontend:network"
    const val data = "$sharedFrontend:data"
    const val remoteConfig = "$sharedFrontend:remoteConfig"
    const val domain = "$sharedFrontend:domain"
    const val domainImpl = "$sharedFrontend:domain-impl"
    const val navigator = "$sharedFrontend:navigator"
    const val uiWelcome = "$sharedFrontend:ui-welcome"
    const val uiAddCategory = "$sharedFrontend:ui-addCategory"
    const val uiAddExercise = "$sharedFrontend:ui-addExercise"
    const val uiCategory = "$sharedFrontend:ui-category"
    const val uiDashboard = "$sharedFrontend:ui-dashboard"
    const val uiForceUpdate = "$sharedFrontend:ui-forceUpdate"
    const val uiLanguage = "$sharedFrontend:ui-language"
    const val uiRegister = "$sharedFrontend:ui-register"
    const val uiSettings = "$sharedFrontend:ui-settings"
    const val uiSummary = "$sharedFrontend:ui-summary"
    const val uiUnit = "$sharedFrontend:ui-unit"
    const val uiVerifyEmail = "$sharedFrontend:ui-verifyEmail"
    const val buildConfig = "$sharedFrontend:buildConfig"
    const val notifications = "$sharedFrontend:notifications"
    const val uiCommonComponent = "$sharedFrontend:ui-common-component"
    const val uiTerms = "$sharedFrontend:ui-terms"
    const val databaseWasm = "$sharedFrontend:database-wasm"
    const val store = "$sharedFrontend:store"
    const val permissionManager = "$sharedFrontend:permission-manager"

    // Admin
    const val uiAdminPanel = "$sharedFrontend:ui-admin-panel"

    // Shared
    const val shared = ":shared"
    const val sharedModel = "$shared:model"
    const val sharedUtils = "$shared:utils"
}