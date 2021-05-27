package com.example.zcalamityappver15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResidentActivity extends AppCompatActivity {
    EditText username, city, barangay, street, number;
    Button submit;
    Res_DB DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident);
        username = (EditText)findViewById(R.id.un);
        city = (EditText)findViewById(R.id.city);
        barangay = (EditText)findViewById(R.id.brngy);
        street = (EditText)findViewById(R.id.street);
        number = (EditText)findViewById(R.id.mn);
        submit = (Button)findViewById(R.id.btnSubmit);
        DB = new Res_DB(this);

        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String user = username.getText().toString();
                String ct = city.getText().toString();
                String brngy = barangay.getText().toString();
                String st = street.getText().toString();
                String mn = number.getText().toString();

                if (user.equals("") || mn.equals("")|| ct.equals("") || brngy.equals("") || st.equals(""))
                    Toast.makeText(ResidentActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean insert = DB.insertData(user, mn, ct, brngy, st);
                    if (insert==true){
                        Toast.makeText(ResidentActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), DashboardActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(ResidentActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}