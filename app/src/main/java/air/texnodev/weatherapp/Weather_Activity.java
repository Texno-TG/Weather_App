package air.texnodev.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import air.texnodev.weatherapp.Adapter.AModel;
import air.texnodev.weatherapp.Adapter.SAdapter;
import air.texnodev.weatherapp.Models.Root;

public class Weather_Activity extends AppCompatActivity {
    private RequestQueue requestQueue;
    TextView city, country, dayle, tempo, poss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        String lat = getIntent().getExtras().getString("lat");
        String lng = getIntent().getExtras().getString("lng");
        String country1 = getIntent().getExtras().getString("country");
        country = findViewById(R.id.City_and_Country);
        country.setText(country1);

        DateFormat dateFormat = new SimpleDateFormat("EEE, MMM dd");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        dayle = findViewById(R.id.dayliy);
        dayle.setText(date.toString());

       // Toast.makeText(this, lat + "  " + lng, Toast.LENGTH_SHORT).show();


        requestQueue = Volley.newRequestQueue(this);
        String url = "https://openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lng+"&units=metric&appid=439d4b804bc8187953eb36d2a8c26a02";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Type type = new TypeToken<Root>(){}.getType();
                        Gson gson = new Gson();
                        Root root = gson.fromJson(response, type);
                        tempo = findViewById(R.id.Temps);
                        tempo.setText(String.valueOf(Math.round(root.main.temp)));
                        poss = findViewById(R.id.possision);
                        poss.setText(root.weather.get(0).main);

                        //Toast.makeText(Weather_Activity.this, root.getName(), Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(stringRequest);


    }
}