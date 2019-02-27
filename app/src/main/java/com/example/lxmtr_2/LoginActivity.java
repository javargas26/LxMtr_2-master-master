package com.example.lxmtr_2;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final String URL_FOR_LOGIN = "https://streetlightdata.000webhostapp.com/project/android/login.php";
    ProgressDialog progressDialog;
    private EditText loginInputUser, loginInputPassword;
    private Button btnlogin;
    String user_prueba, password_prueba;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginInputUser = (EditText) findViewById(R.id.login_input_user);
        loginInputPassword = (EditText) findViewById(R.id.login_input_password);
        btnlogin = (Button) findViewById(R.id.btn_login);

        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate())
                {
                    loginUser(loginInputUser.getText().toString(),
                            loginInputPassword.getText().toString());
                }else {

                    Toast.makeText(LoginActivity.this, "Llene todos los campos antes de continuar", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private boolean validate() {


        user_prueba=loginInputUser.getText().toString();
        password_prueba=loginInputPassword.getText().toString();
        if (user_prueba.equals(""))
        {
            return false;
        }
        if (password_prueba.equals(""))
        {
            return false;
        }
        else{
            return true;
        }

    }

    private void loginUser( final String user, final String password) {
        // Tag used to cancel the request
        String cancel_req_tag = "login";
        progressDialog.setMessage("Accediendo...");
        showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_FOR_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Register Response: " + response.toString());
                hideDialog();
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                   /* if (error)
                    {
                        Toast.makeText(getBaseContext(), "Conectando...", Toast.LENGTH_SHORT).show();
                    }*/

                    if (!error) {
                        String id = jObj.getJSONObject("user").getString("id");
                        String nombre = jObj.getJSONObject("user").getString("Nombre");
                        String apellido = jObj.getJSONObject("user").getString("Apellido");
                        Log.e(TAG, nombre+apellido);
                        // Launch User activity
                        Intent intent = new Intent(
                                LoginActivity.this,
                                MainActivity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("nombre", nombre);
                        intent.putExtra("apellido", apellido);


                        startActivity(intent);
                        finish();
                    } else {

                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("user", user);
                params.put("password", password);
                return params;
            }
        };
        // Adding request to request queue
        AppSigleton.getInstance(getApplicationContext()).addToRequestQueue(strReq,cancel_req_tag);
    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }
    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

}
