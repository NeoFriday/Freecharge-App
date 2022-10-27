package com.example.Freecharge;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList plan_id, plan_price, plan_data, plan_validity;
    private String admin, password;

    public MyAdapter(Context context, ArrayList plan_id, ArrayList plan_provider, ArrayList plan_data, ArrayList plan_validity, String admin, String password) {
        this.context = context;
        this.plan_id = plan_id;
        this.plan_price = plan_provider;
        this.plan_data = plan_data;
        this.plan_validity = plan_validity;
        this.admin = admin;
        this.password = password;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.planentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id_entry.setText(String.valueOf(plan_id.get(position)));
        holder.price_entry.setText(String.valueOf(plan_price.get(position)));
        holder.data_entry.setText(String.valueOf(plan_data.get(position)));
        holder.validity_entry.setText(String.valueOf(plan_validity.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Objects.equals(admin, "Infinity") && Objects.equals(password, "1234554321"))
                {

                }
                else
                {
                    Intent confirm = new Intent(context, Verification.class);
                    context.startActivity(confirm);
//                    Toast.makeText(context, "Please wait while we fetching your details...", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return plan_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id_entry, price_entry, data_entry, validity_entry;
        ConstraintLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id_entry = itemView.findViewById(R.id.id_entry);
            price_entry = itemView.findViewById(R.id.price_entry);
            data_entry = itemView.findViewById(R.id.data_entry);
            validity_entry = itemView.findViewById(R.id.validity_entry);

            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}

