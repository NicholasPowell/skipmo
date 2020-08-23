package com.nilo.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File
import javax.inject.Inject

open class ExecTask : DefaultTask() {

    @get:Input
    var command: String = ""

    @get:InputDirectory
    var path = File(".")

    @TaskAction
    fun runCommand() {
        if(command.isNotEmpty() ) {
            println(command)
            println(command.runCommand(path))
        }
    }
}
