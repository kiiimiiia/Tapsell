package com.tapsell.tapsell

import com.github.eloyzone.jalalicalendar.JalaliDate
import com.tapsell.tapsell.models.AppStatistics
import com.tapsell.tapsell.repository.AppStatisticsRepository
//import com.tapsell.tapsell.Service.AppStatisticsDAO
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

val months =  listOf("01", "02" , "03" , "04", "05", "06" , "07" , "08" , "09" , "10" , "11" , "12")
val days   = listOf("01", "02" , "03" , "04", "05", "06" , "07" , "08" , "09" , "10" , "11" , "12" , "13" , "14" , "15" , "16","17" , "18" , "19" , "20",
					"21" , "22" , "23" , "24" , "25" , "26" , "27" , "28" , "29")
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
			appStatisticsDAO.insert(AppStatistics(reportTime = ((1395..1398).random().toString()+ months.get((0..11).random())+ days.get((0..28).random())).toInt(), type = (1..5).random(), videoRequests = (1..500).random(), webViewRequests = (1..5000).random(),
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
