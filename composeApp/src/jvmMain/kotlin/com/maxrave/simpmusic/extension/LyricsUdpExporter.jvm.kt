package com.maxrave.simpmusic.extension

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

actual object LyricsUdpExporter {
    private const val PORT = 4212
    private val socket by lazy { DatagramSocket() }

    actual fun send(text: String, startMs: Long) {
        try {
            val payload = "$startMs|$text".toByteArray(Charsets.UTF_8)
            val packet = DatagramPacket(
                payload, payload.size,
                InetAddress.getByName("127.0.0.1"), PORT,
            )
            socket.send(packet)
        } catch (_: Exception) { }
    }
}
