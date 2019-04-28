package org.wit.mathstutorappv2.models

interface StatsStore {
    fun getStats(): StatsModel
    fun saveStats(statss: StatsModel)
}