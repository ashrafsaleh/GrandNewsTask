package com.example.grandnews.ui.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.grandnews.R
import com.example.grandnews.adapters.ArticleAdapter
import com.example.grandnews.data.remote.DataX
import com.example.grandnews.model.OnArticleListener
import com.example.grandnews.viewmodel.NewsViewModel





class NewsFragment : Fragment(), OnArticleListener {
    lateinit var newsViewModel : NewsViewModel
    lateinit var recycler : RecyclerView
    lateinit var newsAdapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_news, container, false)
        recycler = view.findViewById(R.id.breakingnewsrecyclerview)
        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        newsViewModel.news.observe( viewLifecycleOwner, Observer {
            recycler.apply {
                adapter = ArticleAdapter(it)
                newsAdapter = adapter as ArticleAdapter
                newsAdapter.setOnItemClickListener(this@NewsFragment)

            }
        })
        if (isNetworkAvailable()){
            newsViewModel.getNews()
        }
        else{
            newsViewModel.getOfflineNews()
        }
        return view
    }

    override fun onclick(dataX: DataX) {
    var action = NewsFragmentDirections.actionNewsFragmentToArticleFragment(dataX.url.toString())
        view?.findNavController()?.navigate(action)
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }

}