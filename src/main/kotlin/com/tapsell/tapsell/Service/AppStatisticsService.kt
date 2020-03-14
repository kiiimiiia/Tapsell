package com.tapsell.tapsell.Service
import com.github.eloyzone.jalalicalendar.JalaliDate
import com.tapsell.tapsell.Repository.AppStatisticsRepository
import com.tapsell.tapsell.Responses.GetStatItem
import com.tapsell.tapsell.Responses.GetStatResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AppStatisticsService {

    @Autowired
    private lateinit var appStatisticsRepository : AppStatisticsRepository

    fun getByDate(startDate : JalaliDate , endDate : JalaliDate , type : Int):  GetStatResponse{
        val appStatisticsFromDb = appStatisticsRepository.getAppStatisticsByRangeDate(startDate, endDate , type)
        var response = MutableList(53 * (endDate.year - startDate.year + 1) ){ GetStatItem()}
        var temp = startDate.toString().split('-')
        var bound1 =( dayCalculator(temp[1].toInt()) + (temp[2].toInt()))/7
        temp = endDate.toString().split('-')
        var bound2 = (endDate.year - startDate.year)*53 + (dayCalculator(temp[1].toInt()) + (temp[2].toInt()))/7

        for ( item in appStatisticsFromDb){
            var timeReport = item.reportTime.toString().split('-')
            println(timeReport)
            var daysOfMonths = dayCalculator(timeReport[1].toInt())

            var monthNumber = (daysOfMonths + timeReport[2].toInt())/7
            var index = (item.reportTime.year - startDate.year)*53 + monthNumber
            if(index<bound1 || index>bound2) {
//                println("bad index " + index)
//                println(bound1)
//                println(bound2)
                continue
            }
//            println("index is " + index)
            var getStatItemOld = response.get(index)

            var getStatItemNew = GetStatItem((index%53)+1 , item.reportTime.year ,
                                         getStatItemOld.requests+item.videoRequests+item.webViewRequests ,
                                           getStatItemOld.clicks+item.videoClicks+item.webViewClicks ,
                                          getStatItemOld.installs+item.videoInstalls+item.webviewInstalls)
            response[index] = getStatItemNew
        }

        var count = 0
        while (count<response.size){
            if( response[count].year == 0)
                response.removeAt(count)
            else
                count++
        }

        return GetStatResponse(response)
    }

    fun dayCalculator(month: Int ) :Int{
        val daysOfMonths : Int
        if (month > 6)
            daysOfMonths = 6 * 31 + (month - 6 -1) * 30
        else
            daysOfMonths = (month - 1) *31

        return daysOfMonths
    }




}