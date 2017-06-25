package com.example.kamesh.foodyfu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.accounts.AccountManager.KEY_PASSWORD;


public class Login extends AppCompatActivity {
    private static final String REGISTER_URL = "http://192.168.0.101:5000/login";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    Button submit;
    EditText password,user_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        submit=(Button)findViewById(R.id.submit);
        password=(EditText)findViewById(R.id.password);
        user_name=(EditText)findViewById(R.id.user_name);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }
    private void registerUser() {
        final String username = user_name.getText().toString();
        final String password1 = password.getText().toString();
        Map<String, String> params = new HashMap<String, String>();
        params.put(KEY_USERNAME, username);
        params.put(KEY_PASSWORD, password1);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST, REGISTER_URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("dfs","sdfs");
                        //Toast.makeText(Login.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("fs","sfs");
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                // something to do here ??
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_USERNAME, username);
                params.put(KEY_PASSWORD, password1);
                return params;
            }

        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
        //requestQueue.add(getRequest);

    }
}


