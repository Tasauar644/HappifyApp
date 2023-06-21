package com.example.aumi_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class contactViewAdapter extends RecyclerView.Adapter<contactViewAdapter.Viewholder> {

    Context context;
    ArrayList<contactModelClass> contactList;


    public  contactViewAdapter(Context context,ArrayList<contactModelClass>contactList){
        this.context=context;
        this.contactList=contactList;

    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.contact_list_layout,parent,false);
        Viewholder viewholder= new Viewholder(view);
        return viewholder;

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        holder.name.setText(contactList.get(position).contactNameModel);
        holder.number.setText(contactList.get(position).contactNumberModel);

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView name,number;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
             name= itemView.findViewById(R.id.fetchedContactName);
             number= itemView.findViewById(R.id.fetchedContactNumber);
        }
    }
}
