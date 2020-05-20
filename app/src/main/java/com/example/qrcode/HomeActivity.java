package com.example.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    Date date = new Date();
    public String tgl = dateFormat.format(date);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void Scanner(View view){
        Intent scanne = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(scanne);
    }

    public void ShowData(View view){
        Intent showdata = new Intent(HomeActivity.this, ShowData.class);
        startActivity(showdata);
    }
}
