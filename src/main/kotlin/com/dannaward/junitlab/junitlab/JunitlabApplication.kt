package com.dannaward.junitlab.junitlab

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JunitlabApplication

fun main(args: Array<String>) {
    runApplication<JunitlabApplication>(*args)
}
