package com.halgo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SendCodeActivity extends AppCompatActivity {

    private EditText code;
    private Button send ;
    private Button back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendcode);
        send=(Button)findViewById(R.id.sendcode);
        back=(Button)findViewById(R.id.backtosend);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SendCodeActivity.this, MainActivity.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SendCodeActivity.this, ForgetPassAtivity.class));
            }
        });
    }
}
