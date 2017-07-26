package com.dp.patidatsamajdirectoryapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dp.patidatsamajdirectoryapp.R;
import com.dp.patidatsamajdirectoryapp.pojo.agricultureResponse.Datum;

import java.util.List;

/**
 * Created by ps11 on 16/07/17.
 */

public class AgricultureAdapter extends RecyclerView.Adapter<AgricultureAdapter.ViewHolder> {

    List<Datum> usersData;
    Context context;


    public AgricultureAdapter(List<Datum> usersData, Context context) {
        this.usersData = usersData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.prototype_agriculture, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.name.setText(usersData.get(position).getNewsTitle());
        holder.occupation.setText(usersData.get(position).getNewsDesc());
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //TODO: Next Activity to show full Article.
            }
        });



    }

    @Override
    public int getItemCount() {
        return usersData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,occupation;
        Button call;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            occupation = (TextView) itemView.findViewById(R.id.occupation);
            occupation.setSelected(true);
            call = (Button) itemView.findViewById(R.id.call);
        }
    }
}
