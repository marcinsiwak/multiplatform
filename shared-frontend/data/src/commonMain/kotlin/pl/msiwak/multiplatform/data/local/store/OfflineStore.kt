package pl.msiwak.multiplatform.data.local.store

import pl.msiwak.multiplatform.utils.KMPPreferences

class OfflineStore(private val sharedKMPPreferences: KMPPreferences) {

    fun getCategoryLastId(): String {
        val lastId = sharedKMPPreferences.getInt(PREFS_CATEGORY_LAST_ID_KEY, 0)
        sharedKMPPreferences.put(PREFS_CATEGORY_LAST_ID_KEY, lastId.plus(1))
        return lastId.toString()
    }

    fun getExerciseLastId(): String {
        val lastId = sharedKMPPreferences.getInt(PREFS_EXERCISE_LAST_ID_KEY, 0)
        sharedKMPPreferences.put(PREFS_EXERCISE_LAST_ID_KEY, lastId.plus(1))
        return lastId.toString()
    }

    fun getResultLastId(): String {
        val lastId = sharedKMPPreferences.getInt(PREFS_RESULT_LAST_ID_KEY, 0)
        sharedKMPPreferences.put(PREFS_RESULT_LAST_ID_KEY, lastId.plus(1))
        return lastId.toString()
    }

    companion object {
        const val PREFS_CATEGORY_LAST_ID_KEY = "PREFS_CATEGORY_LAST_ID_KEY"
        const val PREFS_EXERCISE_LAST_ID_KEY = "PREFS_EXERCISE_LAST_ID_KEY"
        const val PREFS_RESULT_LAST_ID_KEY = "PREFS_RESULT_LAST_ID_KEY"
    }
}
