package com.example.android.aswitch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    SharedPreferences SM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);

        SM = getSharedPreferences("userrecord", 0);
        Boolean islogin = SM.getBoolean("userlogin", false);
        if(islogin){
            Intent intent = new Intent(MainActivity.this, Dashboard.class);
            startActivity(intent);
            finish();
            return;
        }

        Button login = (Button) findViewById(R.id.login);
        final EditText ETuser = (EditText) findViewById(R.id.editText1);
        final EditText ETpass = (EditText) findViewById(R.id.editText2);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String user = ETuser.getText().toString();
                String pass = ETpass.getText().toString();
                if(user.equalsIgnoreCase("admin") && pass.equalsIgnoreCase("admin")){
                    SharedPreferences.Editor edit = SM.edit();
                    edit.putBoolean("userlogin", true);
                    edit.commit();

                    Intent intent = new Intent(MainActivity.this, Dashboard.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Username/Password Invalid", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
