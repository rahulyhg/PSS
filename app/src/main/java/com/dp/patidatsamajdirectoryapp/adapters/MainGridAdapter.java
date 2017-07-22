package com.dp.patidatsamajdirectoryapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dp.patidatsamajdirectoryapp.R;
import com.dp.patidatsamajdirectoryapp.activities.AddMember;
import com.dp.patidatsamajdirectoryapp.activities.Directory;

/**
 * Created by ps11 on 01/07/17.
 */

public class MainGridAdapter extends RecyclerView.Adapter<MainGridAdapter.ViewHolder> {


    Context context;

    public MainGridAdapter(Context context) {
        this.context = context;
    }

    int icons[] = {
    R.mipmap.add_menu,
    R.mipmap.agri,
    R.mipmap.bjsn,
    R.mipmap.blood_bank,
    R.mipmap.calender,
    R.mipmap.directry,
    R.mipmap.education,
    R.mipmap.event,
    R.mipmap.help_line,
    R.mipmap.histry,
    R.mipmap.karyakarini,
    R.mipmap.kundali,
    R.mipmap.matri,
    R.mipmap.news,
    R.mipmap.sandesh,
    R.mipmap.seting,};

    String texts[] = {
            "Add Member",
            "Agriculture",
            "Business",
            "Blood Bank",
            "Calendar",
            "Directory",
            "Education",
            "Event",
            "Help Line",
            "History",
            "Karyakarini",
            "Jobs",
            "Matrimonial",
            "News",
            "Sandesh",
            "Settings"
    };


    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_grid_prototype, parent, false);


        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.icon.setImageResource(icons[position]);
        holder.text.setText(texts[position]);


            holder.icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String text = holder.text.getText().toString();
                    switch(text){

                        case "Add Member" : {context.startActivity(new Intent(context, AddMember.class));}
                        case "Directory" : {context.startActivity(new Intent(context, Directory.class));}

                    }
                }
            });

    }

    @Override
    public int getItemCount() {
        return icons.length;
    }



    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView text;
        public ViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            text = (TextView)itemView.findViewById(R.id.text);
        }
    }
}
