package com.develop.constprogram;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ProgramAdapter extends FirestoreRecyclerAdapter<ProgramModel, ProgramAdapter.ProgramHolder> {

    private  Context context;
    private static ClickListener clickListener;
    private String activityName;



    public ProgramAdapter(@NonNull FirestoreRecyclerOptions<ProgramModel> options, String activityName) {
        super(options);
        this.activityName=activityName;
    }

    @Override
    protected void onBindViewHolder(@NonNull final ProgramHolder holder, int position, @NonNull final ProgramModel model) {
        holder.programTitle.setText(model.getProgramName());
        holder.programDate.setText(model.getProgramDate());
        holder.programAddress.setText(model.getProgramAddress());
        holder.programIdentity.setText(model.getProgramIdentity());
        holder.btnShare.setImageResource(R.drawable.share);
        holder.btnShare.setBackground(null);

        switch (activityName){
            case "Recycle": case "Dashboard":
                holder.btnFavorite.setImageResource(R.drawable.favorite);

                break;
            case "Favorite":
                holder.btnDelete.setImageResource(R.drawable.delete_program_from_favorite);
                holder.btnDelete.setBackground(null);

                break;
            case "MyProgram":
                holder.btnDelete.setImageResource(R.drawable.delete_program_from_favorite);
                holder.btnDelete.setBackground(null);
                holder.btnUpdate.setVisibility(View.VISIBLE);
                holder.btnUpdate.setBackground(null);
                holder.btnUpdate.setImageResource(R.drawable.edit_program);





                break;
            default:

        }





        Picasso.get().load(model.getProgramImage().toString())
                .placeholder(R.drawable.image_holder)
                .fit().centerCrop().into(holder.programImage);


        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Share Button", Toast.LENGTH_LONG).show();
                Intent sharingIntent= new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody="Thank you for sharing our app follow this link to download from playstore";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                context.startActivity(Intent.createChooser(sharingIntent,"Share via"));

            }
        });

        switch (activityName){
            case "Recycle":
            case "Dashboard":
                holder.btnFavorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Favorite Button"+model.getProgramIdentity(), Toast.LENGTH_LONG).show();

                        ProgramModel programModel= new ProgramModel();
                        programModel.addToFavorite(model.getProgramIdentity());


                    }
                });
                break;
            default:
        }


    }

    @NonNull
    @Override
    public ProgramHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        switch (activityName){
            case "Recycle":
                 v= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_program,parent,false);

                break;
            case "Favorite":
                v= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_favorite_list_program,parent,false);

                break;
            case "MyProgram":
                v= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_my_program_list,parent,false);

                break;
            case "Dashboard":
                 v= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_dashboard,parent,false);

                break;

            default:
                 v= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_program,parent,false);

        }
        return new ProgramHolder(v);
    }

    class ProgramHolder extends RecyclerView.ViewHolder  implements View.OnClickListener, View.OnLongClickListener{
        TextView programTitle;
        TextView programDate;
        TextView programAddress;
        TextView programIdentity;
        ImageView programImage;
        ImageButton btnShare;
        ImageButton btnFavorite;
        ImageButton btnUpdate;
        ImageButton btnDelete;

        public  ProgramHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            context = itemView.getContext();

            switch (activityName){
                case "Recycle":
                    programTitle=itemView.findViewById(R.id.txtNameProgramList);
                    programDate=itemView.findViewById(R.id.txtDateProgramList);
                    programAddress=itemView.findViewById(R.id.txtAdressProgramList);
                    programIdentity=itemView.findViewById(R.id.identifiantProgramList);
                    programImage=itemView.findViewById(R.id.imProgramList);
                    btnShare=itemView.findViewById(R.id.imbShareProgramList);
                    btnFavorite=itemView.findViewById(R.id.imbFavoriteProgramList);
                    break;
                case "Favorite":
                    programTitle=itemView.findViewById(R.id.txtNameFavoriteProgramList);
                    programDate=itemView.findViewById(R.id.txtDateFavoriteProgramList);
                    programAddress=itemView.findViewById(R.id.txtAdressFavoriteProgramList);
                    programIdentity=itemView.findViewById(R.id.identifiantFavoriteProgramList);
                    programImage=itemView.findViewById(R.id.imFavoriteProgramList);
                    btnShare=itemView.findViewById(R.id.imbShareFavoriteProgramList);
                    btnDelete=itemView.findViewById(R.id.imbDeleteFavoriteProgramList);

                    break;

                case "MyProgram":

                    if(activityName=="MyProgram"){
                        programTitle=itemView.findViewById(R.id.txtNameMyProgramList);
                        programDate=itemView.findViewById(R.id.txtDateMyProgramList);
                        programAddress=itemView.findViewById(R.id.txtAdressMyProgramList);
                        programIdentity=itemView.findViewById(R.id.identifiantMyProgramList);
                        programImage=itemView.findViewById(R.id.imMyProgramList);
                        btnShare=itemView.findViewById(R.id.imbShareMyProgramList);
                        btnUpdate=itemView.findViewById(R.id.imbUpdateMyProgramList);
                        btnDelete=itemView.findViewById(R.id.imbDeleteMyProgramList);


                    }

                    break;

                case "Dashboard":
                    programTitle=itemView.findViewById(R.id.txtNameProgramDashboard);
                    programDate=itemView.findViewById(R.id.txtDateProgramDashboard);
                    programAddress=itemView.findViewById(R.id.txtAdressProgramDashboard);
                    programIdentity=itemView.findViewById(R.id.identifiantProgramDashboard);
                    programImage=itemView.findViewById(R.id.imProgramDashboard);
                    btnShare=itemView.findViewById(R.id.imbShareProgramDashboard);
                    btnFavorite=itemView.findViewById(R.id.imbFavoriteProgramDashboard);
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
        ProgramAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }
}

