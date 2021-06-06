package umn.ac.id.uas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class rekomendasi extends AppCompatActivity {

    private static final int REQUEST_CODE_IMAGE = 101;
    private ImageView imageViewAdd;
    private EditText Name;
    private EditText Desc;
    private EditText Latitude;
    private EditText Longtitude;
    private Button btnUpload;

    Uri imageUri;
    boolean isImageAdded = false;

    DatabaseReference ref;
    StorageReference StoreRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekomendasi);

        imageViewAdd = findViewById(R.id.imageViewAdd);
        Name = findViewById(R.id.inputName);
        Desc = findViewById(R.id.Deskripsi);
        Latitude = findViewById(R.id.Latitude);
        Longtitude = findViewById(R.id.Longtitude);
        btnUpload = findViewById(R.id.btnUpload);

        ref = FirebaseDatabase.getInstance().getReference().child("Place");
        StoreRef = FirebaseStorage.getInstance().getReference().child("PlaceImg");

        imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Name1 = Name.getText().toString();
                final String Desc1 = Desc.getText().toString();
                final String Latitude1 = Latitude.getText().toString();
                final String Longtitude1 = Longtitude.getText().toString();
                if(isImageAdded != false && Name1 != null && Desc1 != null && Latitude1 != null && Longtitude1 != null){
                    uploadImage(Name1, Desc1, Latitude1, Longtitude1);
                }
            }
        });
    }

    private void uploadImage(final String name1, final String desc1, final String latitude1, final String longtitude1) {
        final String key = ref.push().getKey();
        StoreRef.child(key+".jpg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                StoreRef.child(key+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("PlaceName", name1);
                        hashMap.put("Desc", desc1);
                        hashMap.put("Latitude", latitude1);
                        hashMap.put("Longtitude", longtitude1);
                        hashMap.put("ImageUrl", uri.toString());

                        ref.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(rekomendasi.this, "Data Upload", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_IMAGE && data != null){
            imageUri = data.getData();
            isImageAdded = true;
            imageViewAdd.setImageURI(imageUri);
        }
    }
}