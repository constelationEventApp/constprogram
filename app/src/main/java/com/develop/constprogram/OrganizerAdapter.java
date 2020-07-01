package com.develop.constprogram;

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

import com.develop.constprogram.ui.subscription.FollowerAdapter;
import com.develop.constprogram.ui.subscription.FollowerModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import static com.facebook.FacebookSdk.getApplicationContext;

public class OrganizerAdapter extends FirestoreRecyclerAdapter<OrganizerModel, OrganizerAdapter.OrganizerHolder> {

    private  Context context;
    private static ClickListener clickListener;
    private String activityName;
    FirebaseUser user;



    public OrganizerAdapter(@NonNull FirestoreRecyclerOptions<OrganizerModel> options, String activityName) {
        super(options);
        this.activityName=activityName;
    }

    @Override
    protected void onBindViewHolder(@NonNull OrganizerHolder holder, int position, @NonNull final OrganizerModel model) {

        user = FirebaseAuth.getInstance().getCurrentUser();

        holder.organizerName.setText(model.getOrganizerName());
        holder.organizerIdentity.setText(model.getOrganizerIdentity());

        if(model.getOrganizerCounterFollower()<=1){
            holder.organizerCountFollower.setText(model.getOrganizerCounterFollower()+ "Follower");
        }else{
            holder.organizerCountFollower.setText(model.getOrganizerCounterFollower()+ "Followers");
        }
        holder.btnFollow.setText("Follow");

        if(user.getDisplayName()==model.getOrganizerName()){
            Picasso.get().load(user.getPhotoUrl())
                    .placeholder(R.drawable.image_holder)
                    .fit().centerCrop().into(holder.organizerImage);

        }else{
            Picasso.get().load(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .fit().centerCrop().into(holder.organizerImage);
        }



        holder.btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You Want to follow", Toast.LENGTH_LONG).show();
                FollowerModel followerModel = new FollowerModel();
                followerModel.iFollowYou(model.getOrganizerIdentity());
            }
        });


    }

    @NonNull
    @Override
    public OrganizerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        switch (activityName){
            case "whoToFollow":

                v= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_organizer_list,parent,false);

                break;
            case "Dashboard":
                v= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_dashboard,parent,false);

                break;

            default:
                v= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_program,parent,false);

        }
        return new OrganizerHolder(v);
    }

    class OrganizerHolder extends RecyclerView.ViewHolder  implements View.OnClickListener, View.OnLongClickListener{
        TextView organizerName;
        TextView organizerIdentity;
        TextView organizerCountFollower;
        ImageView organizerImage;
        Button btnFollow;

        public  OrganizerHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            context = itemView.getContext();

            switch (activityName){
                case "whoToFollow":
                    organizerName=itemView.findViewById(R.id.txtNameOrganizerList);
                    organizerImage=itemView.findViewById(R.id.imOrganizerList);
                    organizerCountFollower=itemView.findViewById(R.id.countFollowerOrganizerList);
                    btnFollow=itemView.findViewById(R.id.btnFollowOrganizer);
                    organizerIdentity=itemView.findViewById(R.id.identifiantOrganizerList);

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
        OrganizerAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }
}

