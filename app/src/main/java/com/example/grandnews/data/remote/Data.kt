package com.example.grandnews.data.remote

import com.example.grandnews.data.remote.Children

data class Data(
    val after: String,
    val before: Any,
    val children: List<Children>,
    val dist: Int,
    val geo_filter: Any,
    val modhash: String
)