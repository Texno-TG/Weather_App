package air.texnodev.weatherapp.Service;

import java.io.IOException;

public interface HttpClientInterface {
    String post(String url, String json) throws IOException, IOException;
}
