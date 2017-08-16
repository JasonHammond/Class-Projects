package com.jhammond.savetextapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class LoadButtonActivity extends AppCompatActivity {
    private final static String TEXTFILE="textfile.txt";
    private EditText textEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_load_button);
    textEdit=(EditText)findViewById(R.id.textbox);
    readInFile();
    textEdit.setSelection(textEdit.length());
    }

    public void saveClicked(View v) {
        try {
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput(TEXTFILE, 0));
            out.write(textEdit.getText().toString());
            out.close();
            makeText(this, "Text Saved!", LENGTH_SHORT).show();
        }
        catch(Throwable t) {
            makeText(this, "Exception: " + t.toString(), LENGTH_SHORT).show();
        }
    }

    public void loadClicked(View v) {
        try {
            //Start Activity
            Intent intent = new Intent(this, LoadButtonActivity.class);
            startActivity(intent);
        }
        catch(Throwable t) {
            makeText(this, "Exception: " + t.toString(), LENGTH_SHORT).show();
        }
    }

    public void readInFile() {
        try {
            InputStream in = openFileInput(TEXTFILE);
            if(in != null) {
                InputStreamReader istream = new InputStreamReader(in);
                BufferedReader read = new BufferedReader(istream);
                String str;
                StringBuilder buf = new StringBuilder();

                while( (str = read.readLine() ) != null) buf.append(str).append('\n' );

                in.close();
                textEdit.setText(buf.toString());
                makeText(this, "File loaded!", Toast.LENGTH_SHORT).show();
            }
        }
        catch(java.io.FileNotFoundException e) {
            makeText(this, e.toString(), LENGTH_SHORT).show();
        }
        catch(Throwable t) {
            makeText(this, "Exception: " + t.toString(), LENGTH_SHORT).show();
        }
    }
}
