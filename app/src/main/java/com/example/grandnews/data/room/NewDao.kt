package com.example.grandnews.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.grandnews.data.remote.DataX
import kotlinx.coroutines.flow.Flow

@Dao
interface NewDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNews(data: List<DataX>)

    @Query("select * from news_data")
    fun newsList(): Flow<List<DataX>>
}