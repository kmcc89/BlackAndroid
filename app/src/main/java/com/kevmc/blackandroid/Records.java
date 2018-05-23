package com.kevmc.blackandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Records extends AppCompatActivity {

    EditText user_to_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        user_to_search = (EditText)findViewById(R.id.et_search_user);
    }

    public void viewRecord(View view){

        String str_user_to_search = user_to_search.getText().toString();

        String type = "record";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);

        backgroundWorker.execute(type, str_user_to_search);

    }
}
