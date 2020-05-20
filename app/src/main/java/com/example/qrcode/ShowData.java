package com.example.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class ShowData extends AppCompatActivity {

    //ArrayList digunakan Untuk menampung Data mahasiswa
//    public static ArrayList<String> data = new ArrayList<>();;

//    HomeActivity hm = new HomeActivity();
    ListView listView;
    MainActivity mn = new MainActivity();

//    public ArrayList<String> textt = new ArrayList<String>();
//    File file = new File("/storage/emulated/0/absensi-"+hm.tgl+".txt");
//    BufferedReader in = new BufferedReader(new FileReader("absensi-"+hm.tgl+".txt"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        listView = findViewById(R.id.list);

        tampilData();

    }

    public void tampilData() {
        try(BufferedReader br = new BufferedReader(new FileReader(mn.file))){

            //Untuk mengambil baris data yang ada pada File
            String barisData;

            //Menampilkan semua baris data didalam file contohFile.txt
            while((barisData = br.readLine())!= null){
                System.out.println(barisData);
                mn.textt.add(barisData);
            }

            ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, mn.textt);
            listView.setAdapter(adapter);

            //Digunakan untuk menangani kesalahan jika terjadi error
        }catch(FileNotFoundException ex1){

            //Menangani kesalahan jika file tersebut tidak ditemukan
            System.out.println("File tidak ditemukan "+ mn.file.toString());

        }catch(Exception ex2){
            //Menangani kesalahan jika file tersebut tidak dapat dibaca
            System.out.println("File tidak dapat dibaca "+ mn.file.toString());

        }
    }
}
