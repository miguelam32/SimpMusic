package com.maxrave.simpmusic.extension

expect object LyricsUdpExporter {
    fun send(text: String, startMs: Long)
}
