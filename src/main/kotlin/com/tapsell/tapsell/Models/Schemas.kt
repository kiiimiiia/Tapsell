package com.tapsell.tapsell.Models

import com.github.eloyzone.jalalicalendar.JalaliDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.IndexDirection
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import javax.annotation.processing.Generated


@Document(collection = "AppStatistics")
data class AppStatistics(@Id  private   val id: String? = null,
                          val reportTime: JalaliDate,
                          val type: Int,
                          val videoRequests: Int,
                          val webViewRequests: Int,
                          val videoClicks: Int,
                          val webViewClicks: Int,
                          val videoInstalls: Int,
                          val webviewInstalls: Int){



}


