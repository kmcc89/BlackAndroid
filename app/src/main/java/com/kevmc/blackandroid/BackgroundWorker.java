package com.kevmc.blackandroid;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by kevmc on 22/05/2018.
 */

public class BackgroundWorker extends AsyncTask<String, Void, String> {

    Context context;

    //alert dialog to show data - initiate it in onPreExec method
    AlertDialog alertDialog;

    BackgroundWorker(Context ctx){
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {

        //extract values
        //get first value from params list - we sent type first
        String type = params[0];

        //define url
        String login_url = "http://10.0.2.2/black_android/login.php";

        String register_url = "http://10.0.2.2/black_android/register.php";

        String record_url = "http://10.0.2.2/black_android/records.php";

        if(type.equals("login")){
            //code to post data
            try {
                //get username and password values from params
                String username = params[1];
                String password = params[2];

                //create url object
                URL url = new URL(login_url);

                //use http url connection class
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

                //set request method
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                //create instance of output stream
                OutputStream outputStream = httpURLConnection.getOutputStream();

                //create instance of buffered writer
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                //DATA WE want to post
                String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");

                //WRITE DATA TO BUFFER
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                //read response from post request
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                //read response line by line
                String result = "";
                String line = "";

                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }

                //close buff reader
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                /*
                When we return a string we must change the return type of the method
                We must then change the third parameter of the AsyncTask parameters to string also
                We must also change the parameter type of the onProgressUpdate method to string too
                 */
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //ENDS OF IF CONDITION FOR LOGIN
        } else if(type.equals("register")){

            try {

                String name = params[1];
                String surname = params[2];
                String age = params[3];
                String username = params[4];
                String password = params[5];

                URL url = new URL(register_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name, "UTF-8")+"&"
                        +URLEncoder.encode("surname","UTF-8")+"="+URLEncoder.encode(surname, "UTF-8")+"&"
                        +URLEncoder.encode("age","UTF-8")+"="+URLEncoder.encode(age, "UTF-8")+"&"
                        +URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username, "UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password, "UTF-8");

                bufferedWriter.write(post_data);

                bufferedWriter.flush();
                bufferedWriter.close();

                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                String result = "";
                String line = "";

                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }

                //close buff reader
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //END OF REGISTER IF STATEMENT

        }else if (type.equals("record")){

            try{

                String username = params[1];

                URL url = new URL(record_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("username", "utf-8")+"="+URLEncoder.encode(username, "UTF-8");

                bufferedWriter.write(post_data);

                bufferedWriter.flush();
                bufferedWriter.close();

                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                String result = "";
                String line = "";

                while((line = bufferedReader.readLine()) != null){

                    result += line;
                }

                //CLOSE BUFF READER, INPUT STREAM AND DISCONNECT FROM HTTP URL CONNECTION
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }//END OF doInBackground method

    @Override
    protected void onPreExecute(){
        alertDialog = new AlertDialog.Builder(context).create();
        //set title
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result){
        //show data sent back from server
        alertDialog.setMessage(result);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values){
        super.onProgressUpdate(values);
    }
}
