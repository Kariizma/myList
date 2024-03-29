package com.f4csci380.mylist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder>
{
    public static final String TAG = "MemoAdapter";
    private Context context;
    private ArrayList<Memo> memo;
    private static ClickListener clickListener;

    public MemoAdapter(Context context, ArrayList<Memo> memo)
    {
        this.context = context;
        this.memo = memo;
    }

    @NonNull
    @Override
    public MemoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_listitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoAdapter.ViewHolder holder, int position)
    {
        if(memo.size() == 0)
        {}
        holder.listitem.setText(memo.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return memo.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
        private TextView listitem;
        private LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            listitem = itemView.findViewById(R.id.listItem);
            linearLayout = itemView.findViewById(R.id.LL);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            clickListener.onItemClick(getAdapterPosition(),v);
        }

        @Override
        public boolean onLongClick(View v)
        {
            clickListener.onItemLongClick(getAdapterPosition(),v);
            return false;
        }
    }

    public void setOnItemClickListner(ClickListener clickListner)
    {
        MemoAdapter.clickListener = clickListner;
    }

    public interface ClickListener
    {
        void onItemClick(int position,View v);
        void onItemLongClick(int position, View v);
    }
}
