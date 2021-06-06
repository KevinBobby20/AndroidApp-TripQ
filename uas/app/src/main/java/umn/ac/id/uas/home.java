package umn.ac.id.uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class home extends AppCompatActivity {
    private ImageButton createbtn;
    private ImageButton rekomendasibtn;
    private ImageButton searchbtn;
    private ImageButton jadwalbtn;
    private ImageView canggubtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        createbtn = findViewById(R.id.create);
        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreate();
            }
        });
        canggubtn = findViewById(R.id.plusbtn);
        canggubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCanggu();
            }
        });
        rekomendasibtn = findViewById(R.id.rekomendasi);
        rekomendasibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRekomendasi();
            }
        });
        searchbtn = findViewById(R.id.search);
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearch();
            }
        });
        jadwalbtn = findViewById(R.id.jadwal);
        jadwalbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openJadwal();
            }
        });
    }

    public void openJadwal() {
        Intent intent = new Intent(this, jadwal.class);
        startActivity(intent);
    }

    public void openCanggu() {
        Intent intent = new Intent(this, canggu.class);
        startActivity(intent);
    }


    public void openSearch() {
        Intent intent = new Intent(this, search.class);
        startActivity(intent);
    }

    public void openRekomendasi() {
        Intent intent = new Intent(this, rekomendasi1.class);
        startActivity(intent);
    }

    public void openCreate() {
        Intent intent = new Intent(this, create.class);
        startActivity(intent);
    }
}