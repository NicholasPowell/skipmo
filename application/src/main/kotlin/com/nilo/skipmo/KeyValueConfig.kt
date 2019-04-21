package com.nilo.skipmo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.keyvalue.core.KeyValueAdapter
import org.springframework.data.keyvalue.core.KeyValueOperations
import org.springframework.data.keyvalue.core.KeyValueTemplate
import org.springframework.data.map.MapKeyValueAdapter
import java.util.concurrent.ConcurrentHashMap

@Configuration
open class KeyValueConfig {

//    @Bean(name = ["huh"])
//    open fun keyValueOperation(): KeyValueOperations {
//        return KeyValueTemplate(keyValueAdapter())
//    }
//
//    @Bean
//    open fun keyValueAdapter(): MapKeyValueAdapter {
//        return MapKeyValueAdapter(ConcurrentHashMap::class.java)
//    }
}