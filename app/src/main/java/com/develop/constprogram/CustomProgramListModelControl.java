package com.develop.constprogram;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CustomProgramListModelControl extends BaseAdapter implements  Filterable {
    Context context;
    List<CustomProgramListModel> program;
    List<CustomProgramListModel> contactFiltered;
    List<CustomProgramListModel> getContactFilteredFinal;
    LayoutInflater layoutInflater;
    CustomProgramListModel customPgm;
    CustomFilter filter;



    public CustomProgramListModelControl(Context context, ArrayList<CustomProgramListModel> pgm) {
        this.context=context;
        this.program=pgm;
        this.contactFiltered=pgm;
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return program.size();
    }

    @Override
    public Object getItem(int position) {
        return program.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View rowView, ViewGroup parent) {

        if(rowView==null){
            rowView=layoutInflater.inflate(R.layout.custom_list_program,null,true);
        }
     /*   LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);*/

        customPgm=program.get(position);

        TextView programDate = (TextView) rowView.findViewById(R.id.txtDateProgramList);
        TextView programTitle= (TextView) rowView.findViewById(R.id.txtNameProgramList);
        TextView id= (TextView) rowView.findViewById(R.id.identifiantProgramList);
        TextView programAddress = (TextView) rowView.findViewById(R.id.txtAdressProgramList);

        ImageView programMainImage = (ImageView) rowView.findViewById(R.id.imProgramList);

        ImageButton programFavoriteBtn = (ImageButton) rowView.findViewById(R.id.imbFavoriteProgramList);
        ImageButton programShareBtn =  (ImageButton) rowView.findViewById(R.id.imbShareProgramList);





        programMainImage.setImageResource(R.drawable.ic_launcher_background);
        programFavoriteBtn.setImageResource(R.drawable.ic_launcher_background);
        programShareBtn.setImageResource(R.drawable.icon_share);


        programShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Share Button", Toast.LENGTH_LONG).show();
                Intent sharingIntent= new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody="Thank you for sharing our app follow this link to download from playstore";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                context.startActivity(Intent.createChooser(sharingIntent,"Share via"));

            }
        });
        programFavoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Favorite Button", Toast.LENGTH_LONG).show();
            }
        });

        programDate.setText(customPgm.getProgramDate());
        programTitle.setText(customPgm.getProgramName());
        programAddress.setText(customPgm.getProgramAddress());
        id.setText(customPgm.getProgramIdentity());

        System.out.println(customPgm.getProgramAddress());
        Log.d("Test debug",customPgm.getProgramAddress());
        return rowView;

    }

    public Filter getFilter() {
        // TODO Auto-generated method stub
        if(filter == null)
        {
            filter=new CustomFilter();
        }

        return filter;
    }

    class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            // TODO Auto-generated method stub

            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                Log.e("Main", " data search: " + constraint.toString());

                //CONSTARINT TO UPPER
                constraint = constraint.toString().toUpperCase();

                ArrayList<CustomProgramListModel> filters = new ArrayList<CustomProgramListModel>();
                // filters.clear();
                //get specific items
                for (CustomProgramListModel c : contactFiltered) {
                    if (c.getProgramAddress().toUpperCase().contains(constraint) || c.getProgramDate().toUpperCase().contains(constraint) || c.getProgramName().toUpperCase().contains(constraint)) {
                        filters.add(c);
                        //  System.out.println("Adding :" + c.getNom());
                        System.out.println("Str :" + constraint);
                    }


                }
                results.values = filters;
                results.count = filters.size();

            } else {
                results.values = contactFiltered;
                results.count = contactFiltered.size();


            }

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // TODO Auto-generated method stub


            program = (ArrayList<CustomProgramListModel>) results.values;
            notifyDataSetChanged();


        }
    }


}

