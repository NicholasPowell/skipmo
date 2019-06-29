package com.nilo.skipmo.domain.lobby

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity

import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders

@EnableWebFluxSecurity
open class SecurityConfig {

    @Value("\${spring.security.oauth2.resourceserver.jwk.issuer-uri}")
    private val issuerUri: String? = null

    @Bean
    open fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http
                .authorizeExchange()
                .pathMatchers("/guests/**").permitAll()
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer().jwt()
        http
                .csrf().disable()

                .httpBasic().disable()
                .formLogin().disable()
                .logout().disable()

        return http.build()
    }


    @Bean
    open fun jwtDecoder(): ReactiveJwtDecoder {
        return ReactiveJwtDecoders.fromOidcIssuerLocation(issuerUri)
    }
}