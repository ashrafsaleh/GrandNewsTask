package com.example.grandnews.useCase


import com.example.grandnews.data.remote.DataX
import com.example.grandnews.data.room.NewDao
import com.example.grandnews.repo.NewsRepo
import kotlinx.coroutines.flow.Flow

class NewsUseCase (val db : NewDao){
    val newsRepo = NewsRepo(db)

    suspend fun getNews() : kotlinx.coroutines.flow.Flow<List<DataX>> =newsRepo.getNews()


    fun getOfflineNews() : Flow<List<DataX>> {
        return newsRepo.getOfflineNews()
    }

}