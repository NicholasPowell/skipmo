package com.nilo.tasks.aws

import com.nilo.tasks.ExecTasks
import org.gradle.api.tasks.Input

open class RdsCreateDbTask : ExecTasks() {

    @Input lateinit var dbName: String
    @Input var securityGroupId: String? = null
    @Input var subnetGroupName: String? = null
    @Input var engine: String? = null
    @Input var username: String? = null
    @Input var password: String? = null
    @Input var instanceClass: String? = null

    private object Defaults {
        const val engine = "aurora-postgresql"
        const val instanceClass = "db.r5.large"
        const val securityGroupId = "sg-0f53e2b419af8fe83"
        const val subnetGroupName = "default-vpc-0580e258dab1f622b"
        const val username = "neato"
        const val password = "bandito"
    }

    override fun getCommands() = listOf(
            createDbCluster().getCommand(),
            createRdsDbInstance().getCommand()
    )

    private fun createRdsDbInstance(): RdsCreateDbInstance {
        return RdsCreateDbInstance(
                dbName = dbName,
                engine = engine ?: Defaults.engine,
                instanceClass = instanceClass ?: Defaults.instanceClass
        )
    }

    private fun createDbCluster(): RdsCreateDbCluster {
        return RdsCreateDbCluster(
                dbName = dbName,
                securityGroupId = securityGroupId ?: Defaults.securityGroupId,
                subnetGroupName = subnetGroupName ?: Defaults.subnetGroupName,
                engine = engine ?: Defaults.engine,
                username = username ?: Defaults.username,
                password = password ?: Defaults.password
        )
    }
}