package com.example.grandnews.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.grandnews.data.remote.DataX

@Database(entities = [DataX::class],version = 5)
@TypeConverters(Converters::class)
abstract class NewsDataBase : RoomDatabase(){
     abstract fun newsDao(): NewDao

    companion object {
        @Volatile
        private var INSTANCE: NewsDataBase? = null


        fun getAppDataBase(context: Context): NewsDataBase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder<NewsDataBase>(
                    context.applicationContext, NewsDataBase::class.java, "AppDB"
                )   .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }

    }
}