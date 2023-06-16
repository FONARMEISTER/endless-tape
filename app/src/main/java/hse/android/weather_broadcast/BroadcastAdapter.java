package hse.android.weather_broadcast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BroadcastAdapter extends RecyclerView.Adapter<BroadcastAdapter.BroadcastViewHolder> {

    private final BroadcastContainer broadcastContainer = BroadcastContainer.BroadcastContainerProvider.provide();
    private final LayoutInflater layoutInflater;

    public BroadcastAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public BroadcastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.broadcast_layout, parent, false);
        return new BroadcastViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull BroadcastViewHolder holder, int position) {
        Broadcast broadcast = broadcastContainer.getBroadcast(position);
        TextView date = holder.broadcastLayout.findViewById(R.id.broadcastDate);
        TextView temperature = holder.broadcastLayout.findViewById(R.id.broadcastTemperature);
        TextView city = holder.broadcastLayout.findViewById(R.id.broadcastCity);
        date.setText(broadcast.getDate().toString());
        temperature.setText(broadcast.getTemperature());
        city.setText(broadcast.getCity());
    }

    @Override
    public int getItemCount() {
        return broadcastContainer.getSize();
    }

    class BroadcastViewHolder extends RecyclerView.ViewHolder {

        public final LinearLayout broadcastLayout;
        public final BroadcastAdapter broadcastAdapter;

        public BroadcastViewHolder(@NonNull View itemView, BroadcastAdapter adapter) {
            super(itemView);
            broadcastLayout = itemView.findViewById(R.id.broadcastView);
            broadcastAdapter = adapter;
        }
    }


}
