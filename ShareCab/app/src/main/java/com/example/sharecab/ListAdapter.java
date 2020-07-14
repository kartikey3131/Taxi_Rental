package com.example.sharecab;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.listholder> {

    Context context;
    ArrayList<User2> data;

    public ListAdapter(Context context, ArrayList<User2> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ListAdapter.listholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.list,parent,false);
        listholder l =new listholder(view);
        return l;
    }

    @Override
    public void onBindViewHolder(@NonNull listholder holder, int position) {
        final User2 u = data.get(position);
        holder.name.setText(data.get(position).getName());
        holder.contact.setText(data.get(position).getContact());
        holder.price.setText(data.get(position).getPrice());
        holder.number.setText(data.get(position).getCarNumber());
        holder.model.setText(data.get(position).getModel());

//        holder.name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(context,RiderProfile.class);
//                intent.putExtra("Name",u.getName());
//                context.startActivity(intent);
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class listholder extends RecyclerView.ViewHolder {
        TextView name;
        TextView contact;
        TextView model;
        TextView number;
        TextView price;
        public listholder(@NonNull View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.Name);
            contact =itemView.findViewById(R.id.contact);
            model =itemView.findViewById(R.id.model);
            number =itemView.findViewById(R.id.number);
            price =itemView.findViewById(R.id.price);
        }
    }
}
