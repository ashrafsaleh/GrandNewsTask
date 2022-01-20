package com.example.grandnews.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.grandnews.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ArticleFragment : Fragment() {
    val args: ArticleFragmentArgs by navArgs()
    lateinit var webView: WebView
    lateinit var fab: FloatingActionButton

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
        var view = inflater.inflate(R.layout.fragment_article, container, false)
        webView=view.findViewById(R.id.articlewebview)
        fab=view.findViewById(R.id.floatingbutton)
        val article = args.articleUrl
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl(article!!)
        fab.setOnClickListener{
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, article);
            startActivity(Intent.createChooser(shareIntent,("test")))
        }
        return view
    }

}