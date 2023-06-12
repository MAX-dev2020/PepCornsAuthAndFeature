package com.example.pepcorns.data

data class StockData(
    val `data`: List<Data>,
    val meta: Meta
)

data class Data(
    val `52_week_high`: Double,
    val `52_week_low`: Double,
    val currency: String,
    val day_change: Double,
    val day_high: Double,
    val day_low: Double,
    val day_open: Double,
    val exchange_long: String,
    val exchange_short: String,
    val is_extended_hours_price: Boolean,
    val last_trade_time: String,
    val market_cap: Long,
    val mic_code: String,
    val name: String,
    val previous_close_price: Double,
    val previous_close_price_time: String,
    val price: Double,
    val ticker: String,
    val volume: Int
)

data class Meta(
    val requested: Int,
    val returned: Int
)