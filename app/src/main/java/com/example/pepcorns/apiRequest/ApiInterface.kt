package com.example.pepcorns.apiRequest

import com.example.pepcorns.data.StockData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Url

interface ApiInterface {
    @GET
    fun getDashboardData(@Url url: String): Call<StockData>
}