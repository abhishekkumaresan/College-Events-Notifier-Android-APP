package com.example.abishek.collegeeventsnotifier;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    EditText e1;
    android.support.v7.widget.SearchView sv;
    String key = null;
    String searchkey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listView);
        e1 = (EditText) findViewById(R.id.editText);
        final Database d = new Database(this);
        SharedPreferences pref = getSharedPreferences("mypref", MODE_PRIVATE);

        if (pref.getBoolean("firststart", true)) {
            // update sharedpreference - another start wont be the first
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("firststart", false);
            editor.apply(); // apply changes

            // first start, show your dialog | first-run code goes here
            d.insertColleges();
            Toast.makeText(this, "Initialization competed", Toast.LENGTH_LONG).show();
        }
        ArrayList<String> colleges = d.getCollegeNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, colleges);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cname = (String) parent.getItemAtPosition(position);
                String c = d.getUrl(cname);
                searchkey = d.getSearchKeysByCollegeName(cname);
                key = e1.getText().toString();
                if (c != null && key != "") {
                    Intent i = new Intent(MainActivity.this, ViewByCollege.class);
                    i.putExtra("url", c);
                    i.putExtra("key", key);
                    i.putExtra("serachkey", searchkey);
                    startActivity(i);

                } else
                    Toast.makeText(getApplicationContext(), "URL NOT FOUND", Toast.LENGTH_SHORT).show();


            }


        });
    }
}


