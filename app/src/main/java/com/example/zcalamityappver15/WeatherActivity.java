package com.example.zcalamityappver15;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;


public class WeatherActivity extends AppCompatActivity {

    EditText text;
    Button button;
    ImageView imageView;
    TextView country_yt, city_yt, temp_yt, date_yt, longitude, latitude, humidity, sunrise, sunset, pressure, wind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

    text=findViewById(R.id.cityname);
    button=findViewById(R.id.btn);
    imageView=findViewById(R.id.imageView3);
    country_yt=findViewById(R.id.country);
    city_yt=findViewById(R.id.city);
    temp_yt=findViewById(R.id.temp);
    date_yt=findViewById(R.id.date);

    longitude=findViewById(R.id.Longitude);
    latitude=findViewById(R.id.Latitude);
    humidity=findViewById(R.id.Humidity);
    sunrise=findViewById(R.id.Sunrise);
    sunset=findViewById(R.id.Sunset);
    pressure=findViewById(R.id.Pressure);
    wind=findViewById(R.id.Wind);



    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            findWeather();
        }
    });

    }

    public void findWeather(){
        String city = text.getText().toString();
        String url = "http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=31765415ed251c53ae90ff61d828688b";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(String response) {
                //calling API
                try {
                    JSONObject jsonObject = new JSONObject(response);
                //find country
                    JSONObject object1 = jsonObject.getJSONObject("sys");
                    String country_find = object1.getString("country");
                    country_yt.setText(country_find+"   :");
                //find city
                    String city_find = jsonObject.getString("name");
                    city_yt.setText(city_find);
                //find temperature
                    JSONObject object2 = jsonObject.getJSONObject("main");
                    double temp_find = object2.getDouble("temp");
                    temp_yt.setText(temp_find+"°C");
                //find date & time
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat std = new SimpleDateFormat("dd/MM/yyyy \nHH:mm:ss");
                    String date = std.format(calendar.getTime());
                    date_yt.setText(date);
                //find image icon
                    JSONArray jsonArray = jsonObject.getJSONArray("weather");
                    JSONObject obj = jsonArray.getJSONObject(0);
                    String icon = obj.getString("icon");
                    Picasso.get().load("http://openweathermap.org/img/wn/"+icon+"@2x.png").into(imageView);
                //find latitude
                    JSONObject object = jsonObject.getJSONObject("coord");
                    double lat_find = object.getDouble("lat");
                    latitude.setText(lat_find+" °N");

                //find longitude
                    JSONObject object3 = jsonObject.getJSONObject("coord");
                    double long_find = object3.getDouble("lon");
                    longitude.setText(long_find+" °E");
                //find humidity
                    JSONObject object4 = jsonObject.getJSONObject("main");
                    int humidity_find = object4.getInt("humidity");
                    humidity.setText(humidity_find+" %");
                //find sunrise
                    JSONObject object5 = jsonObject.getJSONObject("sys");
                    String sunrise_find = object5.getString("sunrise");
                    sunrise.setText(sunrise_find+"");
                //find sunset
                    JSONObject object6 = jsonObject.getJSONObject("sys");
                    String sunset_find = object6.getString("sunset");
                    sunset.setText(sunset_find+"");
                //find pressure
                    JSONObject object7 = jsonObject.getJSONObject("main");
                    String pressure_find = object7.getString("pressure");
                    pressure.setText(pressure_find+" hPa");
                //find wind
                    JSONObject object8 = jsonObject.getJSONObject("wind");
                    String wind_find = object8.getString("speed");
                    wind.setText(wind_find+" km/h");


                }catch (JSONException e){
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(WeatherActivity.this,error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(WeatherActivity.this);
        requestQueue.add(stringRequest);
    }

}