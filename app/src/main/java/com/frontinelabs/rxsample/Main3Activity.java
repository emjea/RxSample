package com.frontinelabs.rxsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.frontinelabs.rxsample.adapter.AnswersAdapter;
import com.frontinelabs.rxsample.model.DataBus;
import com.frontinelabs.rxsample.model.Item;
import com.frontinelabs.rxsample.model.ModelItems;
import com.frontinelabs.rxsample.model.MyRxBus;
import com.frontinelabs.rxsample.service.ApiUtils;
import com.frontinelabs.rxsample.service.SOService;



import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main3Activity extends AppCompatActivity {
    private AnswersAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SOService mService;
    TextView txt_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mService = ApiUtils.getSOService();
        mRecyclerView = findViewById(R.id.rv_answers);
        mAdapter = new AnswersAdapter(this, new ArrayList<Item>(0), id -> MyRxBus.publish
                ("other "+ id));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        txt_click = findViewById(R.id.txt_click);

        txt_click.setOnClickListener(view ->  MyRxBus.publish
                (new DataBus(3, "54", "titel"))
        );
        loadAnswers();
    }

    public void loadAnswers() {
        mService.getAnswers().enqueue(new Callback<ModelItems>() {
            @Override
            public void onResponse(Call<ModelItems> call, Response<ModelItems> response) {

                if(response.isSuccessful()) {
                    mAdapter.updateAnswers(response.body().getItems());
                    Log.d("MainActivity", "posts loaded from API");
                }else {
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<ModelItems> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");

            }
        });
    }

    public void loadAnswersRx() {


    }
}
