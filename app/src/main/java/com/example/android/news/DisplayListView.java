package com.example.android.news;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    CustomAdapter customAdapter;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_layout);

        listView=(ListView) findViewById(R.id.listview);
        customAdapter =new CustomAdapter(this,R.layout.row_layout);
        listView.setAdapter(customAdapter);






        json_string=getIntent().getExtras().getString("json_data");
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray=jsonObject.getJSONArray("articles");
            int count=0;

            String author,title;

            while (count<jsonArray.length()){
                JSONObject JO=jsonArray.getJSONObject(count);
                author=JO.getString("author");
                title=JO.getString("title");

                NewsFeed newsFeed=new NewsFeed(author,title);
                customAdapter.add(newsFeed);
            count++;
            }



        }catch (JSONException e){
            e.printStackTrace();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter,View view,int position,long arg){
                try {
                    JSONObject JO1 = jsonArray.getJSONObject(position);
                    String value=JO1.toString();
                    Intent newsInfo=new Intent(DisplayListView.this,NewsInfo.class);
                    newsInfo.putExtra("row data",value);
                    startActivity(newsInfo);
                }catch (JSONException e){
                    e.printStackTrace();
                }
                //Object json_string1= listView.getItemAtPosition(position);
            }
        });



    }
}
