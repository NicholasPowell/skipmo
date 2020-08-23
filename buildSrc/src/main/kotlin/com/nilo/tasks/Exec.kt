package com.nilo.tasks

import java.io.File
import java.util.concurrent.TimeUnit

fun String.printCommand() = apply{ println("/bin/sh -c \"$this\"") }
fun Process?.printOutput() = println(this?.readOutput() ?: "error" )
fun String.runCommand(workingDir: File): Process? {
    try {
        val parts = listOf("/bin/sh", "-c", this )
        val proc = ProcessBuilder(*parts.toTypedArray())
                .directory(workingDir)
                .redirectOutput(ProcessBuilder.Redirect.PIPE)
                .redirectError(ProcessBuilder.Redirect.PIPE)
                .start()

        proc.waitFor(60, TimeUnit.MINUTES)
        return proc
    } catch(e: Exception) {
        e.printStackTrace()
        return null
    }
}

fun Process.readOutput() = "${inputStream.bufferedReader().readText()}\n${errorStream.bufferedReader().readText()}"