package com.example.abishek.collegeeventsnotifier;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by abishek on 13/4/16.
 */
public class ViewByCollege extends AppCompatActivity {

    ScrollView s;
    String user_given_key;
    ListView lv;
    String[] more_than_one_url, more_than_one_search_key;
    String searchkey, error, IllError, nullError;
    boolean no_serach_key, checker = false, IlleagalException = false, nullException = false;
    Database d = new Database(this);


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_by_college);
        s = (ScrollView) findViewById(R.id.scrollView);
        lv = (ListView) findViewById(R.id.listView3);
        Intent intent = getIntent();
        String url = intent.getExtras().getString("url");
        searchkey = intent.getExtras().getString("serachkey");
        user_given_key = intent.getExtras().getString("key");
        Async async = new Async();
        async.execute(url);
    }

    private class Async extends AsyncTask<String, Void, String> {


        ProgressDialog progressDialog;
        String uri;
        String ret, body, event, search;
        ArrayList<Map<String, String>> output_of_search = new ArrayList<>();
        SimpleAdapter search_adapter;

        @Override
        protected String doInBackground(String... params) {

            uri = params[0];
            assert uri != null;
            if (uri.length() == 0) {
                Toast.makeText(ViewByCollege.this, "Empty Uri", Toast.LENGTH_LONG).show();
            }
            more_than_one_url = uri.split(",");
            if (searchkey.length() == 0) {
                no_serach_key = true;
            }
            more_than_one_search_key = searchkey.split(",");
            if (no_serach_key) {
                for (String url : more_than_one_url) {


                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(url).build();
                    try {
                        Response response = client.newCall(request).execute();
                        ret = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Document doc = Jsoup.parse(ret);
                    body = doc.getAllElements().text();
                    if (user_given_key.length() != 0) {
                        user_given_key = user_given_key.toLowerCase();
                        if (body.contains(user_given_key)) {
                            Map<String, String> datum = new HashMap<String, String>(2);

                            search = body.substring(event.indexOf(user_given_key) - 100, body.indexOf(user_given_key) + 100);
                            datum.put("First Line", d.getCollegeNameForURL(url));
                            datum.put("Second Line", search);
                            output_of_search.add(datum);
                        }
                    }


                }
            } else {
                for (String url : more_than_one_url) {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(url).build();
                    try {
                        Response response = client.newCall(request).execute();
                        ret = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Document doc = Jsoup.parse(ret);
                    body = doc.getAllElements().text();
                    for (String key : more_than_one_search_key) {
                        if (body.contains(key)) {
                            event = body.substring(body.indexOf(key) - 200, body.indexOf(key)).toLowerCase();
                        }
                        if (user_given_key.length() != 0) {
                            user_given_key = user_given_key.toLowerCase();
                            if (event.contains(user_given_key)) {
                                Map<String, String> datum = new HashMap<>(2);
                                try {
                                    search = event.substring(event.indexOf(user_given_key) - 100, event.indexOf(user_given_key) + 100);
                                    datum.put("First Line", d.getCollegeNameForURL(url));
                                    datum.put("Second Line", search);
                                    output_of_search.add(datum);
                                } catch (StringIndexOutOfBoundsException e) {
                                    if (event.indexOf(user_given_key) - 100 < 0 && event.indexOf(user_given_key) + 100 > event.length()) {
                                        datum.put("First Line", d.getCollegeNameForURL(url));
                                        datum.put("Second Line", event);
                                        output_of_search.add(datum);
                                    } else if (event.indexOf(user_given_key) - 100 < 0) {
                                        search = event.substring(0, event.indexOf(user_given_key) + 100);
                                        datum.put("First Line", d.getCollegeNameForURL(url));
                                        datum.put("Second Line", search);
                                        output_of_search.add(datum);
                                    } else if (event.indexOf(user_given_key) + 100 > event.length()) {
                                        search = event.substring(event.indexOf(user_given_key) - 100, event.length());
                                        datum.put("First Line", d.getCollegeNameForURL(url));
                                        datum.put("Second Line", search);
                                        output_of_search.add(datum);
                                    } else {
                                        error = e.getCause().toString() + e.getMessage();
                                        checker = true;
                                    }
                                }
                            }
                        } else {
                            for (String search_key : more_than_one_search_key) {
                                try {
                                    Map<String, String> datum = new HashMap<>(2);
                                    search = event.substring(event.indexOf(search_key) - 100, event.indexOf(search_key) + 100);
                                    datum.put("First Line", d.getCollegeNameForURL(url));
                                    datum.put("Second Line", search);
                                    output_of_search.add(datum);
                                } catch (StringIndexOutOfBoundsException e) {
                                    Map<String, String> datum = new HashMap<>(2);
                                    if (event.indexOf(key) - 100 < 0 && event.indexOf(key) + 100 > event.length()) {
                                        datum.put("First Line", d.getCollegeNameForURL(url));
                                        datum.put("Second Line", event);
                                        output_of_search.add(datum);
                                    } else if (event.indexOf(key) - 100 < 0) {
                                        search = event.substring(0, event.indexOf(key) + 100);
                                        datum.put("First Line", d.getCollegeNameForURL(url));
                                        datum.put("Second Line", search);
                                        output_of_search.add(datum);
                                    } else if (event.indexOf(user_given_key) + 100 > event.length()) {
                                        search = event.substring(event.indexOf(key) - 100, event.length());
                                        datum.put("First Line", d.getCollegeNameForURL(url));
                                        datum.put("Second Line", search);
                                        output_of_search.add(datum);
                                    } else {
                                        error = e.getMessage() + e.getCause();
                                        checker = true;
                                    }
                                } catch (NullPointerException e) {
                                    nullException = true;
                                    nullError = e.getMessage();
                                }

                            }

                        }
                    }

                }
            }

            //search_adapter=new ArrayAdapter<>(ViewByCollege.this,android.R.layout.simple_list_item_2,output_of_search);
            search_adapter = new SimpleAdapter(ViewByCollege.this, output_of_search,
                    android.R.layout.simple_list_item_2,
                    new String[]{"First Line", "Second Line"},
                    new int[]{android.R.id.text1, android.R.id.text2});
            return ret;
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(ViewByCollege.this, "onPreExecute()", Toast.LENGTH_LONG).show();
            progressDialog = ProgressDialog.show(ViewByCollege.this, "Loading", "Please Wait");

        }

        @Override
        protected void onPostExecute(String res) {
            progressDialog.dismiss();
            lv.setAdapter(search_adapter);
            Log.d("contains text", user_given_key);
            //Toast.makeText(ViewByCollege.this,String.valueOf(searchkey),Toast.LENGTH_LONG).show();
            Toast.makeText(ViewByCollege.this, String.valueOf(output_of_search.size()), Toast.LENGTH_LONG).show();
            if (checker) {
                Toast.makeText(ViewByCollege.this, error, Toast.LENGTH_LONG).show();
            }
            if (IlleagalException) {
                Toast.makeText(ViewByCollege.this, IllError, Toast.LENGTH_LONG).show();

            }
            if (nullException)
                Toast.makeText(ViewByCollege.this, nullError, Toast.LENGTH_LONG).show();
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Map<String, String> m = (HashMap<String, String>) parent.getItemAtPosition(position);
                    String passing_url = d.getUrl(m.get("First Line"));
                    Intent intent = new Intent(ViewByCollege.this, WebViewActivity.class);
                    intent.putExtra("url", passing_url);
                    startActivity(intent);
                }

            });
        }

    }

}


