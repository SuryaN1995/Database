package com.example.android.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener{

    Button b;
    TextView name,pass;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.editText);
        pass = findViewById(R.id.editText2);

        b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty()||pass.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter the required fields",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (savedInstanceState == null) {
                        name.setText("");
                        pass.setText("");
                        FragmentManager fm = getSupportFragmentManager();
                        fm.beginTransaction().replace(R.id.fragment, new BlankFragment()).setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).addToBackStack(null).commit();
                } else {
                        Log.d("mytag", "Error");
                }

            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        System.out.println("fragment callback");
    }
}