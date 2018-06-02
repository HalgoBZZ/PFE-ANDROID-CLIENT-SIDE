package com.halgo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ForgetPassAtivity extends AppCompatActivity {

    private Button send;
    private Button back;
    private EditText email;
    private EditText login;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpass);

        send=(Button)findViewById(R.id.send);
        back=(Button)findViewById(R.id.backtologin);
        email=(EditText) findViewById(R.id.emailsender);
        login=(EditText) findViewById(R.id.loginsender);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetPassAtivity.this, SendCodeActivity.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetPassAtivity.this, MainActivity.class));
            }
        });
    }
}
