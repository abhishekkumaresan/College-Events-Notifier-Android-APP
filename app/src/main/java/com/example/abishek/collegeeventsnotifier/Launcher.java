package com.example.abishek.collegeeventsnotifier;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by abishek on 16/5/16.
 */
public class Launcher extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laucher);
        Button b1 = (Button) findViewById(R.id.button);
        Button b2 = (Button) findViewById(R.id.button2);
        Button b3 = (Button) findViewById(R.id.button4);
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
        assert b1 != null;
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Launcher.this, MainActivity.class);
                startActivity(intent);
            }
        });
        assert b2 != null;
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Launcher.this, ViewByTopic.class);
                startActivity(intent);

            }
        });
        assert b3 != null;
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Launcher.this, AddNewSite.class);
                startActivity(intent);

            }
        });

    }
}
