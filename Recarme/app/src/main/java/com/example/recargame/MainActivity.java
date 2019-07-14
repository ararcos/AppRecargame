package com.example.recargame;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
ImageButton btnMovi,btnClaro,btnTuenti,btnCnt,btnDtv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnMovi=findViewById(R.id.btn_movi);
        btnClaro=findViewById(R.id.btn_claro);
        btnTuenti=findViewById(R.id.btn_tuenti);
        btnCnt=findViewById(R.id.btn_cnt);
        btnDtv=findViewById(R.id.btn_dtv);

        btnMovi.setOnClickListener(this);
        btnClaro.setOnClickListener(this);
        btnCnt.setOnClickListener(this);
        btnDtv.setOnClickListener(this);
        btnTuenti.setOnClickListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.SEND_SMS)){
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS},1);
            }else{
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS},1);
            }
        }else{
            //do nothing
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.SEND_SMS)==PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this,"Permisos Concedidos",Toast.LENGTH_LONG).show();

                    }
                }else{
                    Toast.makeText(this,"Permisos NO Concedidos",Toast.LENGTH_LONG).show();
                }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_claro:

                 intent= new Intent (this, SMSClaro.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.btn_cnt:
                intent = new Intent (this, SMSMovi.class);
                intent.putExtra("titulo", "CNT");
                intent.putExtra("clave", "Cnt");
                startActivityForResult(intent, 0);
                break;
            case R.id.btn_dtv:
                intent = new Intent (this, SMSMovi.class);
                intent.putExtra("titulo", "DirecTV");
                intent.putExtra("clave", "Dtv");
                startActivityForResult(intent, 0);
                break;
            case R.id.btn_movi:
                intent = new Intent (this, SMSMovi.class);
                intent.putExtra("titulo", "Movistar");
                intent.putExtra("clave", "Rec");
                startActivityForResult(intent, 0);
                break;
            case R.id.btn_tuenti:
                intent = new Intent (this, SMSMovi.class);
                intent.putExtra("titulo", "Tuenti");
                intent.putExtra("clave", "Tue");
                startActivityForResult(intent, 0);
                break;
            case R.id.fab:
                String numero="3355";
                String mensaje = "Saldo";
                Toast.makeText(MainActivity.this,mensaje,Toast.LENGTH_LONG).show();

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(numero,null,mensaje,null,null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Consulta de Saldo Correcta")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }catch (Exception e){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Fallo Consulta Saldo")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                break;
        }
    }
}
