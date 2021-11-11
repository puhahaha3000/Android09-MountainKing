package com.boostcamp.mountainking.data

import androidx.lifecycle.MutableLiveData
import com.boostcamp.mountainking.entity.Achievement
import com.boostcamp.mountainking.entity.Mountain
import com.boostcamp.mountainking.entity.Tracking

interface RepositoryInterface {
    suspend fun getMountain()
    suspend fun getTracking(): List<Tracking>?
    suspend fun getAchievement(): List<Achievement>
    suspend fun getStatistics()
    suspend fun getWeather()
    suspend fun searchMountainName(name: String): List<Mountain>
    suspend fun searchMountainNameInCity(state: String, cityName: String, name: String): List<Mountain>

    suspend fun putTracking(tracking: Tracking)
    suspend fun updateStatistics()
    suspend fun updateAchievement(achievement: Achievement)

    var isRunning: Boolean
    var trackingMountain: String?
    var curTime: MutableLiveData<String>
    var curDistance: MutableLiveData<Int>
    var date: MutableLiveData<String>
}