package com.example.a3aetim.prototipomobilebdinterno;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.ApplicationViewHolder> {
    private ArrayList<Application> mAppList;

    public static class ApplicationViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTitle,mDesc;
        public ApplicationViewHolder(View itemView){
            super(itemView);
            mImageView = itemView.findViewById(R.id.imgvAppItemMarket);
            mTitle = itemView.findViewById(R.id.txtTitleAppMarket);
            mDesc = itemView.findViewById(R.id.txtAppDescMarket);
        }
    }
    public ApplicationAdapter(ArrayList<Application> appList){
        mAppList = appList;
    }
    @Override
    public ApplicationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.app_item,viewGroup,false);
        ApplicationViewHolder avh = new ApplicationViewHolder(view);
        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicationViewHolder applicationViewHolder, int i) {
        Application currentApp = mAppList.get(i);
        //applicationViewHolder.mImageView.setImageBitmap();
        applicationViewHolder.mTitle.setText(currentApp.getTitle());
        applicationViewHolder.mDesc.setText(String.valueOf(currentApp.getPreco()));
    }

    @Override
    public int getItemCount() {
        return mAppList.size();
    }
}
