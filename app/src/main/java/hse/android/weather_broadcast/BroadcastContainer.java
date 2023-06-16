package hse.android.weather_broadcast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;

public class BroadcastContainer {

    private final MutableLiveData<List<Broadcast>> broadcastList = new MutableLiveData<>();

    {
        broadcastList.postValue(new ArrayList<>());
    }

    public void setObserver(Observer<List<Broadcast>> observer){
        broadcastList.observeForever(observer);
    }

    public synchronized void addBroadcast(Broadcast broadcast) {
        List<Broadcast> tmp = broadcastList.getValue();
        tmp.add(broadcast);
        broadcastList.postValue(tmp);
    }

    public Broadcast getBroadcast(int pos) {
        return broadcastList.getValue().get(pos);
    }

    public int getSize() {
        return broadcastList.getValue().size();
    }


    public static class BroadcastContainerProvider {
        private static final BroadcastContainer broadcastContainer = new BroadcastContainer();

        public static BroadcastContainer provide() {
            return broadcastContainer;
        }

    }

}
