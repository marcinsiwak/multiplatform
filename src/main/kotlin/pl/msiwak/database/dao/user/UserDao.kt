package pl.msiwak.database.dao.user

interface UserDao {
    suspend fun getUser(id: String)
    suspend fun addNewUser(userId: String, name: String, email: String)
}
