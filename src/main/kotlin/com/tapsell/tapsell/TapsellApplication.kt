package com.tapsell.tapsell


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class TapsellApplication()


fun main(args: Array<String>) {
	runApplication<TapsellApplication>(*args)
}
