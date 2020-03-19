package com.tapsell.tapsell.models

import com.github.eloyzone.jalalicalendar.JalaliDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document(collection = "AppStatistics")
data class AppStatistics(@Id  private   val id: String? = null,
                          val reportTime: Int,
                          val type: Int,
                          val videoRequests: Int,
                          val webViewRequests: Int,
                          val videoClicks: Int,
                          val webViewClicks: Int,
                          val videoInstalls: Int,
                          val webviewInstalls: Int){



}


