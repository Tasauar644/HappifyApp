package com.example.aumi_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryViewAdapter extends RecyclerView.Adapter<HistoryViewAdapter.ViewHolder> {
    Context context;
    ArrayList<HistoryModelClass> arrayList;

    HistoryViewAdapter(Context context, ArrayList<HistoryModelClass>arrayList){
        this.context=context;
        this.arrayList=arrayList;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_layout,parent,false);
        ViewHolder viewholder=new ViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtNotes.setText(arrayList.get(position).Notes);
        holder.txtTime.setText(arrayList.get(position).Time);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNotes,txtTime;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            txtNotes= itemView.findViewById(R.id.saved_notes);
            txtTime=itemView.findViewById(R.id.saved_times);
        }
    }
}
