package umn.ac.id.uas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class create2 extends AppCompatActivity {

    private TextView place,date;
    private EditText editText;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private ImageView addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create2);

        editText = findViewById(R.id.editText);
        addButton = findViewById(R.id.simpan);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("Notif", "Notif", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }



        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Notif
                NotificationCompat.Builder builder = new NotificationCompat.Builder(create2.this, "Notif");
                builder.setContentTitle("Tripq");
                builder.setContentText("Reminder untuk jadwal liburan berhasil dibuat");
                builder.setSmallIcon(R.drawable.logocek);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(create2.this);
                managerCompat.notify(1, builder.build());

                MyDatabaseHelper myDB = new MyDatabaseHelper(create2.this);
                myDB.addPlace(place.getText().toString(), date.getText().toString(),
                        dateButton.getText().toString(), editText.getText().toString());
                Intent intent = new Intent(create2.this, jadwal.class);
                startActivity(intent);
            }
        });

        initDatePicker();
        dateButton = findViewById(R.id.editText23);
        dateButton.setText(getTodaysDate());

        place = findViewById(R.id.textView5);
        date = findViewById(R.id.qwe);

        String userplace = getIntent().getStringExtra("keyplace");
        String userdate = getIntent().getStringExtra("keydate");

        date.setText(userdate);
        place.setText(userplace);
    }

    private String getTodaysDate() {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);


    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat (month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DES";


        return "JAN";
    }

    public void openDatePicker(View view) {

        datePickerDialog.show();
    }
}