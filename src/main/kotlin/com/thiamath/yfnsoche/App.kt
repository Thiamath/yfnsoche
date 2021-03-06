/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.thiamath.yfnsoche

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean

import org.springframework.web.client.RestTemplate


@SpringBootApplication
class App {

    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate? {
        return builder.build()
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(App::class.java, *args)
}
