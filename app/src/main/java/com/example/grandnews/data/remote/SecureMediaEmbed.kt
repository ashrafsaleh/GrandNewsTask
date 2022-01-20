package com.example.grandnews.data.remote

data class SecureMediaEmbed(
    val content: String,
    val height: Int,
    val media_domain_url: String,
    val scrolling: Boolean,
    val width: Int
)