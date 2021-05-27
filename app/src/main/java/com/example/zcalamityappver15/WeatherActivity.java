package com.example.zcalamityappver15;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;



public class WeatherActivity extends AppCompatActivity {

    EditText text;
    Button button;
    ImageView imageView;
    TextView location_yt, brngy_yt, mun_yt, type_yt;
    String username = "baguio.alyssa";
    String password = "Ldw3M8Sr";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        try{
            String url = "https://philsensors.asti.dost.gov.ph/api/station";
            HttpURLConnection conn = (HttpURLConnection) weburl.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
// snippet begins
            conn.setRequestProperty("Authorization",
                    "Basic " + Base64.getEncoder().encodeToString(
                            (username + ":" + password).getBytes()
                    )
            );
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //print result
            System.out.println(response.toString());
            // below code converts the json response to json object and reads each values
            JSONObject jsonObj = new JSONObject(response.toString());
            System.out.println(jsonObj.getString("station_id"));
// snippet ends

        }catch(Exception e){
//do exception handling here

        }
    }

}