package com.jeniatyt.message

/**
 * Шаблоны сообщений.
 */
object Message : AbstractMessage() {

    val WELCOME: String = loadTemplate("/message/welcome.md")
    val ERROR: String = loadTemplate("/message/error.md")
    val PRICE_STORE: String = loadTemplate("/message/price_store.md")
    val PRICE_LOGISTICS: String = loadTemplate("/message/price_logistics.md")
}
