package umn.ac.id.uas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class jadwal extends AppCompatActivity {

    RecyclerView recyclerView;
    MyDatabaseHelper myDB;
    ArrayList<String> place_id, place_name, place_date, place_reminder, place_catatan;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        recyclerView = findViewById(R.id.recycler);

        myDB = new MyDatabaseHelper(jadwal.this);
        place_id = new ArrayList<>();
        place_name = new ArrayList<>();
        place_date = new ArrayList<>();
        place_reminder = new ArrayList<>();
        place_catatan = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(jadwal.this, this, place_id, place_name, place_date, place_reminder, place_catatan);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(jadwal.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                place_id.add(cursor.getString(0));
                place_name.add(cursor.getString(1));
                place_date.add(cursor.getString(2));
                place_reminder.add(cursor.getString(3));
                place_catatan.add(cursor.getString(4));
            }
        }
    }
}