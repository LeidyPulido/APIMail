package com.example.leidy.enviodecorreo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EmailMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_main);
    }

    public void onClick(View v){
        enviar();
    }

    public void enviar(){
        String[] aQuien = {"luchofelipe20002000@gmail.com"};
        String [] cc = {"llpulidom@gmail.com"};
        String asunto ="Asunto probando";
        String mensajillo = "Este mensaje es secreto";
        String textos = "Este es el contenido";
        Intent miIntent = new Intent(Intent.ACTION_SEND);
        miIntent.setData(Uri.parse("mailto:"));
        miIntent.putExtra(Intent.EXTRA_EMAIL,aQuien);
        miIntent.putExtra(Intent.EXTRA_CC,cc);
        miIntent.putExtra(Intent.EXTRA_SUBJECT,mensajillo);
        miIntent.putExtra(Intent.EXTRA_TEXT,textos);
        miIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(miIntent,"Email "));

    }
}
