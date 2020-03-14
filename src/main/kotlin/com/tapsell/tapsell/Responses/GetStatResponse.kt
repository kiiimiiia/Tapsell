package com.tapsell.tapsell.Responses

import java.io.Serializable


class GetStatResponse(listOfStat : List<GetStatItem?> )  : Serializable {

    var ListOfStats: List<GetStatItem?>

    init {
        ListOfStats = listOfStat
    }
}