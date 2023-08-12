package air.texnodev.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.ArrayList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import air.texnodev.weatherapp.Adapter.AModel;
import air.texnodev.weatherapp.Adapter.SAdapter;
import air.texnodev.weatherapp.Service.ConnectionImpl;
import air.texnodev.weatherapp.Service.FileServiceImpl;
import air.texnodev.weatherapp.Service.Internet;
import air.texnodev.weatherapp.Service.Net;
import air.texnodev.weatherapp.Service.OkHttps;

public class MainActivity extends AppCompatActivity implements OnItemsClick{

    public TextView textView;
    SearchView searchView;
    ArrayList<AModel> aModels = new ArrayList<>();
    RecyclerView recyclerView;
    private RequestQueue requestQueue;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.new_my_text);


        searchView = (SearchView) findViewById(R.id.search_now);
       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String s) {
               setSearchQuery(s);
               Toast.makeText(MainActivity.this,s, Toast.LENGTH_SHORT).show();
               return false;
           }

           @Override
           public boolean onQueryTextChange(String s) {
               return false;
           }
       });
    }

    public void setSearchQuery(String query){


        requestQueue = Volley.newRequestQueue(MainActivity.this);
        String url = "http://45e30b7f.services.gismeteo.ru/inform-service/a407a91cfcb53e52063b77e9e777f5bd/cities/?search_all=1&lat_lng=1&count=20&lang=en&startsWith="+query;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        aModels.clear();
                        textView.setText(response);
                        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                       // textView.setText(response);
                        try {
                        //builderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

                        DocumentBuilder builder = builderFactory.newDocumentBuilder();
                        Document document = builder.parse(new InputSource(new StringReader(response)));
                        document.getDocumentElement().normalize();

                        NodeList nodeList = document.getElementsByTagName("item");

                        String conyty = "";
                        for (int i = 0; i < nodeList.getLength(); i++) {
                            Node node = nodeList.item(i);
                            if (node.hasAttributes()){
                                Element element = (Element) node;
                                String name = element.getAttribute("n");
                                String lon = element.getAttribute("lng");
                                String lat = element.getAttribute("lat");
                                String country = element.getAttribute("country_name");
                                String id = element.getAttribute("id");
                                aModels.add(new AModel(name, country, lat, lon));

                              conyty += "\n" + name + ", " + country;
                            }

                        }
                            recyclerView = findViewById(R.id.searching);
                            SAdapter adapter = new SAdapter(MainActivity.this, aModels, MainActivity.this);
                            recyclerView.setAdapter(adapter);

                        textView.setText(conyty);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(stringRequest);


    }


    @Override
    public void onClick(View view, int pozitsion) {
        String lat = ((TextView) view.findViewById(R.id.Long)).getText().toString();
        String lng = ((TextView) view.findViewById(R.id.Lng)).getText().toString();
        String country = ((TextView) view.findViewById(R.id.City_Country)).getText().toString();
        Intent intent = new Intent(this, Weather_Activity.class);
        intent.putExtra("lat", lat);
        intent.putExtra("lng", lng);
        intent.putExtra("country", country);
        startActivity(intent);

        //Toast.makeText(this, lat + "  "+ lng,  Toast.LENGTH_SHORT).show();
    }
}