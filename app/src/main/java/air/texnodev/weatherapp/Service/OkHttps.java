package air.texnodev.weatherapp.Service;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttps extends AsyncTask {
    OkHttpClient client = new OkHttpClient();

    @Override
    protected String doInBackground(Object[] objects) {
        Request.Builder builder = new Request.Builder();
        builder.url((String) objects[0]);
        Request request = builder.build();
        try {
            Response response = client.newCall(request).execute();

            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String post(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
