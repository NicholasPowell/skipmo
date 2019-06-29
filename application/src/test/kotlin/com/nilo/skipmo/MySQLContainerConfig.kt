package com.nilo.skipmo

//@Configuration
class MySQLContainerConfig {

//    class KotlinHackMySQLContainer(imageName: String?) : MySQLContainer<KotlinHackMySQLContainer>(imageName)

//    var container = KotlinHackMySQLContainer("mysql:5.6")

//    @Bean("mySQLContainer")
//    fun configureMySqlContainer() : KotlinHackMySQLContainer
//    {
//        container.start()
//        return container
//    }

//    @Bean("dataSource")
//    @DependsOn("mySQLContainer")
//    fun dataSource() : DataSource
//    {
//
//        return BasicDataSourceFactory.createDataSource(
//                mapOf(
//                        "url" to "localhost",
//                        "username" to container.username,
//                        "password" to container.password,
//                        "port" to container.getMappedPort(3306).toString(),
//                        "driverClassName" to "com.mysql.cj.jdbc.Driver"
//
//                ).toProperties()
//        )
//
//
//    }
}