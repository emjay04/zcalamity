package com.example.zcalamityappver15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LocalActivity extends AppCompatActivity {

    EditText username, lguoffice, barangay, establishment, barangay1, street;
    Button submit;
    Local_DB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        username = (EditText)findViewById(R.id.un);
        lguoffice = (EditText)findViewById(R.id.office);
        barangay = (EditText)findViewById(R.id.brngy);
        establishment = (EditText)findViewById(R.id.establishment);
        barangay1 = (EditText)findViewById(R.id.brngy1);
        street = (EditText)findViewById(R.id.street);
        submit = (Button)findViewById(R.id.btnSubmit);
        DB = new Local_DB(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                String office = lguoffice.getText().toString();
                String brngy = barangay.getText().toString();
                String est = establishment.getText().toString();
                String brngy1 = barangay1.getText().toString();
                String st = street.getText().toString();

                if (name.equals("") || office.equals("")|| brngy.equals("") || est.equals("") || brngy1.equals("") || st.equals(""))
                    Toast.makeText(LocalActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean insert = DB.insertData(name, office, brngy, est, brngy1, st);
                    if (insert==true){
                        Toast.makeText(LocalActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), DashboardActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LocalActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }





            }
        });

    }
}