package air.texnodev.weatherapp.Service;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.net.URL;

import okhttp3.OkHttpClient;

public class Net extends Volley{
    public  String result;


    public String Vollrys(String url, Context context){

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.start();
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                result = "oppen";

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                result = "errpo";
            }
        });

        request.setShouldCache(true);
        request.setTag("My_TAG");
        queue.add(request);
        queue.stop();
        return result;
    }
}
