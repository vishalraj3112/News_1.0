package com.example.android.news;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;

import java.io.InputStream;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsInfo extends Activity{
    String jsonString_1;
    JSONObject jsonObject;
    String urlString;
    WebView web;
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        progressBar=(ProgressBar) findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);



        jsonString_1=getIntent().getExtras().getString("row data");

        try {
            jsonObject =new JSONObject(jsonString_1);
            urlString=jsonObject.getString("url");
        }catch (JSONException e){
            e.printStackTrace();
        }

        web=(WebView) findViewById(R.id.webview01);
        web.setWebViewClient(new myWebViewClient());
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(urlString);
    }
    class myWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url_one) {
            // TODO Auto-generated method stub

            view.loadUrl(url_one);
            return true;

        }
        @Override
        public void onPageFinished(WebView view,String url_one){
           progressBar.setVisibility(View.GONE);
        }
    }

        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            if ((keyCode == KeyEvent.KEYCODE_BACK) && web.canGoBack()) {
                web.goBack();
                return true;
            }
            return super.onKeyDown(keyCode, event);
        }



    }









/*
public class NewsInfo extends AppCompatActivity {
    String jsonString_1;
    JSONObject jsonObject;
    String title,des,urlString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);
        jsonString_1=getIntent().getExtras().getString("row data");

       try {
            jsonObject =new JSONObject(jsonString_1);
            title=jsonObject.getString("title");
            des=jsonObject.getString("description");
            urlString=jsonObject.getString("urlToImage");
        }catch (JSONException e){
            e.printStackTrace();
        }

        TextView titleview=(TextView) findViewById(R.id.infoTitle);
        TextView desview =(TextView) findViewById(R.id.indoText);
//        TextView linkview=(TextView) findViewById(R.id.link);

        titleview.setText(title);
        desview.setText(des);
//        linkview.setText(urlString);
        new DownloadImageTask((ImageView) findViewById(R.id.infoImage))
                .execute(urlString);



    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }



    }

 */