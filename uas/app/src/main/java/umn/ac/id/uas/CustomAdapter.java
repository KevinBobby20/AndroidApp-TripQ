package umn.ac.id.uas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    Activity activity;
    ArrayList place_name, place_date, place_reminder, place_catatan;
    ArrayList id;
    MyDatabaseHelper myDatabaseHelper;
    int position;

    CustomAdapter(Activity activity, Context context, ArrayList id, ArrayList place_name, ArrayList place_date, ArrayList place_reminder, ArrayList place_catatan){
        this.activity = activity;
        this.context = context;
        this.place_name = place_name;
        this.place_date = place_date;
        this.place_reminder = place_reminder;
        this.place_catatan = place_catatan;
        this.id = id;
        myDatabaseHelper = new MyDatabaseHelper(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.jadwal_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        this.position = position;
        holder.placeName.setText(String.valueOf(place_name.get(position)));
        holder.placeDate.setText(String.valueOf(place_date.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("name", String.valueOf(place_name.get(position)));
                intent.putExtra("date", String.valueOf(place_date.get(position)));
                intent.putExtra("reminder", String.valueOf(place_reminder.get(position)));
                intent.putExtra("catatan", String.valueOf(place_catatan.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return place_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView placeName, placeDate;
        ImageView mainLayout;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            placeName = itemView.findViewById(R.id.textView);
            placeDate = itemView.findViewById(R.id.textView1);
            mainLayout = itemView.findViewById(R.id.edit);
        }
    }
}
