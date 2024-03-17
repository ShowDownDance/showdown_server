package com.showdown.showdown_web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class ShowdownWebApplication

fun main(args: Array<String>) {
	runApplication<ShowdownWebApplication>(*args)
}