package pl.msiwak.multiplatform

import io.ktor.client.plugins.cookies.CookiesStorage
import io.ktor.http.Cookie
import io.ktor.http.Url

class CookieStore: CookiesStorage {
    override suspend fun addCookie(requestUrl: Url, cookie: Cookie) {

    }

    override fun close() {
        TODO("Not yet implemented")
    }

    override suspend fun get(requestUrl: Url): List<Cookie> {
        TODO("Not yet implemented")
    }
}