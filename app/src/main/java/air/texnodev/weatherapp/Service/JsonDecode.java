package air.texnodev.weatherapp.Service;

import air.texnodev.weatherapp.Models.Root;

import java.util.List;

public interface JsonDecode {
    List<Root> JsonDe(String json);
}
