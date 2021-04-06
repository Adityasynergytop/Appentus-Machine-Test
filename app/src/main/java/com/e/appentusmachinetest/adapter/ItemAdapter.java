package com.e.appentusmachinetest.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.Toast;
 
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.e.appentusmachinetest.R;
import com.e.appentusmachinetest.model.ImageResponsemodel;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ItemAdapter extends PagedListAdapter<ImageResponsemodel, ItemAdapter.ItemViewHolder> {
 
    private Context mCtx;
 
    public ItemAdapter(Context mCtx) {
        super(DIFF_CALLBACK);
        this.mCtx = mCtx;
    }
 
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.row_images_item, parent, false);
        return new ItemViewHolder(view);
    }
 
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ImageResponsemodel item = getItem(position);
 
        if (item != null) {

            // Load image from network
            Glide.with(mCtx)
                    .load(item.getDownloadUrl())
                    .apply(new RequestOptions().placeholder(R.drawable.placeholder_image).error(R.drawable.error_image))
                    .into(holder.imageView);
        }else{
            Toast.makeText(mCtx, "Item is null", Toast.LENGTH_LONG).show();
        }
    }
 
    private static DiffUtil.ItemCallback<ImageResponsemodel> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ImageResponsemodel>() {
                @Override
                public boolean areItemsTheSame(ImageResponsemodel oldItem, ImageResponsemodel newItem) {
                    return oldItem.getId() == newItem.getId();
                }
 
                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(ImageResponsemodel oldItem, ImageResponsemodel newItem) {
                    return oldItem.equals(newItem);
                }
            };
 
    class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
 
        public ItemViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.iv_images);
        }
    }
}