package umn.ac.id.uas;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    View v;
    TextView textView;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView= itemView.findViewById(R.id.cardView);
        textView = itemView.findViewById(R.id.nama);
        v=itemView;
    }
}
