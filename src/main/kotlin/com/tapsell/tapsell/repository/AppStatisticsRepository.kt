package com.tapsell.tapsell.repository

import com.github.eloyzone.jalalicalendar.JalaliDate
import com.tapsell.tapsell.models.AppStatistics
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface AppStatisticsRepository : MongoRepository<AppStatistics, String> {
    @Query(value = "{ reportTime : { \$gte : ?0 , \$lte :?1  } , type :?2 }" , sort = "{reportTime :1}")
    fun findByReportTimeBetweenAndType(from : Int,  to : Int , type: Int) : List<AppStatistics>


}