package com.jhammond.greetingsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LastNameGet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_name_get);
    }

    public void sendFullName(View view) {
        Bundle extras = getIntent().getExtras();
        Intent intent2 = new Intent(LastNameGet.this, Greeting.class);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        String message2 = extras.getString("firstname") + " " + editText2.getText().toString();
        intent2.putExtra("fullname", message2);
        startActivity(intent2);
    }
}
