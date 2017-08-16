package com.jhammond.greetingsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FirstNameGet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_name_get);
    }

    public void sendFirstName(View view) {
        Intent intent = new Intent(FirstNameGet.this, LastNameGet.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra("firstname", message);
        startActivity(intent);
    }
}