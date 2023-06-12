package com.example.pepcorns.components

sealed class CompanyTicker(val companyName:String){
    object Apple : CompanyTicker("AAPL")
    object Microsoft : CompanyTicker("MSFT")
    object Tesla : CompanyTicker("TSLA")
}
