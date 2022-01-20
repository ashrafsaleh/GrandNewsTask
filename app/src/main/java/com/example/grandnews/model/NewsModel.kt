package com.example.grandnews.model

import com.example.grandnews.data.remote.Data


data class NewsModel(
    val `data`: Data,
    val kind: String
)