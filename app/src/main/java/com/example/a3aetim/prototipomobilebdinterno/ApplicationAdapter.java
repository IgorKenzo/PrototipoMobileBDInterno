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
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    public void setOnitemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class ApplicationViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTitle,mDesc,mId;

        public ApplicationViewHolder(final View itemView, final OnItemClickListener listener){
            super(itemView);
            mImageView = itemView.findViewById(R.id.imgvAppItemMarket);
            mId = itemView.findViewById(R.id.txtIdAppMarket);
            mTitle = itemView.findViewById(R.id.txtTitleAppMarket);
            mDesc = itemView.findViewById(R.id.txtAppDescMarket);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int pos =getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            listener.onItemClick(itemView,pos);
                        }
                    }
                }
            });
        }
    }
    public ApplicationAdapter(ArrayList<Application> appList){
        mAppList = appList;
    }
    @Override
    public ApplicationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.app_item,viewGroup,false);
        ApplicationViewHolder avh = new ApplicationViewHolder(view,mListener);
        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicationViewHolder applicationViewHolder, int i) {
        Application currentApp = mAppList.get(i);
        //applicationViewHolder.mImageView.setImageBitmap();
        applicationViewHolder.mId.setText(String.valueOf(currentApp.get_IdApp()));
        applicationViewHolder.mTitle.setText(currentApp.getTitle());
        applicationViewHolder.mDesc.setText("R$ "+ String.valueOf(currentApp.getPrice()));
    }

    @Override
    public int getItemCount() {
        return mAppList.size();
    }
}
