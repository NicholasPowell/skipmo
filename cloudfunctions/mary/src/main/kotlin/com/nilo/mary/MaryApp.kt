package com.nilo.mary

import com.nilo.skipmo.CoreAdapter
import kotliquery.queryOf
import kotliquery.sessionOf
import org.postgresql.Driver
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@SpringBootApplication
class MaryApp

@Configuration
class Mary {

    @Bean
    fun newGame() = java.util.function.Supplier {
        Class.forName("org.postgresql.Driver")
        val game = CoreAdapter().createGame("Nick", "Lori").getDisplay()
        //val host = "database-1.cluster-cdmmqr7be8vv.us-east-1.rds.amazonaws.com"
        val host = "skipbo-cluster.cluster-cdmmqr7be8vv.us-east-1.rds.amazonaws.com"
        val user = "npowell"
        val password = "Angannon22"
        val session = sessionOf("jdbc:postgresql://$host:5432/postgres", user, password)

        session.execute(queryOf("""
            insert into game values (1, "{\"sweet\":\"pie\"}")
        """.trimIndent()))
//        session.execute(queryOf("create schema skipmo"))
//        session.execute(queryOf(""" CREATE TABLE game (
//	id serial NOT NULL PRIMARY KEY,
//	info json NOT NULL
//); """))
        "hard time"
    }

}

//AWSLambdaVPCAccessExecutionRole
//VPC


fun main(args: Array<String>) {
    runApplication<MaryApp>(*args)
}