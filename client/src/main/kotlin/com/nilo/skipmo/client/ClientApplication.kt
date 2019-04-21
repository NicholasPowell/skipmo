package com.nilo.skipmo.client

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.netflix.ribbon.RibbonClient
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@SpringBootApplication
@RibbonClient(name = "skipmo")
@RestController
@EnableDiscoveryClient
class ClientApplication {
    @LoadBalanced
    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

    @Autowired
    lateinit var restTemplate: RestTemplate

    @RequestMapping("/hi")
    fun hi(): String? {
        return this.restTemplate.getForObject("http://skipmo/hmm", String::class.java)
    }
}

fun main(args: Array<String>) {
    runApplication<ClientApplication>(*args)
}
