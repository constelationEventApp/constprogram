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
import com.develop.constprogram.ui.subscription.FollowingModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import static com.facebook.FacebookSdk.getApplicationContext;

public class FollowingAdapter extends FirestoreRecyclerAdapter<FollowingModel, FollowingAdapter.FollowingHolder> {

    private  Context context;
    private static ClickListener clickListener;
    private String activityName;
    FirebaseUser user;



    public FollowingAdapter(@NonNull FirestoreRecyclerOptions<FollowingModel> options, String activityName) {
        super(options);
        this.activityName=activityName;
    }

    @Override
    protected void onBindViewHolder(@NonNull FollowingHolder holder, int position, @NonNull FollowingModel model) {

        user = FirebaseAuth.getInstance().getCurrentUser();

        holder.name.setText(model.getUserName());

      /*  Picasso.get().load(user.getPhotoUrl())
                .placeholder(R.drawable.image_holder)
                .fit().centerCrop().into(holder.image);*/
      holder.image.setImageResource(R.drawable.image_holder);
            //Picasso.get().load(R.drawable.image_holder).into(holder.image);

        holder.btnBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You Want to Block", Toast.LENGTH_LONG).show();

            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You Want to Delete", Toast.LENGTH_LONG).show();

            }
        });

    }

    @NonNull
    @Override
    public FollowingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_who_follow_me,parent,false);

        return new FollowingHolder(v);
    }

    class FollowingHolder extends RecyclerView.ViewHolder  implements View.OnClickListener, View.OnLongClickListener{
        TextView name;
        ImageView image;
        Button btnBlock;
        Button btnDelete;


        public  FollowingHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            context = itemView.getContext();

            switch (activityName){
                case "Following":
                    name=itemView.findViewById(R.id.followingName);
                    image=itemView.findViewById(R.id.followingImage);
                    btnBlock=itemView.findViewById(R.id.btnBlock);
                    btnDelete=itemView.findViewById(R.id.btnDelete);


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
        FollowingAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }
}

