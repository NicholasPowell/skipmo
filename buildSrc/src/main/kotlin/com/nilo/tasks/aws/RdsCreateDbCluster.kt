package com.nilo.tasks.aws

data class RdsCreateDbCluster(
        val dbName: String,
        val securityGroupId: String,
        val subnetGroupName: String,
        val engine: String,
        val username: String,
        val password: String
) {
    fun getCommand() = "aws rds create-db-cluster " +
                "--database-name $dbName " +
                "--db-cluster-identifier $dbName-cluster " +
                "--vpc-security-group-ids $securityGroupId " +
                "--db-subnet-group-name $subnetGroupName " +
                "--engine $engine " +
                "--master-username $username " +
                "--master-user-password $password "
}
