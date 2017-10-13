package com.frontinelabs.rxsample

import android.annotation.SuppressLint
import android.app.Fragment
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.frontinelabs.rxsample.service.WikiApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_blank.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg


@SuppressLint("ValidFragment")
/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [BlankFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemOneFragment : Fragment() {

    var count : Int = 0
    var disposable: Disposable? = null

    val wikiApiServe by lazy {
        WikiApiService.create()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        async(UI){
            startAnim()
            bg {
                beginSearch("jobs")
            }
            stopAnim()
        }
    }

    fun startAnim() {
        avi.show()
        // or avi.smoothToShow();
    }

    fun stopAnim() {
        avi.hide()
        // or avi.smoothToHide();
    }
    override fun onResume() {
        super.onResume()

    }

    private fun beginSearch(searchString: String) {
        disposable = wikiApiServe.hitCountCheck("query", "json", "search", searchString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> txt_sample.text = "${result.query.search} result found" },
                        { error -> Log.e("error", error.message) }
                )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}// Required empty public constructor
