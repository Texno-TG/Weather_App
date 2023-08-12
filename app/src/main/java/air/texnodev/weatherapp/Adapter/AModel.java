package air.texnodev.weatherapp.Adapter;

public class AModel {

    String name;
    String country;
    String lon;
    String lng;

    public AModel(String name, String country, String lon, String lng) {
        this.name = name;
        this.country = country;
        this.lon = lon;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
