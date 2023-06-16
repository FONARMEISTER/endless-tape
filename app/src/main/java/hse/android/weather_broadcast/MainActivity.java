package hse.android.weather_broadcast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.WorkerParameters;

import android.os.Bundle;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    private BroadcastAdapter broadcastAdapter;
    private final BroadcastContainer broadcastContainer = BroadcastContainer.BroadcastContainerProvider.provide();

    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recycler = findViewById(R.id.broadcastRecycler);
        broadcastAdapter = new BroadcastAdapter(this);
        recycler.setAdapter(broadcastAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                loadNextBroadcast();
            }
        });
        broadcastContainer.setObserver(list -> broadcastAdapter.notifyDataSetChanged());
        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(MyWorker.class).build();
        WorkManager.getInstance(this).enqueue(request);
    }

    private void loadNextBroadcast() {
        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(MyWorker.class).build();
        WorkManager.getInstance(this).enqueue(request);
    }
}