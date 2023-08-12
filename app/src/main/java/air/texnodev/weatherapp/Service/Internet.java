package air.texnodev.weatherapp.Service;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Internet implements HttpClientInterface {

    private static final String TAG = OkHttpClient.class.getSimpleName();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient httpClient = new OkHttpClient();

    @Override
    public String post(String url, String json) {

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = null;
        try {
            response = httpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response.body().toString();
    }
}

