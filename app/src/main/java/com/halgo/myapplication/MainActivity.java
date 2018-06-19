package com.halgo.myapplication;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private TextView pwdforget;
    private Button connexion;
    private EditText login;
    private EditText pwd;
    private TextView warn;
    private String loginUrl ="http://10.0.3.2:8080/login/";
    private RequestQueue requestQueue;


    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // check Fields For Empty Values
            checkFieldsForEmptyValues();
        }
    };

    void checkFieldsForEmptyValues(){
        Button b = (Button) findViewById(R.id.connexion);

        String s1 = login.getText().toString();
        String s2 = pwd.getText().toString();

        if(s1.equals("")|| s2.equals("")){
            b.setEnabled(false);
        } else {
            b.setEnabled(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        warn=(TextView)findViewById(R.id.warn);
        login=(EditText)findViewById(R.id.login);
        pwd=(EditText)findViewById(R.id.password);
        connexion=(Button)findViewById(R.id.connexion);
        pwdforget=(TextView)findViewById(R.id.mdp_oublie);

        requestQueue = Volley.newRequestQueue(this);

        login.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(login.getText().toString().isEmpty()){
                    warn.setText("*Tapez votre login svp!!");
                }else {
                    warn.setText("");
                }
            }
        });

        login.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after){
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                if(login.getText().toString().isEmpty()){
                    warn.setText("*Tapez votre login svp!!");
                }else{
                    warn.setText("");
                }
            }
            @Override
            public void afterTextChanged(Editable s){

            }
        });

        pwd.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(pwd.getText().toString().isEmpty()){
                    warn.setText("*Tapez votre mot de passe svp!!");
                }else{
                    warn.setText("");
                }
            }
        });

        pwd.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after){
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                if(pwd.getText().toString().isEmpty()){
                    warn.setText("*Tapez votre mot de passe svp!!");
                }else{
                    warn.setText("");
                }
            }
            @Override
            public void afterTextChanged(Editable s){

            }
        });
        // set listeners
        login.addTextChangedListener(mTextWatcher);
        pwd.addTextChangedListener(mTextWatcher);

        // run once to disable if empty
        checkFieldsForEmptyValues();



        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                final User user=new User(login.getText().toString(),pwd.getText().toString());
                String JsonURL=loginUrl;
                StringRequest obreq = new StringRequest(Request.Method.POST, JsonURL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    warn.setText(response.toString());
                                    //intent.putExtra("login",login.getText().toString());
                                    //startActivity(intent);

                                }catch ( NumberFormatException e) {
                                    warn.setText("verfier vos données!!");
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                warn.setText(error.getMessage());
                            }
                        }
                ) {
                    @Override
                    protected HashMap<String, String> getParams()
                    {
                        HashMap<String, String>  params = new HashMap<String, String>();
                        params.put("cmpt_LOGIN", login.getText().toString());
                        params.put("cmpt_PWD", pwd.getText().toString());
                        return params;
                    }
                };
                requestQueue.add(obreq);

            }
            });

        pwdforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ForgetPassAtivity.class));
            }
        });
    }



}
