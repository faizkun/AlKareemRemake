package com.faizdev.alkareemremake.data.source.service

import com.faizdev.alkareemremake.data.source.model.AdzanScheduleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("day")
    suspend fun getAdzanSchedule(
        @Query("latitude") latitude:String,
        @Query("longitude") longitude:String
    ): AdzanScheduleResponse
}