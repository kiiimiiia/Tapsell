package com.tapsell.tapsell.service
import com.tapsell.tapsell.models.AppStatistics
import com.tapsell.tapsell.repository.AppStatisticsRepository
import com.tapsell.tapsell.responses.GetStatItem
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
val months =  listOf("01", "02" , "03" , "04", "05", "06" , "07" , "08" , "09" , "10" , "11" , "12")
val days   = listOf("01", "02" , "03" , "04", "05", "06" , "07" , "08" , "09" , "10" , "11" , "12" , "13" , "14" , "15" , "16","17" , "18" , "19" , "20", "21" , "22" , "23" , "24" , "25" , "26" , "27" , "28" , "29")
@Service
class AppStatisticsService {

    @Autowired
    private lateinit var appStatisticsRepository: AppStatisticsRepository

    fun getByDate(startDate: String, endDate: String, type: Int): List<GetStatItem> {
        generateData()
        var startDateSplited = startDate.split('-')
        var endDateSplited = endDate.split('-')
        val appStatisticsFromDb = appStatisticsRepository.findByReportTimeBetweenAndType((startDateSplited[0] + startDateSplited[1] + startDateSplited[2]).toInt(),
                (endDateSplited[0] + endDateSplited[1] + endDateSplited[2]).toInt(),
                type)
        println(appStatisticsFromDb)
        //fruits.groupBy(keySelector = { it.first()
        var groupedResult = appStatisticsFromDb.groupBy(keySelector = {
            (it.reportTime.toString().substring(startIndex = 0, endIndex = 4).toInt() - startDate[0].toInt()) * 53 +
                    (dayCalculator(it.reportTime.toString().substring(startIndex = 4, endIndex = 6).toInt()) +
                            it.reportTime.toString().substring(startIndex = 6, endIndex = 8).toInt()) / 7
        })

        println(groupedResult.size)
        println(groupedResult)

        return groupedResult.map {
            var clicks   = it.value.sumBy { it.videoClicks + it.webViewClicks }
            var installs  = it.value.sumBy { it.videoInstalls + it.webviewInstalls }
            var requests  = it.value.sumBy { it.videoRequests + it.webViewRequests }
            var weekNum  = (dayCalculator(it.value[0].reportTime.toString().substring(startIndex = 4, endIndex = 6).toInt())+
                           it.value[0].reportTime.toString().substring(startIndex = 6, endIndex = 8).toInt())/7
            var year     = it.value[0].reportTime.toString().substring(startIndex = 0, endIndex = 4).toInt()
            GetStatItem(weekNum,year,requests,clicks,installs)
        }

    }

    fun dayCalculator(month: Int): Int {
        val daysOfMonths: Int
        if (month > 6)
            daysOfMonths = 6 * 31 + (month - 6 - 1) * 30
        else
            daysOfMonths = (month - 1) * 31

        return daysOfMonths
    }

    fun generateData(){

		if(appStatisticsRepository.count() <1000)
		    appStatisticsRepository.deleteAll()

		for(i in 1..1000) {
            appStatisticsRepository.insert(AppStatistics(reportTime = ((1395..1398).random().toString()+ months.get((0..11).random())+ days.get((0..28).random())).toInt(), type = (1..5).random(), videoRequests = (1..500).random(), webViewRequests = (1..5000).random(),
					videoClicks = (1..500).random(), webViewClicks = (1..500).random(), videoInstalls = (1..500).random(), webviewInstalls = (1..500).random()))
		}


    }


}