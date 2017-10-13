package com.frontinelabs.rxsample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frontinelabs.rxsample.CustomItemClickListener;
import com.frontinelabs.rxsample.R;
import com.frontinelabs.rxsample.model.DataBus;
import com.frontinelabs.rxsample.model.DataProductShop;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList<DataProductShop> mAndroidList;
    private CustomItemClickListener mListener;
    Context mContext;

    public DataAdapter(Context context, ArrayList<DataProductShop> androidList,
                       CustomItemClickListener
            listener) {
        mAndroidList = androidList;
        mListener = listener;
        mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        ViewHolder pvh = new ViewHolder(view, mContext);

        view.setOnClickListener(v -> mListener.onItemClick(v, pvh.getAdapterPosition()));
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTvName.setText(mAndroidList.get(position).getTitle());
        holder.mTvVersion.setText(mAndroidList.get(position).getProductId());
        holder.mTvApi.setText(mAndroidList.get(position).getSalePrice().toString());
    }

    public void setItemToPostion(DataBus dataBus, int itemPosition) {

        for (int i = 0; i < mAndroidList.size(); i++){
            Log.e("Postion",mAndroidList.get(i).getProductId()+"="+dataBus.getId());
            if(mAndroidList.get(i).getProductId().equals(dataBus.getId())){
                Log.e("setItemToPostion", String.valueOf(dataBus.getId()));

                mAndroidList.get(i).setTitle(dataBus.getData());
                notifyItemChanged(i);
            }else{

            }
        }

    }
    @Override
    public int getItemCount() {
        return mAndroidList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvName, mTvVersion, mTvApi;

        public ViewHolder(View view, Context c) {
            super(view);

            mTvName = view.findViewById(R.id.tv_name);
            mTvVersion = view.findViewById(R.id.tv_version);
            mTvApi = view.findViewById(R.id.tv_api_level);
        }
    }

    public interface PostItemListener {
        void onPostClick(long id);
    }

    public DataProductShop getListModel(int position) {
        return mAndroidList.get(position);
    }


}
