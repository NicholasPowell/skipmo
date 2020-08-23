import com.nilo.tasks.aws.CreateSubnetTask
import com.nilo.tasks.aws.CreateVpcTask
import com.nilo.tasks.aws.defaults.CreateSkipboDbTask

plugins {
    maven
//    kotlin("jvm")
}

tasks.register<CreateSkipboDbTask>("createNewDb") {
    group = "aws"
    dbName = "sweeeet"
    subnetGroupName = "subnet-04b375a1cd25aab85"
}
tasks.register<CreateVpcTask>("createVpc") {
    group = "aws"
    vpcName = "myNewVpc"
    cidrBlock = "10.0.0.0/16"
}
tasks.register<CreateSubnetTask>("createSubnetEastA") {
    group = "aws"
    subnetName = "subnet-east-a"
    vpcId = "vpc-006fc02e1d896cbfc"
    cidrBlock = "10.0.0.0/28"
    availabilityZone = "us-east-1a"
}

tasks.register<CreateSubnetTask>("createSubnetEastB") {
    group = "aws"
    subnetName = "subnet-east-b"
    vpcId = "vpc-006fc02e1d896cbfc"
    cidrBlock = "10.0.1.0/28"
    availabilityZone = "us-east-1b"
}



//tasks.named("createNewDb") { dependsOn("createVpc" ) }
