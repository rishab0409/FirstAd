package com.example.firstad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class statsAdapter  extends FirebaseRecyclerAdapter<stats,statsAdapter.statsViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public statsAdapter(@NonNull FirebaseRecyclerOptions<stats> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull statsViewHolder holder, int position, @NonNull stats model) {
        holder.statshead.setText(model.getHead());
        holder.statsfield1.setText(model.getField1());
        holder.statsfield2.setText(model.getField2());
        holder.statsfield3.setText(model.getField3());
        holder.statsfield4.setText(model.getField4());
        holder.statsvalue1.setText(model.getValue1());
        holder.statsfield2.setText(model.getValue2());
        holder.statsvalue3.setText(model.getValue3());
        holder.statsvalue4.setText(model.getValue4());
        Picasso.get().load(model.getImg()).into(holder.statsimg);

    }

    @NonNull
    @Override
    public statsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.statsxml, parent, false);

        return new statsAdapter.statsViewHolder(view);
    }

    class statsViewHolder extends RecyclerView.ViewHolder{

        TextView statshead,statsfield1,statsvalue1,statsfield2,statsvalue2,statsfield3,statsvalue3,statsfield4,statsvalue4;
        ImageView statsimg;

    public statsViewHolder(@NonNull View itemView) {
        super(itemView);

        statshead=itemView.findViewById(R.id.statshead);
        statsfield1=itemView.findViewById(R.id.statsfield1);
        statsvalue1=itemView.findViewById(R.id.statsvalue1);
        statsfield2=itemView.findViewById(R.id.statsfield2);
        statsvalue2=itemView.findViewById(R.id.statsvalue2);
        statsfield3=itemView.findViewById(R.id.statsfield3);
        statsvalue3=itemView.findViewById(R.id.statsvalue3);
        statsfield4=itemView.findViewById(R.id.statsfield4);
        statsvalue4=itemView.findViewById(R.id.statsvalue4);
        statsimg=itemView.findViewById(R.id.statsimg);

    }
}


    }

