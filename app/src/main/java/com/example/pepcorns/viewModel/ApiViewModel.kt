package com.example.pepcorns.viewModel

import androidx.lifecycle.ViewModel
import com.example.pepcorns.MyApplication
import com.example.pepcorns.apiRequest.GetService
import com.example.pepcorns.data.StockData
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiViewModel : ViewModel(){
    private var _dashboardData = MutableStateFlow<StockData?>(null)

    val dashboardData: MutableStateFlow<StockData?> = _dashboardData

    fun makeApiRequest(apiEndpoint: String) {
        GetService().getApiInterface("https://api.stockdata.org/v1/data/quote?symbols=", MyApplication.getToken()).getDashboardData(apiEndpoint)
            .enqueue(object : Callback<StockData> {
                override fun onResponse(
                    call: Call<StockData>,
                    response: Response<StockData>
                ) {
                    println("Response: ${response.body()}")
                    println("Response: ${response.raw()}")
                    // Process the response data here
                    _dashboardData.value = response.body()
                }

                override fun onFailure(call: Call<StockData>, t: Throwable) {
                    println("Error: ${t.message}")
                    // Handle the error here
                }
            })
    }
}