package com.example.stud.measureapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;

public class AdaugareMasuratoareActivity extends AppCompatActivity {

    Button btnStart;
    Button btnStop;
    EditText denumireMasuratoare;
    TextInputLayout textInputLayout;
    Masuratoare masuratoare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaugare_masuratoare);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        denumireMasuratoare = (EditText) findViewById(R.id.denumireMasuratoare);
        textInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(denumireMasuratoare.getText().toString().isEmpty()) {
                    textInputLayout.setError("Nu poate fi goala denumirea!");
                } else {
                    masuratoare = new Masuratoare();
                    masuratoare.setDenumire(denumireMasuratoare.getText().toString());
                    masuratoare.setData(new Date());

                    Intent intent = new Intent(AdaugareMasuratoareActivity.this, ServiceLocatie.class);
                    startService(intent);

                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentService = new Intent(AdaugareMasuratoareActivity.this, ServiceLocatie.class);
                stopService(intentService);

                BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        Bundle bundle = intent.getBundleExtra("bundle");
                        ArrayList<Punct> listaPuncte = (ArrayList<Punct>) bundle.getSerializable("listaPuncte");
                        for(Punct punct: listaPuncte) {
                            Log.i("STOP", punct.toString());
                        }
                        masuratoare.setListaPuncte(listaPuncte);
                    }
                };
                Intent intent =  new Intent();
                intent.putExtra("masuratoare", masuratoare);
                setResult(RESULT_OK, intent);

                finish();
            }
        });
    }
}
