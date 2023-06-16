package hse.android.weather_broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView date = findViewById(R.id.currentBroadcastDate);
        TextView temp = findViewById(R.id.currentBroadcastTemperature);
        TextView city = findViewById(R.id.currentBroadcastCity);
        CurrentBroadcast currentBroadcast = CurrentBroadcast.CurrentBroadcastProvider.provide();
        Broadcast cur = currentBroadcast.getCurrentBroadcast();
        date.setText(cur.getDate().toString());
        temp.setText(cur.getTemperature());
        city.setText(cur.getCity());
    }
}