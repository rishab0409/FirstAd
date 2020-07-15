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

import static com.squareup.picasso.Picasso.get;

public class PostAdapter extends FirebaseRecyclerAdapter<Post, PostAdapter.PostViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PostAdapter(@NonNull FirebaseRecyclerOptions<Post> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PostViewHolder holder, int position, @NonNull Post model) {
        holder.title.setText(model.getTitle());
        holder.description.setText(model.getDescription());
        holder.company_name.setText(model.getCompany_name());
        holder.date.setText(model.getDate());
        Picasso.get().load(model.getImg_url()).into(holder.company_img1);
        Picasso.get().load(model.getImg_url()).into(holder.company_img2);

    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post, parent, false);

        return new PostViewHolder(view);
    }

    class PostViewHolder extends RecyclerView.ViewHolder{

        TextView title,description,company_name,date;
        ImageView company_img1,company_img2;


        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
            company_name=itemView.findViewById(R.id.company_name);
            date=itemView.findViewById(R.id.date);
            company_img1=itemView.findViewById(R.id.company_img1);
            company_img2=itemView.findViewById(R.id.company_img2);
        }
    }
}
