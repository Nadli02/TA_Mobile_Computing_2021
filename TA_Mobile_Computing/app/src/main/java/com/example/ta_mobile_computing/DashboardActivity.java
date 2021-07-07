package com.example.ta_mobile_computing;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {
    String Username;
    TextView user;
    Button daftar, about1;
    private static final int PERMISSION_CODE = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        user = (TextView)findViewById(R.id.textEmail);
        daftar = (Button)findViewById(R.id.buttonLogOut);
        about1 = (Button)findViewById(R.id.about);
        Intent intent = getIntent();
        // Receiving User Email Send By MainActivity.
        Username = intent.getStringExtra(MainActivity.Username);
        // Setting up received email to TextView.
        user.setText(user.getText().toString()+ Username);
        // Adding click listener to Log Out button.
        about1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.ACTIVITY_RECOGNITION) ==
                            PackageManager.PERMISSION_DENIED) {
                        String[] premission = {Manifest.permission.ACTIVITY_RECOGNITION};

                        requestPermissions(premission, PERMISSION_CODE);
                    }
                    else {
                        Intent intent = new Intent(DashboardActivity.this, SecondActivity.class);
                        startActivity(intent);
                    }
                }
                else {
                    Intent intent = new Intent(DashboardActivity.this, SecondActivity.class);
                    startActivity(intent);
                }

            }
            public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                switch (requestCode) {
                    case PERMISSION_CODE: {
                        if(grantResults.length > 0 && grantResults[0]==
                                PackageManager.PERMISSION_GRANTED) {
                            Intent intent = new Intent(DashboardActivity.this, SecondActivity.class);
                            startActivity(intent);
                        }
                        else {
                            AlertDialog.Builder builder = new
                                    AlertDialog.Builder(DashboardActivity.this);
                            builder.setMessage("Please Allow To Continue").setNegativeButton("OK", null).create().show();
                        }
                    }
                }
            }
        });
    }
}