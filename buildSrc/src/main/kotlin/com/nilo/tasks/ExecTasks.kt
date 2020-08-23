package com.nilo.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.util.concurrent.TimeUnit


abstract class ExecTasks : DefaultTask() {

    @Input
    abstract fun getCommands() : List<String>

    @get:InputDirectory
    var path = File(".")

    @TaskAction
    fun runCommand() {
        getCommands().forEach{
            command ->
            println("Executing command")
            println(command)
            command.runCommand(path).printOutput()
        }
    }
}
