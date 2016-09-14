package com.example.abishek.collegeeventsnotifier;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
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
 * Created by abishek on 16/5/16.
 */
public class ViewByTopic extends AppCompatActivity {

    EditText e1;
    Spinner spinner;
    ListView lv;
    Button b;
    String user_given_key, location, error, nullerror;
    boolean checker, nullchecker;
    ArrayList<String> locations = new ArrayList<>();
    ArrayList<ArrayList<String>> locationUrls, urlbyplace = new ArrayList<>();
    ArrayList<ArrayList<String>> searchkeysbyplace = new ArrayList<>();
    ArrayList<Map<String, String>> output_of_search = new ArrayList<>();
    Database d = new Database(this);
    private boolean isSpinnerInitial = true;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_by_topic);

        try {
            locations = d.getCollegeLocations();
        } catch (NullPointerException e) {
            e.printStackTrace();
            Toast.makeText(this, String.valueOf(locations.size()), Toast.LENGTH_LONG).show();
        }
        spinner = (Spinner) findViewById(R.id.spinner);
        lv = (ListView) findViewById(R.id.listView2);
        // Creating adapter for spinner

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, locations);


        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isSpinnerInitial) {
                    isSpinnerInitial = false;
                } else {
                    e1 = (EditText) findViewById(R.id.editText4);
                    user_given_key = e1.getText().toString();
                    location = parent.getItemAtPosition(position).toString();
                    locationUrls = d.getCollegeUrlForLocation(location);
                    searchkeysbyplace = d.getAllSearchKeysByLocation(location);
                    Async async = new Async();
                    async.execute(locationUrls);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                location = null;
                Toast.makeText(ViewByTopic.this, "please select a location", Toast.LENGTH_LONG).show();

            }
        });

    }


    private class Async extends AsyncTask<ArrayList<ArrayList<String>>, Void, String> {


        ProgressDialog progressDialog;
        String ret, body, event, search, college;
        SimpleAdapter search_adapter;

        @Override
        protected String doInBackground(ArrayList<ArrayList<String>>... params) {
            urlbyplace = params[0];
            int i = 0, it, itr;
            if (user_given_key.length() == 0) {
                for (it = 0; it < urlbyplace.size(); it++) {
                    for (String url : urlbyplace.get(it)) {
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder().url(url).build();
                        try {
                            Response response = client.newCall(request).execute();
                            ret = response.body().string();
                            Document doc = Jsoup.parse(ret);
                            body = doc.getAllElements().text();
                            college = d.getCollegeNameForURL(url);
                        } catch (IOException | IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (NullPointerException e) {
                            Log.d("body", body);
                            continue;
                        }
                        for (ArrayList<String> te : searchkeysbyplace) {
                            if (te.get(0).equals(college)) {
                                i = searchkeysbyplace.indexOf(te);
                                break;
                            }

                        }
                        for (String key : searchkeysbyplace.get(i)) {
                            if (body.contains(key)) {
                                Map<String, String> datum = new HashMap<String, String>(2);
                                try {

                                    event = body.substring(body.indexOf(key) - 100, body.indexOf(key)).toLowerCase();
                                    search = body.substring(event.indexOf(key) - 100, body.indexOf(key) + 100);
                                    datum.put("First Line", d.getCollegeNameForURL(url));
                                    datum.put("Second Line", search);
                                    output_of_search.add(datum);
                                } catch (StringIndexOutOfBoundsException e) {
                                    if (event.indexOf(key) - 100 < 0 && event.indexOf(key) + 100 > event.length()) {
                                        datum.put("First Line", d.getCollegeNameForURL(url));
                                        datum.put("Second Line", event);
                                    } else if (event.indexOf(key) - 100 < 0) {
                                        search = event.substring(0, event.indexOf(key) + 100);
                                        datum.put("First Line", d.getCollegeNameForURL(url));
                                        datum.put("Second Line", search);
                                    } else if (event.indexOf(key) + 100 > event.length()) {
                                        search = event.substring(event.indexOf(key) - 100, event.length());
                                        datum.put("First Line", d.getCollegeNameForURL(url));
                                        datum.put("Second Line", search);
                                    } else {
                                        error = e.getMessage() + e.getCause();
                                        checker = true;
                                    }
                                    output_of_search.add(datum);
                                } catch (NullPointerException e) {
                                    nullchecker = true;
                                    nullerror = url;
                                }


                            }

                        }

                    }
                }


            } else {

                assert urlbyplace != null;
                for (itr = 0; itr < urlbyplace.size(); itr++) {
                    for (String url : urlbyplace.get(itr)) {
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
                                Map<String, String> datum = new HashMap<String, String>();
                                try {
                                    search = body.substring(event.indexOf(user_given_key) - 100, body.indexOf(user_given_key) + 100);
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
                                        error = e.getMessage() + e.getCause();
                                        checker = true;
                                    }
                                }
                            }
                        }


                    }
                }
            }


            //search_adapter = new ArrayAdapter<>(ViewByTopic.this, android.R.layout.simple_list_item_1, output_of_search);
            search_adapter = new SimpleAdapter(ViewByTopic.this, output_of_search,
                    android.R.layout.simple_list_item_2,
                    new String[]{"First Line", "Second Line"},
                    new int[]{android.R.id.text1, android.R.id.text2});


            return ret;
        }


        @Override
        protected void onPreExecute() {
            Toast.makeText(ViewByTopic.this, "onPreExecute()", Toast.LENGTH_LONG).show();
            progressDialog = ProgressDialog.show(ViewByTopic.this, "Loading", "Please Wait");

        }

        @Override
        protected void onPostExecute(String res) {
            progressDialog.dismiss();
            lv.setAdapter(search_adapter);
            Log.d("contains text", user_given_key);
            //Toast.makeText(ViewByCollege.this,String.valueOf(searchkey),Toast.LENGTH_LONG).show();
            Toast.makeText(ViewByTopic.this, String.valueOf(output_of_search.size()), Toast.LENGTH_LONG).show();
            if (checker) {
                Toast.makeText(ViewByTopic.this, error, Toast.LENGTH_LONG).show();
            }
            if (nullchecker) {
                Toast.makeText(ViewByTopic.this, nullerror, Toast.LENGTH_LONG).show();
            }
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Map<String, String> m = (HashMap<String, String>) parent.getItemAtPosition(position);
                    String passing_url = d.getUrl(m.get("First Line"));

                    Intent intent = new Intent(ViewByTopic.this, WebViewActivity.class);
                    intent.putExtra("url", passing_url);
                    startActivity(intent);
                }

            });
        }

    }

}



