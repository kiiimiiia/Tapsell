package com.tapsell.tapsell

import com.github.eloyzone.jalalicalendar.JalaliDate
import com.tapsell.tapsell.Models.AppStatistics
import com.tapsell.tapsell.Repository.AppStatisticsRepository
//import com.tapsell.tapsell.Service.AppStatisticsDAO
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class TapsellApplication(private val appStatisticsDAO: AppStatisticsRepository): ApplicationRunner{
	override fun run(args: ApplicationArguments?) {
		print(appStatisticsDAO.count())
		if(appStatisticsDAO.count() <1000)
		this.createAppStatistic()
	}
	private fun createAppStatistic(){
		this.cleanCollections()
		for(i in 1..1000) {
			appStatisticsDAO.insert(AppStatistics(reportTime = JalaliDate((1395..1398).random(), (1..12).random(), (1..29).random()), type = (1..5).random(), videoRequests = (1..500).random(), webViewRequests = (1..5000).random(),
					videoClicks = (1..500).random(), webViewClicks = (1..500).random(), videoInstalls = (1..500).random(), webviewInstalls = (1..500).random()))
		}
	}

	private fun cleanCollections(){
		appStatisticsDAO.deleteAll()
	}
}

fun main(args: Array<String>) {
	runApplication<TapsellApplication>(*args)
}
