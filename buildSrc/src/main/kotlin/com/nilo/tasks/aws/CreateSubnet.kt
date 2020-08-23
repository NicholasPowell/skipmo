package com.nilo.tasks.aws

data class CreateSubnet(
        val vpcId: String,
        val cidrBlock: String,
        val subnetName: String,
        val availabilityZone: String
) {
    private val createSubnet = "aws ec2 create-subnet"
    private val vpcArg = "--vpc-id $vpcId"
    private val cidrArg = "--cidr-block $cidrBlock"
    private val subnetArg = "--tag-specifications \"ResourceType=subnet,Tags=[{Key=Name,Value=$subnetName}]\""
    private val availabilityZoneArg = "--availability-zone $availabilityZone"
    private val extractSubnetId = "jq -r .Subnet.SubnetId"

    fun assemble() = "$createSubnet $vpcArg $cidrArg $subnetArg $availabilityZoneArg | $extractSubnetId"
}

