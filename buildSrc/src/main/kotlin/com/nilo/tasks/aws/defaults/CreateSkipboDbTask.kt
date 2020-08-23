package com.nilo.tasks.aws.defaults

import com.nilo.tasks.aws.RdsCreateDbTask

open class CreateSkipboDbTask : RdsCreateDbTask() {
    init {
        securityGroupId = "sg-0f53e2b419af8fe83"
        subnetGroupName = "default-vpc-0580e258dab1f622b"
        engine = "aurora-postgresql"
        username = "test"
        password = "test1234"
        instanceClass = "db.r5.large"
    }
}