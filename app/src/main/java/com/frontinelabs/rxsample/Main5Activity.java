package com.frontinelabs.rxsample;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.frontinelabs.rxsample.adapter.DataAdapter;
import com.frontinelabs.rxsample.adapter.SliderAdapter;
import com.frontinelabs.rxsample.model.DataBus;
import com.frontinelabs.rxsample.model.DataProductShop;
import com.frontinelabs.rxsample.model.DataSlider;
import com.frontinelabs.rxsample.model.ModelItems;
import com.frontinelabs.rxsample.model.ModelProduct;
import com.frontinelabs.rxsample.model.ModelSlider;
import com.frontinelabs.rxsample.model.MyRxBus;
import com.frontinelabs.rxsample.service.RequestInterface;
import com.github.piasy.biv.BigImageViewer;
import com.github.piasy.biv.loader.fresco.FrescoImageLoader;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main5Activity extends AppCompatActivity {
    public static final String BASE_URL = "https://api.ebaba.co.id/";
    public static final String BASE_URL_SLIDER = "https://api.ebaba.co.id/";

    AVLoadingIndicatorView avi;
    private RecyclerView mRecyclerView;

    private CompositeDisposable mCompositeDisposable;

    private DataAdapter mAdapter;

    private ArrayList<DataProductShop> mAndroidArrayList;
    private ArrayList<DataSlider> sliderArrayList;

    private RollPagerView mLoopViewPager;

    SliderAdapter mLoopAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        BigImageViewer.initialize(FrescoImageLoader.with(this));

        mLoopViewPager = findViewById(R.id.loop_view_pager3);
        avi = findViewById(R.id.avi);
        mCompositeDisposable = new CompositeDisposable();



        initRecyclerView();
       /* Observable.fromCallable(() -> {
            avi.show();
            loadJSON();

            return false;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    avi.hide();
                });

        Observable.fromCallable(() -> {
            avi.show();
            loadSlider();

            return false;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {

                    avi.hide();
                });*/


        Tasks.executeInBackground(this, new BackgroundWork<String>() {
            @Override
            public String doInBackground() throws Exception {
                loadJSON();

                return "";
            }
        }, new Completion<String>() {
            @Override
            public void onSuccess(Context context, String result) {

            }

            @Override
            public void onError(Context context, Exception e) {
                e.printStackTrace();
                //Toast.makeText(context, "Gagal mengambil data", Toast.LENGTH_LONG).show();
            }
        });
        Tasks.executeInBackground(this, () -> {
            loadSlider();

            return "";
        }, new Completion<String>() {
            @Override
            public void onSuccess(Context context, String result) {

            }

            @Override
            public void onError(Context context, Exception e) {
                e.printStackTrace();
                //Toast.makeText(context, "Gagal mengambil data", Toast.LENGTH_LONG).show();
            }
        });
        MyRxBus.subscribe((message) -> {
            if (message instanceof DataBus) {
                Log.v("Main5", ((DataBus) message).getData());
            }
        });
    }

    private void loadSlider() {
        RequestInterface requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_URL_SLIDER)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RequestInterface.class);
        mCompositeDisposable.add(requestInterface.load_slider()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseSlider,this::handleErrorSlider));
    }

    private void handleResponseSlider(ModelSlider modelSlider) {
        Log.e("handleResponseSlider", String.valueOf(modelSlider.getData()));

        sliderArrayList = new ArrayList<>(modelSlider.getData());
        mLoopAdapter = new SliderAdapter(this, R.layout.item_viewager_banner, mLoopViewPager,
                sliderArrayList);
        mLoopViewPager.setPlayDelay(5000);
        mLoopViewPager.setAdapter(mLoopAdapter);

        mLoopViewPager.setHintView(
                new ColorPointHintView(this, ContextCompat.getColor(this, R.color.colorPrimary), Color.GRAY));
    }

    private void handleErrorSlider(Throwable error) {
        Log.e("error", "Error "+error.getLocalizedMessage());

    }

    private void initRecyclerView() {

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void loadJSON() {
        avi.show();
        RequestInterface requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RequestInterface.class);

        mCompositeDisposable.add(requestInterface.load_product("termurah")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));
    }

    private void handleResponse(ModelProduct modelProduct) {
        Log.e("handleResponse", String.valueOf(modelProduct.getData()));
        mAndroidArrayList = new ArrayList<>(modelProduct.getData());
        mAdapter = new DataAdapter(this, mAndroidArrayList, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.e("pos", mAdapter.getListModel(position).getTitle());
                MyRxBus.publish
                        (new DataBus(position, mAdapter.getListModel(position).getProductId(), mAdapter.getListModel(position).getTitle()));
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        avi.hide();
    }

    private void handleResponse(List<ModelItems> androidList) {

        Log.e("handleResponse", String.valueOf(androidList.size()));
        /*mAndroidArrayList = new ArrayList<>(androidList);
        mAdapter = new DataAdapter(mAndroidArrayList);
        mRecyclerView.setAdapter(mAdapter);*/
    }

    private void handleError(Throwable error) {
        avi.hide();

        //Toast.makeText(this, "Error "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        Log.e("error", "Error "+error.getLocalizedMessage());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }
}
