package com.example.grandnews.viewmodel


import android.app.Application
import androidx.lifecycle.*
import com.example.grandnews.data.remote.DataX
import com.example.grandnews.data.room.NewDao
import com.example.grandnews.data.room.NewsDataBase
import com.example.grandnews.useCase.NewsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
class NewsViewModel(application: Application)
: AndroidViewModel(application){
    lateinit var favDao : NewDao
    lateinit var useCase: NewsUseCase
    private var _news = MutableLiveData<List<DataX>>()

    val news: LiveData<List<DataX>>  get() = _news

    init {
        favDao = NewsDataBase.getAppDataBase(application)!!.newsDao()!!
        useCase = NewsUseCase(favDao)
    }
    fun getNews() {
        viewModelScope.launch {
            useCase.getNews().collect {
                _news.postValue(it)
            }
        }
    }

    fun getOfflineNews() {
        viewModelScope.launch {
            useCase.getOfflineNews().collect {
                _news.postValue(it)
            }
        }
    }

}


