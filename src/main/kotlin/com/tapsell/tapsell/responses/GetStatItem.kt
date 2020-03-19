package com.tapsell.tapsell.responses

import java.io.Serializable

data  class GetStatItem (var weekNum:Int = 0,  var year:Int = 0, var requests:Int = 0, var  clicks:Int=0, var installs:Int =0) :Serializable
    //:Serializable
//{
//    var weekNum:Int
//    var year:Int
//    var requests:Int
//    var clicks:Int
//    var installs:Int
//
//
//    init {
//        weekNum = weekNumm
//        year    = yearr
//        requests = requestss
//        clicks = clickss
//        installs = installss
//    }
//
//    }