package com.dp.patidatsamajdirectoryapp.adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dp.patidatsamajdirectoryapp.R;
import com.dp.patidatsamajdirectoryapp.pojo.directoryUserResponse.LastDatum;

import java.util.List;

/**
 * Created by ps11 on 16/07/17.
 */

public class DirectoryListAdapter extends RecyclerView.Adapter<DirectoryListAdapter.ViewHolder> {

    List<LastDatum> usersData;
    Context context;


    public DirectoryListAdapter(List<LastDatum> usersData, Context context) {
        this.usersData = usersData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.prototype_directory_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.name.setText(usersData.get(position).getName());
        holder.occupation.setText(usersData.get(position).getOccupation());
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling

                    Intent i = new Intent(Intent.ACTION_DIAL);
                    i.setData(Uri.parse("tel:"+usersData.get(position).getMobile()));
                    context.startActivity(i);
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }

            }
        });



    }

    @Override
    public int getItemCount() {
        return usersData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,occupation;
        ImageButton call;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            occupation = (TextView) itemView.findViewById(R.id.occupation);
            occupation.setSelected(true);
            call = (ImageButton) itemView.findViewById(R.id.call);
        }
    }
}
