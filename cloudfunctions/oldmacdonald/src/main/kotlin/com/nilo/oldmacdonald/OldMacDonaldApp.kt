package com.nilo.oldmacdonald

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@SpringBootApplication
class OldMacDonaldApp

@Configuration
class OldMacDonald {
    @Bean
    fun hadALittleLamb() = java.util.function.Function<String, String> {
        "$it had a farm"
    }
}

fun main(args: Array<String>) {
    runApplication<OldMacDonaldApp>(*args)
}