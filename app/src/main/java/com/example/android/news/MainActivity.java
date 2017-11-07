package com.example.android.news;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.news.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Created by VIshal on 03-11-2017.
 */

public class MainActivity extends AppCompatActivity {



    EditText emailText;
    TextView responseView;
    ProgressBar progressBar;
    String json_string;

    static final String API_KEY="07f979115b4e4c8da0ccdf2485c715c8";
    static final String API_URL="https://newsapi.org/v1/articles?";
    // static final String API_KEY="17b5df3f0a747573";
    // static final String API_URL="https://api.fullcontact.com/v2/person.json?";


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseView =(TextView) findViewById(R.id.responseView);
        emailText= (EditText) findViewById(R.id.emailText);
        progressBar =(ProgressBar) findViewById(R.id.progressBar);
        final String url="http://www.google.com";
        Button queryButton=(Button) findViewById(R.id.queryButton);
        Button convButton=(Button) findViewById(R.id.convButton);
        //Button googleButton=(Button) findViewById(R.id.google);

        /*googleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
                startActivity(intent);
            }
        });*/

        queryButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new RetrieveFeedTask().execute();
            }

        });


    }

    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            responseView.setText("");
        }

        protected String doInBackground(Void... urls) {
            String email = emailText.getText().toString();
            // Do some validation here

            try {
                URL url = new URL(API_URL + "source=" + email + "&apiKey=" + API_KEY);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            progressBar.setVisibility(View.GONE);
            responseView.setText(response);
            json_string=response;
            /*
            if (response == null) {
                response = "THERE WAS AN ERROR";
                json_string=response;
            }

            progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);
            responseView.setText(response);
            // TODO: check this.exception
            // TODO: do something with the feed
*/
           /*try {
                JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
                String requestID = object.getString("requestId");
                int likelihood = object.getInt("likelihood");
                JSONArray photos = object.getJSONArray("photos");




            } catch (JSONException e) {
                e.printStackTrace();
            }*/
        }
    }

public void parseJSON(View view){

    if (json_string==null){
        Toast.makeText(getApplicationContext(),"First search news source",Toast.LENGTH_LONG).show();

    }
    else{
        Intent intent=new Intent(MainActivity.this,DisplayListView.class);
        intent.putExtra("json_data",json_string);
        startActivity(intent);

    }

}


}
