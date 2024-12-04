package pl.msiwak.multiplatform.utils


external interface Document {
    val title: String
    var cookie: String
    fun getElementById(id: String): HTMLElement?
}

external interface HTMLElement {
    var innerHTML: String
    var textContent: String?
}

external fun getDocument(): Document
external fun setCookie(name: String, value: String, days: Int)
external fun setHttpOnlyCookie(name: String, value: String, expirationDays: Int)
external class Date {
    fun setTime(milliseconds: Double)
    fun getTime(): Double
    fun toUTCString(): String
}
fun createDate(): Date = Date()
