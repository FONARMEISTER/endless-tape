package hse.android.weather_broadcast;

import android.content.Context;
import android.text.format.Time;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOError;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyWorker extends Worker {
    private final OkHttpClient client = new OkHttpClient();
    BroadcastContainer broadcastContainer = BroadcastContainer.BroadcastContainerProvider.provide();

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Request request = new Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/weather?lat=59.937500&lon=30.308611&appid=53490b7df3f00a6485752eaae0747216")
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("not successful");
            }
            JSONObject jsonObject = new JSONObject(response.body().string());
            JSONObject main = jsonObject.getJSONObject("main");
            String city = jsonObject.get("timezone").toString();
            String temp = main.get("temp").toString();
            Date date = Calendar.getInstance().getTime();
            Broadcast broadcast = new Broadcast(date, temp, city);
            broadcastContainer.addBroadcast(broadcast);
            Log.i("response", "Successful");
            return Result.success();
        } catch (IOException e) {
            Log.e("request", e.getMessage());
        } catch (JSONException e) {
            Log.e("json", e.getMessage());
        }
        return Result.failure();
    }
}
