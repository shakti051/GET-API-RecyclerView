package com.example.getsync;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    private ArrayList<Person> people;
    ItemClicked activity;

    public interface ItemClicked{
        void onItemClicked(int index);
    }

    public PersonAdapter(Context context, ArrayList<Person> list) {
        people = list;
        activity = (ItemClicked)context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvAge,tvImei;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvImei = itemView.findViewById(R.id.tvImei);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   activity.onItemClicked(people.indexOf((Person)view.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder viewHolder, int i) {
        viewHolder.itemView.setTag(people.get(i));
        viewHolder.tvName.setText("Name: "+people.get(i).getName());
        viewHolder.tvAge.setText("Age: "+people.get(i).getAge());
        viewHolder.tvImei.setText("IMEI: "+people.get(i).getImei());
    }

    @Override
    public int getItemCount() {
        return people.size();
    }
}
