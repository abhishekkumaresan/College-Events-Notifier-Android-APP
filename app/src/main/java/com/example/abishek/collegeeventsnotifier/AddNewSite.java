package com.example.abishek.collegeeventsnotifier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by abishek on 2/4/16.
 */
public class AddNewSite extends AppCompatActivity {
    String loc;
    Boolean ab;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_site);
        final EditText e1 = (EditText) findViewById(R.id.editText);
        final EditText e2 = (EditText) findViewById(R.id.editText2);
        final EditText e3 = (EditText) findViewById(R.id.editText3);
        Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(getApplicationContext());
                loc = e3.getText().toString();
                if (loc.length() == 0) {
                    ab = db.insertNewCollege(e1.getText().toString(), e2.getText().toString(), null);
                }
                ab = db.insertNewCollege(e1.getText().toString(), e2.getText().toString(), loc);
                if (ab) {
                    Toast.makeText(getApplicationContext(), "New website has been added  successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Error in Adding", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
