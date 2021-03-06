package com.jhammond.greetingsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

public class Greeting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);

        Intent intent = getIntent();
        Bundle extrasFinal = intent.getExtras();
        String nameToDisplay = extrasFinal.getString("fullname");
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText("Hello, " + nameToDisplay + "!\n\nNice to meet you!");

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_greeting);
        layout.addView(textView);
    }
}
