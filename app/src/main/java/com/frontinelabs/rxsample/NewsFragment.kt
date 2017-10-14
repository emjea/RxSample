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
import android.widget.Toast
import com.droidcba.kedditbysteps.commons.RedditNewsItem
import com.droidcba.kedditbysteps.features.news.adapter.NewsDelegateAdapter
import com.frontinelabs.rxsample.adapter.MainAdapter
import com.frontinelabs.rxsample.service.SearchRepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.news_fragment.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

class NewsFragment : Fragment(), NewsDelegateAdapter.onViewSelectedListener {
    override fun onItemSelected(url: String?) {
        Toast.makeText(context, url, Toast.LENGTH_LONG).show()
    }

    var count: Int = 1
    var isLoading: Boolean = false
    private lateinit var layoutManager: LinearLayoutManager
    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val repository = SearchRepositoryProvider.provideSearchRepository()
    val news = mutableListOf<RedditNewsItem>()

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


        Log.e("launch", "start")


        // Start a coroutine
        //get_data()


        load_json_data()
    }

    private fun load_json_data() {
        isLoading = true
        news.clear()
        Log.e("page", count.toString())
        compositeDisposable.add(
                repository.searchUsers("Medan", "Java", count)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({ result ->
                            for (i in 0 until result.items.size) {
                                news.add(RedditNewsItem(
                                        result.items[i].score.toString(),
                                        result.items[i].login,
                                        5, // number of comments
                                        1457207701L - i * 200, // time
                                        result.items[i].avatar_url, // image url
                                        result.items[i].url
                                ))
                                /*Log.e("Result", "There are ${result.items[i].login} Python " +
                                        "developers in " +
                                        "Jayapura")*/
                            }
                            count++
                            (news_list.adapter as MainAdapter).addNews(news)
                            //isLoading = false
                            Log.e("loading", isLoading.toString())
                            Log.e("page after", count.toString())
                            Log.e("loading", isLoading.toString())
                            Log.e("total", news_list.adapter.itemCount.toString())
                            Log.e("lastVisibleItemPosition", lastVisibleItemPosition.toString())

                            if (result.items.size < 10) {
                                Log.e("loading", "1")

                                isLoading = true
                            } else {
                                Log.e("loading", "2")

                                isLoading = false
                            }

                        }, { error ->
                            Log.e("error", error.printStackTrace().toString())
                        })
        )

    }

    private fun get_data() {
        if (count >= 40)
            news_list.adapter.notifyItemRemoved(count)
        else
            launch(CommonPool) {
                isLoading = true
                delay(5000)
                load_data()
                Log.e("launch", "Hello")
            }

    }

    private fun load_data() {
        val news = mutableListOf<RedditNewsItem>()
        for (i in count..count + 9) {
            news.add(RedditNewsItem(
                    "author$i",
                    "Title $i",
                    i, // number of comments
                    1457207701L - i * 200, // time
                    "http://lorempixel.com/200/200/technics/$i", // image url
                    "url"
            ))
            count++

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

                Log.e("bottom", totalItemCount.toString())
                if (!isLoading && isLastItemDisplaying(recyclerView)) {
                    Log.e("bottom", "true")
                    // get_data()
                    Toast.makeText(context, "Loading", Toast.LENGTH_LONG).show()
                    load_json_data()
                }else{
                    news_list.adapter.notifyItemRemoved(totalItemCount)

                }

            }

        })
    }

    private fun isLastItemDisplaying(recyclerView: RecyclerView): Boolean {
        if (recyclerView.adapter.itemCount != 0) {
            val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
            if (lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == recyclerView.adapter.itemCount - 1)
                return true
        }
        return false
    }
}
