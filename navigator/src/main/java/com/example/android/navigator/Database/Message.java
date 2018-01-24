package com.example.android.navigator.Database;

import android.content.Context;
import android.widget.Toast;

/**
 * Its used to display the toast message in the database i.e in HomeFragment
 */
public class Message {

    public static void message(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
