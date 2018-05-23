package com.kevmc.blackandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    /*
    Define edit text variables
     */

    EditText usernameEt, passwordEt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Set the value of the edit texts by getting the value from the xml layout page
        using findViewById and R.id. and the name of the edit text field in the xml page
         */
        usernameEt = (EditText)findViewById(R.id.et_username);
        passwordEt = (EditText)findViewById(R.id.et_password);
    }

    public void onLogin(View view){

        String username = usernameEt.getText().toString();
        String password = passwordEt.getText().toString();

        String type = "login";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);

        backgroundWorker.execute(type, username, password);

    }

    //method to open Register activity when button on main page is pressed
    public void openRegister(View view){

        startActivity(new Intent(this, Register.class));
    }

    public void openRecords(View view){

        startActivity(new Intent(this, Records.class));
    }

}
