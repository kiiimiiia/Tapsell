package com.tapsell.tapsell.Repository

import com.github.eloyzone.jalalicalendar.JalaliDate
import com.tapsell.tapsell.Models.AppStatistics
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface AppStatisticsRepository : MongoRepository<AppStatistics, String> {
    @Query(value = "{ reportTime : { \$gte : ?0 , \$lte :?1  } , type :?2}")
    fun getAppStatisticsByRangeDate(from : JalaliDate,  to : JalaliDate , type: Int) : List<AppStatistics>


}