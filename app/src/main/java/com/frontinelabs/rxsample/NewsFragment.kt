package com.frontinelabs.rxsample

/**
 * Created by EBaba on 12/10/2017.
 */

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.droidcba.kedditbysteps.commons.RedditNewsItem
import com.droidcba.kedditbysteps.features.news.adapter.NewsDelegateAdapter
import com.frontinelabs.rxsample.adapter.MainAdapter
import kotlinx.android.synthetic.main.news_fragment.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

class NewsFragment : Fragment(), NewsDelegateAdapter.onViewSelectedListener {
    override fun onItemSelected(url: String?) {
        if (url.isNullOrEmpty()) {
            Snackbar.make(news_list, "No URL assigned to this news", Snackbar.LENGTH_LONG).show()
        } else {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }

    var count:Int = 1
    var isLoading: Boolean = false
    private lateinit var layoutManager: LinearLayoutManager

    companion object {
        private val KEY_REDDIT_NEWS = "redditNews"
    }

    private val lastVisibleItemPosition: Int
        get() = layoutManager.findLastVisibleItemPosition()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //val view = inflater.inflate(R.layout.news_fragment, container, false)
        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        news_list.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        news_list.layoutManager = layoutManager
        initAdapter()
        setRecyclerViewScrollListener()


        println("Start")

        // Start a coroutine
        launch(CommonPool) {
            delay(5000)
            Log.e("launch", "Hello")
        }

        Thread.sleep(2000) // wait for 2 seconds
        Log.e("sleep", "wait for 2 seconds")

       /* if (savedInstanceState == null) {
            val handler = Handler()
            handler.postDelayed({
                isLoading = true
                load_data()
            }, 5000)
         }*/
    }

    private fun load_data() {
            val news = mutableListOf<RedditNewsItem>()
            for (i in count..count+10) {
                count++
                news.add(RedditNewsItem(
                        "author$i",
                        "Title $i",
                        i, // number of comments
                        1457207701L - i * 200, // time
                        "http://lorempixel.com/200/200/technics/$i", // image url
                        "url"
                ))
            }
            (news_list.adapter as MainAdapter).addNews(news)
        isLoading = false
    }


    private fun initAdapter() {
        if (news_list.adapter == null) {
            news_list.adapter = MainAdapter(this)
        }
    }
    private fun setRecyclerViewScrollListener() {
        news_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView!!.layoutManager.itemCount
                if (isLoading == false && totalItemCount == lastVisibleItemPosition + 1) {
                    Log.e("bottom", "true")
                    load_data()
                }
            }
        })
    }

}
