package hse.android.weather_broadcast;

import java.util.Date;

public class Broadcast {

    private Date date;
    private String temperature;
    private String city;

    public Broadcast(Date date, String temperature, String city) {
        this.date = date;
        this.temperature = temperature;
        this.city = city;
    }


    public String getCity() {
        return city;
    }

    public String getTemperature() {
        return temperature;
    }

    public Date getDate() {
        return date;
    }
}
