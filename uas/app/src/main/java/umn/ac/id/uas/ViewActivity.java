package umn.ac.id.uas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ViewActivity extends AppCompatActivity {

    private ImageView imageview;
    TextView textView;
    TextView desc;
    ImageView maps;
    ImageView add;

    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view2);

        add= findViewById(R.id.addButton);
        maps= findViewById(R.id.mapsButton);
        imageview=findViewById(R.id.cardView);
        textView=findViewById(R.id.textView);
        desc=findViewById(R.id.dekripsiCanggu);
        ref= FirebaseDatabase.getInstance().getReference().child("Place");



        String Place=getIntent().getStringExtra("Place");

        ref.child(Place).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                {
                    String  PlaceName = dataSnapshot.child("PlaceName").getValue().toString();
                    String  ImageUrl = dataSnapshot.child("ImageUrl").getValue().toString();
                    String  Desc = dataSnapshot.child("Desc").getValue().toString();
                    String  Lat = dataSnapshot.child("Latitude").getValue().toString();
                    String  Longt = dataSnapshot.child("Longtitude").getValue().toString();


                    Picasso.get().load(ImageUrl).into(imageview);
                    textView.setText(PlaceName);
                    desc.setText(Desc);

                    maps.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String uri = "http://maps.google.com/maps?q=loc:" + Lat + "," + Longt;
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                            intent.setPackage("com.google.android.apps.maps");
                            startActivity(intent);
                        }
                    });

                    add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                           open();
                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }

    private void open() {
        Intent intent = new Intent(this, create.class);
        startActivity(intent);
    }


}