package com.example.strokeawareness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends  RecyclerView.Adapter<MyAdapter.MyViewHolder>{


        Context context;
        ArrayList<History> history;

        public MyAdapter(Context c , ArrayList<History> p)
        {
            context = c;
            history = p;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.risk.setText(history.get(position).getCategory());
            holder.category.setText(history.get(position).getRisk());

        }

        @Override
        public int getItemCount() {
            return history.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {
            TextView risk,category;
            public MyViewHolder(View itemView) {
                super(itemView);
                risk = (TextView) itemView.findViewById(R.id.riskTV);
                category = (TextView) itemView.findViewById(R.id.categoryTV);
            }
            public void onClick(final int position)
            {
                /*btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, position+" is clicked", Toast.LENGTH_SHORT).show();
                    }
                });  */
            }
        }

}
