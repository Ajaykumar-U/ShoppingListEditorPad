package com.kotlinandroid.shoppinglist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter {

    boolean check;

    List<ShoppingItemEntity> dataList;
    Context ctx;

    public RecyclerAdapter(List<ShoppingItemEntity> dataList, Context ctx) {
        this.dataList = dataList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1= (ViewHolder) holder;
        ShoppingItemEntity shoppingItemEntity = dataList.get(position);
        holder1.tvItemName.setText("Name   : "+shoppingItemEntity.getItemName());
        holder1.tvItemWeight.setText("Weight : "+shoppingItemEntity.getItemWeight());
        if(shoppingItemEntity.isCheckItem()){
            holder1.checkBox.setChecked(true);
        }
        else{
            holder1.checkBox.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvItemName,tvItemWeight;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvItemWeight = itemView.findViewById(R.id.tvItemWeight);
            checkBox = itemView.findViewById(R.id.checkBox);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ShoppingItemEntity shoppingItemSelected = dataList.get(getAdapterPosition());
            Intent intent = new Intent(ctx,UpdateActivity.class);
            intent.putExtra("task",shoppingItemSelected);
            ctx.startActivity(intent);
        }
    }
}
