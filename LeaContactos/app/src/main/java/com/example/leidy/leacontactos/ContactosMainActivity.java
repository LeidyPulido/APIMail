package com.example.leidy.leacontactos;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ContactosMainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CHOOSE_PHONE = 1;
    private TextView vPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos_main);

        vPhone = (TextView) findViewById(R.id.TextView01);
        findViewById(R.id.Button01).setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == REQUEST_CHOOSE_PHONE)
                && (resultCode == Activity.RESULT_OK)) {
            try {
                String phone = data.getStringExtra("phone");
                String phone2 = data.getStringExtra("phone2");
                System.out.println("**********************************************************************************");
                System.out.println("phone"+phone);
                System.out.println("phone2"+phone2);

                vPhone.setText(phone+phone2);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onClick(View v) {
        // Intent intent = new Intent("org.francho.CHOOSE_PHONE");
        //
        // Intent intent = new Intent("android.intent.action.MAIN");
        Intent intent = new Intent("org.leidy.CHOOSE_PHONE");

        startActivityForResult(intent, REQUEST_CHOOSE_PHONE);
    }
}
