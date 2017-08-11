package com.hfad.loginsession;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements View.OnClickListener{

    private EditText txtEmail , txtPwd;
    private AppCompatButton btnLogin;

    private boolean loggedin = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = (EditText) findViewById(R.id.editTextEmail);
        txtPwd = (EditText) findViewById(R.id.editTextPassword);

        btnLogin = (AppCompatButton) findViewById(R.id.buttonLogin);

        btnLogin.setOnClickListener(this);
    }

    protected void onResume(){
        super.onResume();

        SharedPreferences sharedPreerences = getSharedPreferences("session",Context.MODE_PRIVATE);

        loggedin = sharedPreerences.getBoolean("loggedin",false);

        if(loggedin){
            Intent intent = new Intent(this,ProfileActivity.class);
            startActivity(intent);
        }
    }

    public void login(){
        final String email = txtEmail.getText().toString().trim();
        final String pwd = txtPwd.getText().toString().trim();

        if((email.equals("Devang"))&&(pwd.equals("Bhatt"))){
            SharedPreferences sharedPreferences = getSharedPreferences("session", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("user","Devang");
            editor.putBoolean("loggedin",true);

            editor.commit();

            Intent intent = new Intent(this,ProfileActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Invalid Username , Password", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View v){
        login();
    }
}
