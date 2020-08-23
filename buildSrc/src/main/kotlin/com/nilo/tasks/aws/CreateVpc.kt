package com.nilo.tasks.aws

data class CreateVpc(
        val cidrBlock: String,
        val vpcName: String
) {
    private val createVpc = "aws ec2 create-vpc"
    private val cidrBlockArg = "--cidr-block $cidrBlock"
    private val vpcNameArg = "--tag-specifications \"ResourceType=vpc,Tags=[{Key=Name,Value=$vpcName}]\""
    private val extractVpcId = "jq -r .Vpc.VpcId"

    fun assemble() = "$createVpc $cidrBlockArg $vpcNameArg | $extractVpcId"
}

