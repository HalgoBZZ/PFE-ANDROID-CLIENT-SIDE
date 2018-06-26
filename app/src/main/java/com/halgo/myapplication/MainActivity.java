package com.halgo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.net.HttpURLConnection;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private TextView pwdforget;
    private Button connexion;
    private EditText login;
    private EditText pwd;
    private int mStatusCode;
    private TextView warn;
    private String token="";

    private String loginUrl ="http://10.0.3.2:8080/login";
    //private String loginUrl ="http://10.0.2.2:8080/login";
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
        Intent intent=getIntent();
        token=intent.getStringExtra("token");
        boolean isLoggedIn = SharedPreference.getInstance(this).getBoolean(BaseConstant.LOGGED_IN, false);
        /*if(!token.isEmpty()){
            startActivity(new Intent(MainActivity.this,HelpActivity.class));
        }*/

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
                    warn.setText("*Login obligatoire!!");
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
                    warn.setText("*Login obligatoire!!");
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
                    warn.setText("*Mot de passe obligatoire!!");
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
                    warn.setText("*Mot de passe obligatoire!!");
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
                JSONObject jobj=new JSONObject();
                try {
                    jobj.put("cmpt_LOGIN", login.getText().toString());
                    jobj.put("cmpt_PWD",pwd.getText().toString());
                }catch (JSONException e){
                    e.printStackTrace();
                }

                JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.POST, loginUrl,
                        jobj,new Response.Listener<JSONObject>() {


                    @Override
                            public void onResponse(JSONObject response) {

                            Log.i(MainActivity.class.getName(), "response login :"+response);
                                    warn.setText(mStatusCode);
                                    try {
                                        String token = response.getString("token");
                                        warn.setText(token);
                                    }catch(JSONException e){
                                        e.printStackTrace();
                                    }
                                    intent.putExtra("login", login.getText().toString());
                                    //startActivity(intent);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i(MainActivity.class.getName(), "error login :"+error);
                                //warn.setText(error.getMessage());
                            }
                        }
                ){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        String credentials = login.getText().toString()+":"+pwd.getText().toString();
                        String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                        headers.put("Content-Type", "application/json");
                        headers.put("Authorization", auth);
                        return headers;
                    }

                    @Override
                    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                        Log.i(MainActivity.class.getName(), "parseNetworkResponse login :"+response.data);
                        try {
                            if (mStatusCode == HttpURLConnection.HTTP_OK){
                                token = response.headers.get("Authorization");
                                if (!TextUtils.isEmpty(token)){
                                    Log.i(MainActivity.class.getName(), "access token :"+token);

                                    navigateToMain(token);

                                }else {
                                 //   Toast.makeText(MainActivity.this,"empty token",Toast.LENGTH_LONG).show();
                                }
                            }else {
                             //   Toast.makeText(MainActivity.this,"Login failed!",Toast.LENGTH_LONG).show();
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        mStatusCode = response.statusCode;
                        //warn.setText(mStatusCode);
                        return super.parseNetworkResponse(response);
                    }
                    @Override
                    public String getBodyContentType() {
                        return "application/json";
                    }

                };
                //obreq.setTag(REQ_TAG);
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

    private void navigateToMain(String token) {
        SharedPreference.getInstance(this).put(BaseConstant.ACCESS_TOKEN, token);
        SharedPreference.getInstance(this).put(BaseConstant.LOGGED_IN, true);
        Intent intent = new Intent(MainActivity.this, HelpActivity.class);
        intent.putExtra("token",token);
        intent.putExtra("login",login.getText().toString());
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }


}
