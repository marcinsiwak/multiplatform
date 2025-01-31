package pl.msiwak.multiplatform.notifications

expect class NotificationsManager() {
    suspend fun getToken(): String?
}