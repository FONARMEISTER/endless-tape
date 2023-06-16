package hse.android.weather_broadcast;

public class CurrentBroadcast {

    private Broadcast currentBroadcast;

    public static class CurrentBroadcastProvider {
        private static final CurrentBroadcast currentBroadcast = new CurrentBroadcast();

        public static CurrentBroadcast provide() {
            return currentBroadcast;
        }
    }

    public void setCurrentBroadcast(Broadcast broadcast){
        this.currentBroadcast = broadcast;
    }

    public Broadcast getCurrentBroadcast() {
        return currentBroadcast;
    }
}
