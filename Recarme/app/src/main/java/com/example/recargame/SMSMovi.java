package com.example.recargame;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SMSMovi extends AppCompatActivity {
Button btnenviar;
EditText txtvalor,txtnumero;
TextView lblTitulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsmovi);
        btnenviar=findViewById(R.id.btnenviarM);
        txtnumero=findViewById(R.id.txtnumeroM);
        txtvalor=findViewById(R.id.txtvalorM);
        lblTitulo=findViewById(R.id.tvTitulo);
        String Titulo = getIntent().getStringExtra("titulo");
        final String clave = getIntent().getStringExtra("clave");
        Titulo="Recagas de "+ Titulo;
        lblTitulo.setText(Titulo);
        btnenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero="3355";
                String mensaje = clave+" 0724 "+txtnumero.getText().toString()+" "+txtvalor.getText().toString();
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(numero,null,mensaje,null,null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(SMSMovi.this);
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(SMSMovi.this);
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
    void BorrarDatos(){
        txtvalor.setText("");
        txtnumero.setText("");
    }
}
