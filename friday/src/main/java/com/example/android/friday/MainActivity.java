package com.example.android.friday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Button b_send,b_clear;
    Spinner s_class;
    EditText t_name,t_age;
    String[] section={
      "8th STD","9th STD","10th STD"
    };
    SimpleCursorAdapter mAdapter;
    ListView mListView;
    myDbAdapter helper;
    private int sid;
    RadioGroup rg;
    RadioButton men,femen;
    AdapterActivity ada;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b_send = findViewById(R.id.button_submit);
        b_clear = findViewById(R.id.button_clear);
        s_class = findViewById(R.id.data_class);
        t_name = findViewById(R.id.text_name);
        t_age = findViewById(R.id.text_age);
        rg = findViewById(R.id.radio_grp);
        men=findViewById(R.id.radio_male);
        femen=findViewById(R.id.radio_female);

        helper = new myDbAdapter(this);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,section);
        s_class.setAdapter(adapter);

        s_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                sid = s_class.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        b_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    addUser(v);
            }
        });
        b_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t_name.setText("");
                t_age.setText("");
                s_class.setSelection(0);
                rg.clearCheck();
            }
        });
    }
    public void addUser(View view)
    {
        int rid=0;
        String name = t_name.getText().toString();
        String age = t_age.getText().toString();
        String sec = s_class.getSelectedItem().toString();
        rid = rg.getCheckedRadioButtonId();
        View rb = rg.findViewById(rid);
        int index= rg.indexOfChild(rb);
        RadioButton btn = (RadioButton) rg.getChildAt(index);
        if(name.isEmpty() || age.isEmpty()|| sec.isEmpty())
        {
            Message.message(getApplicationContext(),"Enter Both Name and Password");
        }
        else
        {
            String gen = (String)btn.getText();
            long id = helper.insertData(name,rid,gen,sec);
            if(id<=0)
            {
                Message.message(getApplicationContext(),"Insertion Unsuccessful");
                t_name.setText("");
                t_age.setText("");
                s_class.setSelection(0);
                rg.clearCheck();
            } else
            {
                Message.message(getApplicationContext(),"Insertion Successful");
                t_name.setText("");
                t_age.setText("");
                s_class.setSelection(0);
                rg.clearCheck();
            }
        }
    }




    public void modify_data(View view) {
        Intent intent = new Intent(this, ModifyActivity.class);
        startActivity(intent);
    }

    public void delete_data(View view) {
        Intent intent = new Intent(this, DeleteActivity.class);
        startActivity(intent);
    }

    public void dis(View view) {
//        mListView = findViewById(R.id.list);
//        helper = new myDbAdapter(this);
//        mAdapter = new SimpleCursorAdapter(this,
//                R.layout.listview_item_layout,
//                null,
//                new String[] { helper.getData()},
//                new int[] {R.id.name , R.id.age, R.id.std,R.id.gen }, 0);
//        mListView.setAdapter(mAdapter);
        String str = helper.getData();
        Message.message(this,str);
    }
}

