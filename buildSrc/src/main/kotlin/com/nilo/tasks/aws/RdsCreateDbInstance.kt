package com.nilo.tasks.aws

data class RdsCreateDbInstance(
        val dbName: String,
        val engine: String,
        val instanceClass: String
) {
    fun getCommand() = "aws rds create-db-instance " +
            "--db-instance-identifier $dbName " +
            "--db-cluster-identifier $dbName-cluster " +
            "--engine $engine " +
            "--db-instance-class $instanceClass"
}
