package com.nilo.tasks.aws

import com.nilo.tasks.printCommand
import com.nilo.tasks.printOutput
import com.nilo.tasks.runCommand
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

open class CreateVpcTask : DefaultTask() {

    @get:InputDirectory
    var path = File(".")

    @get:Input
    lateinit var vpcName: String

    @get:Input
    lateinit var cidrBlock: String

    private fun assembleCommand() = CreateVpc(cidrBlock = cidrBlock, vpcName = vpcName).assemble()

    @TaskAction
    fun runCommand() {
        assembleCommand()
                .printCommand()
                .runCommand(path)
                .printOutput()
    }


}
