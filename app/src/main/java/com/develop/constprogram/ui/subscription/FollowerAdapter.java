package com.develop.constprogram.ui.subscription;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.develop.constprogram.R;
import com.develop.constprogram.ui.subscription.FollowerModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import static com.facebook.FacebookSdk.getApplicationContext;

public class FollowerAdapter extends FirestoreRecyclerAdapter<FollowerModel, FollowerAdapter.FollowerHolder> {

    private  Context context;
    private static ClickListener clickListener;
    private String activityName;
    FirebaseUser user;



    public FollowerAdapter(@NonNull FirestoreRecyclerOptions<FollowerModel> options, String activityName) {
        super(options);
        this.activityName=activityName;
    }

    @Override
    protected void onBindViewHolder(@NonNull FollowerHolder holder, int position, @NonNull FollowerModel model) {

        user = FirebaseAuth.getInstance().getCurrentUser();

        holder.name.setText(model.getOrganizerIdentity());

       /* Picasso.get().load(user.getPhotoUrl())
                .placeholder(R.drawable.image_holder)
                .fit().centerCrop().into(holder.image);*/
        Picasso.get().load(R.drawable.image_holder).into(holder.image);


        holder.btnUnfollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You Want to Unfollow", Toast.LENGTH_LONG).show();

            }
        });

    }

    @NonNull
    @Override
    public FollowerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  v= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_who_i_follow_list,parent,false);
        return new FollowerHolder(v);
    }

    class FollowerHolder extends RecyclerView.ViewHolder  implements View.OnClickListener, View.OnLongClickListener{
        TextView name;
        ImageView image;
        Button btnUnfollow;

        public  FollowerHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            context = itemView.getContext();

            switch (activityName){
                case "Follower":
                    name=itemView.findViewById(R.id.followerName);
                    image=itemView.findViewById(R.id.followerImage);
                    btnUnfollow=itemView.findViewById(R.id.btnUnfollow);

                    break;


                default:

            }

        }
        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);


        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);

            return false;
        }


    }

    public void setOnItemClickListener(ClickListener clickListener) {
        FollowerAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }
}

