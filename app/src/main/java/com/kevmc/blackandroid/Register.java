package com.kevmc.blackandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    //define edit text and string variables
    EditText name, surname, age, username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //cast edit text data
        name = (EditText)findViewById(R.id.et_name);
        surname = (EditText)findViewById(R.id.et_surname);
        age = (EditText)findViewById(R.id.et_age);
        username = (EditText)findViewById(R.id.et_username);
        password = (EditText)findViewById(R.id.et_password);
    }

    public void onRegister(View view){

        String str_name = name.getText().toString();
        String str_surname = surname.getText().toString();
        String str_age = age.getText().toString();
        String str_username = username.getText().toString();
        String str_password = password.getText().toString();

        String type = "register";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);

        backgroundWorker.execute(type, str_name, str_surname, str_age, str_username, str_password);

    }
}
