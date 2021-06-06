package umn.ac.id.uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class start extends AppCompatActivity {
    private ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        button = findViewById(R.id.lanjut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanjut();
            }
        });
    }

    public void lanjut() {
        Intent intent = new Intent(this, start2.class);
        startActivity(intent);
    }
}