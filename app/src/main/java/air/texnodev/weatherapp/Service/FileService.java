package air.texnodev.weatherapp.Service;

public interface FileService {
    void writeFile(String path, String text);

    String readFile(String path);
}
