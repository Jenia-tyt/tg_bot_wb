package com.jeniatyt.message

abstract class AbstractMessage {

    protected fun loadTemplate(path: String) = Message::class.java.getResource(path)?.readText()
        ?: throw IllegalArgumentException("Cannot load message template by path: $path")
}
