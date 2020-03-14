package com.tapsell.tapsell.Responses

import java.io.Serializable

class GetStatItem (weekNumm:Int = 0, yearr:Int = 0, requestss:Int = 0, clickss:Int=0, installss:Int =0) :Serializable{
    var weekNum:Int
    var year:Int
    var requests:Int
    var clicks:Int
    var installs:Int


    init {
        weekNum = weekNumm
        year    = yearr
        requests = requestss
        clicks = clickss
        installs = installss
    }

    }