package com.nilo.tasks.aws

import com.nilo.tasks.printCommand
import com.nilo.tasks.printOutput
import com.nilo.tasks.runCommand
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

open class CreateSubnetTask : DefaultTask() {

    @get:InputDirectory
    var path = File(".")

    @get:Input
    lateinit var subnetName: String

    @get:Input
    lateinit var vpcId: String

    @get:Input
    lateinit var cidrBlock: String

    @get:Input
    lateinit var availabilityZone: String

    private fun assembleCommand() = CreateSubnet(
            vpcId = vpcId,
            subnetName = subnetName,
            cidrBlock = cidrBlock,
            availabilityZone = availabilityZone
    ).assemble()

    @TaskAction
    fun runCommand() {
        assembleCommand()
                .printCommand()
                .runCommand(path)
                .printOutput()
    }


}
