package com.example.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.media.MediaPlayer;

import com.google.zxing.Result;
import android.app.AlertDialog;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    public ZXingScannerView mScannerView;
//    private MediaPlayer player;

    public ArrayList<String> textt = new ArrayList<String>();
    boolean checked = true;

//    ShowData sd = new ShowData();
    HomeActivity hm = new HomeActivity();
    public File file = new File("/storage/emulated/0/absensi-"+hm.tgl+".txt");;
    FileOutputStream fop = null;
    OutputStreamWriter writer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        try{
//            if (player.isPlaying()) {
//                player.stop();
//                player.release();
//            }
        }catch(Exception e){
//            Toast.makeText(this, "Masuk Exception", Toast.LENGTH_SHORT).show();
        }
        Log.v("TAG", result.getText()); // Prints scan results
        Log.v("TAG", result.getBarcodeFormat().toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setMessage(result.getText());
        AlertDialog alert1 = builder.create();
        alert1.show();
//        player = MediaPlayer.create(this, R.raw.sukses);
//        player.setLooping(false); // Set looping
//        player.start();
//        ShowData.data.add(result.getText());
        try{
            fop = new FileOutputStream(file, true);
            if (!file.exists()){
                file.createNewFile();
            }
            writer = new OutputStreamWriter(fop);
            ambilData();
            if(textt.isEmpty()){
                writer.write(result.getText()+"\n");
                Toast.makeText(this, "Masukk", Toast.LENGTH_SHORT).show();
            }else {
                writer.write(result.getText()+"\n");
            }
//            Toast.makeText(this, textt.size()+"--"+textt.size(), Toast.LENGTH_SHORT).show();
//            for (int i = 0; i <= textt.size() ; i++){
//                if(textt.get(i)==result.getText()){
////                    checked = true;
//                    Toast.makeText(this, "Makassar ", Toast.LENGTH_SHORT).show();
//                }
//
//            }
            writer.close();
            fop.close();
        }catch (IOException ex){
            System.out.println("Gagal Menyimpan");
        }catch (IndexOutOfBoundsException ex){
            System.out.println("Data Terlalu Banyak");
        }

        try{
            Thread.sleep(1000);
        }catch (InterruptedException ex){
            Toast.makeText(this, ""+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

        mScannerView.resumeCameraPreview(this);
    }

    public void ambilData(){
        try(BufferedReader br = new BufferedReader(new FileReader(file))){

            //Untuk mengambil baris data yang ada pada File
            String barisData;

            //Menampilkan semua baris data didalam file contohFile.txt
            while((barisData = br.readLine())!= null){
                System.out.println(barisData);
                textt.add(barisData);
            }
        }catch(FileNotFoundException ex1){

            //Menangani kesalahan jika file tersebut tidak ditemukan
            System.out.println("File tidak ditemukan "+ file.toString());

        }catch(Exception ex2){
            //Menangani kesalahan jika file tersebut tidak dapat dibaca
            System.out.println("File tidak dapat dibaca "+ file.toString());

        }
    }
}
