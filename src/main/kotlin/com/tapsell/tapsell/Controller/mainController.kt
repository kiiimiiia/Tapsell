package com.tapsell.tapsell.Controller

import com.github.eloyzone.jalalicalendar.JalaliDate
import com.tapsell.tapsell.Models.AppStatistics
import com.tapsell.tapsell.Responses.GetStatResponse
import com.tapsell.tapsell.Service.AppStatisticsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("api/AppStatistics")
class mainController() {
    @Autowired
    private lateinit var appStatisticsService: AppStatisticsService

    @Cacheable(value = ["AppStatistics"],  key = "#type + #startDate + #endDate" )
    @RequestMapping("/getStats" , method= arrayOf(RequestMethod.GET))
    @ResponseBody
    fun getStats( @RequestParam startDate : String, @RequestParam endDate : String , @RequestParam type :Int): GetStatResponse
    {
        var startDateSplited = startDate.split('-')
        var endDateSpliterator = endDate.split('-')
        return appStatisticsService.getByDate(JalaliDate(startDateSplited[0].toInt() , startDateSplited[1].toInt() , startDateSplited[2].toInt()) ,
                                              JalaliDate(endDateSpliterator[0].toInt() , endDateSpliterator[1].toInt() , endDateSpliterator[2].toInt()) ,
                                              type)
    }

}