package com.tapsell.tapsell.controller

import com.github.eloyzone.jalalicalendar.JalaliDate
import com.tapsell.tapsell.responses.GetStatResponse
import com.tapsell.tapsell.service.AppStatisticsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/AppStatistics")
class Controller() {
    @Autowired
    private lateinit var appStatisticsService: AppStatisticsService

    @Cacheable(value = ["AppStatistics"],  key = "#type + #startDate + #endDate" )
    @RequestMapping("/getStats" , method= arrayOf(RequestMethod.GET))
    @ResponseBody
    fun getStats( @RequestParam startDate : String, @RequestParam endDate : String , @RequestParam type :Int): GetStatResponse
    {
        return GetStatResponse(appStatisticsService.getByDate( startDate, endDate, type))
    }

}