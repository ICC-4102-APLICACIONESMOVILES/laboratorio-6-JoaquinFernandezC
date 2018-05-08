package com.example.joaco.labsmobiles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEdit;
    private EditText passwordEdit;
    SavePreferences savePreferences;

    NetworkManager networkManager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEdit = (EditText) findViewById(R.id.email);
        passwordEdit = (EditText) findViewById(R.id.password);

        savePreferences = SavePreferences.getInstance(this);

        networkManager = NetworkManager.getInstance(this);

        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                emailEdit.setError(null);
                passwordEdit.setError(null);

                String email = emailEdit.getText().toString();
                String password = passwordEdit.getText().toString();

                try {
                    networkManager.login(email, password, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            getForms();
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            System.out.println(error);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                boolean cancel = false;
                View focusView = null;

                if (TextUtils.isEmpty(password)){
                    passwordEdit.setError("Required Field");
                    cancel = true;
                    focusView = passwordEdit;
                }
                if (TextUtils.isEmpty(email)){
                    emailEdit.setError("Required Field");
                    cancel = true;
                    focusView = emailEdit;
                }
                if (!email.contains("@")){
                    emailEdit.setError("Invalid email");
                    cancel = true;
                    focusView = emailEdit;
                }
                if (password.length() < 6 && !TextUtils.isEmpty(password)){
                    passwordEdit.setError("Password too short");
                    cancel = true;
                    focusView = passwordEdit;
                }
                if (cancel){
                    focusView.requestFocus();
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Credentials correct", Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent();
                    intent.putExtra("EMAIL", email);
                    intent.putExtra("PASSWORD", password);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
    private void getForms(){
        networkManager.getForms(new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                System.out.println(error);
            }
        });
    }
}
