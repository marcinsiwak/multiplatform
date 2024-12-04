package pl.msiwak.multiplatform.utils

class KMMPreferencesImpl() : KMMPreferences {

//    val document = getDocument()

    override fun put(key: String, value: Int) {

    }

    override fun put(key: String, value: String) {
    }

    override fun put(key: String, value: Boolean) {
    }

    override fun getInt(key: String, default: Int): Int = 0

    override fun getString(key: String): String? = ""

    override fun getBool(key: String, default: Boolean): Boolean = true

}


//    val date = createDate()
//    date.setTime(date.getTime() + (7 * 24 * 60 * 60 * 1000)) // Set expiration
//    val expires = "expires=${date.toUTCString()}"
//    document.cookie = "$key=$value;$expires;path=/"