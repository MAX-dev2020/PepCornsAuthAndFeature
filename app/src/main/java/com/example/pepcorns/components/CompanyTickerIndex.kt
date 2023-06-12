package com.example.pepcorns.components

sealed class CompanyTickerIndex(val companyIndex:Int){
    object AAPL : CompanyTickerIndex(0)
    object MSFT : CompanyTickerIndex(1)
    object TSLA : CompanyTickerIndex(2)
}
