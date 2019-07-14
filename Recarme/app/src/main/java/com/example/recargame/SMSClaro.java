package com.example.recargame;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SMSClaro extends AppCompatActivity {
Spinner tipo;
EditText txtnumero,txtvalor;
Button enviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsclaro);
        tipo=findViewById(R.id.spinner);
        txtnumero=findViewById(R.id.txtnumeroC);
        txtvalor=findViewById(R.id.txtvalorC);
        enviar=findViewById(R.id.btnenviarc);

        String[] opciones={"Recarga Normal","Recarga Limitada"};
        ArrayAdapter<String> adaptadorTipo = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,opciones);
        tipo.setAdapter(adaptadorTipo);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selecion=tipo.getSelectedItem().toString();
                String clave="";
                if (selecion.equals("Recarga Normal")){
                    clave="Recp";
                }else if (selecion.equals("Recarga Limitada")){
                    clave="Recpl";
                }
                String numero="3355";
                String mensaje = clave+" 0724 "+txtnumero.getText().toString()+" "+txtvalor.getText().toString();


                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(numero,null,mensaje,null,null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(SMSClaro.this);
                    builder.setMessage("Recarga Enviada Correctamente")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    finish();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }catch (Exception e){
                    AlertDialog.Builder builder = new AlertDialog.Builder(SMSClaro.this);
                    builder.setMessage("Fallo Recarga")
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

            }
        });
    }
}
