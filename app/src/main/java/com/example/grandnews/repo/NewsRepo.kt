package com.example.grandnews.repo

import com.example.grandnews.data.remote.Children
import com.example.grandnews.data.remote.DataX
import com.example.grandnews.data.remote.RetroInstance
import com.example.grandnews.data.room.NewDao

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import java.util.ArrayList

class NewsRepo(val db: NewDao) {

    suspend fun getNews(): kotlinx.coroutines.flow.Flow<List<DataX>> = flow {
        var reposne = RetroInstance.api.getNews()
        emit(mapChildrenToDatax(reposne.data.children))
    }.onEach {
        insertNews(it)
    }


    fun insertNews(dataX: List<DataX>) {
        db.insertNews(dataX)
    }

    fun getOfflineNews(): Flow<List<DataX>> = db.newsList().flowOn(
        Dispatchers.IO
    )

    fun mapChildrenToDatax(childrenList: List<Children>): List<DataX> {
        var datax = ArrayList<DataX>()
        for (i in childrenList) {
            datax.add(i.data)
        }
        return datax
    }
}