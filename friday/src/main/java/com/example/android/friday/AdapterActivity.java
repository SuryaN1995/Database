package com.example.android.friday;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by techjini on 22/1/18.
 */

public class AdapterActivity extends AppCompatActivity{

    SimpleCursorAdapter mAdapter;
    ListView mListView;
    myDbAdapter helper;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.value_layout);
        mListView = findViewById(R.id.list);
        helper = new myDbAdapter(this);
        mAdapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.listview_item_layout,
                null,
                new String[] { helper.getData()},
                new int[] {R.id.name , R.id.age, R.id.std,R.id.gen }, 0);
        mListView.setAdapter(mAdapter);
    }

}
